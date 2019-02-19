package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.BattleManager;
import model.vo.User;
import music.Music;


public class Map extends JPanel implements Runnable, KeyListener {

	int port = 8600;


	private MainFrame mf;
	private Map m;
	private PInfoPage pip;
	private UserMenuPage ump; 
	private MarketView marketView;//SM_�߰�
	private CenterView centerView;
	private LabPage lp;
	private int movementSP = 3;
	private BattleManager bm = new BattleManager();
	private StartPage stp;

	private BattlePage bp;
	private BattleSkillPage bsp;
	private int ctn = 0;
	private String text;

	private boolean keyUp = false;
	private boolean keyDown = false;
	private boolean keyLeft = false;
	private boolean keyRight = false; 
	private boolean playerMove = false;
	private boolean sprint = false;
	private boolean cantmove = false;
	public boolean isCantmove() {
		return cantmove;
	}

	public void setCantmove(boolean cantmove) {
		this.cantmove = cantmove;
	}

	private boolean dialogOn = false;
	private boolean candial = false;

	private String dialogstr = "";

	private Toolkit tk = Toolkit.getDefaultToolkit();

	private Image img = new ImageIcon("images/userF.PNG").getImage();
	private Image gym = new ImageIcon("images/gym.PNG").getImage();
	private Image vill = new ImageIcon("images/main.PNG").getImage();
	private Image lab = new ImageIcon("images/lab.png").getImage();
	private Image center = new ImageIcon("images/Center.png").getImage();
	private Image huntfield = new ImageIcon("images/HuntFieldPage.png").getImage();
	private Image h_fire = new ImageIcon("images/fireField.png").getImage();
	private Image h_water = new ImageIcon("images/waterField.png").getImage();
	private Image h_jungle = new ImageIcon("images/jungleField.png").getImage();
	private Image mainPage = new ImageIcon("images/mainPage.gif").getImage();
	private Image pvp = new ImageIcon("images/Pvp.png").getImage();//SM_�߰�//���� �� pvp����
	private Image dialog = new ImageIcon("images/dialog.png").getImage(); // ��ȭâ �̹���

	//���� �̹��� �̸��� �ٷ� rpg.png�Դϴ�. �̹����� �ҷ��ɴϴ�
	private Image buffimg;// ������۸��� �Դϴ�.
	private Graphics gc;

	private Thread th;

	private int x, y; // �ɸ����� ���� ��ǥ�� ���� ����
	private int pWidth = 31, pHeight = 32;

	private int nx, ny; //npc ��ǥ

	private int cnt; //���� ������ ī���� �ϱ� ���� ����
	private int moveStatus; //�ɸ��Ͱ� ��� �ٶ󺸴��� ������ ���� ����
	private int num = 0;
	private boolean onOff;
	private int centernum;
	private int oh;
	private int ohOn = 0;
//	Music m_vill = new Music("village.mp3", false);
	




	private User user;
	int escCtn=0;//SM_�߰�

	public Map(MainFrame mf, User user) {
		this.user = user;
		System.out.println("�� Ŭ���� ����...");

		this.mf = mf;
		this.m = this;
		this.ump = new UserMenuPage(mf, m,user);
		/*      this.stp = new StartPage(mf);*/
		this.bp = new BattlePage(mf, m,user);   //BattlePage �߰�

		this.marketView=new MarketView(mf,m,user);//SM_�߰�
		this.centerView = new CenterView(mf, m,user);
		this.lp = new LabPage(mf, this, user); 
		lp.setVisible(false);

		onOff = true;

		this.setVisible(true);
		this.setSize(1024,768);
		this.setBounds(0,0,1024,768);
		init();
		
//		m_vill.start();


		start();

		Dimension screen = tk.getScreenSize();

		int xpos = (int)(screen.getWidth() / 2 - getWidth() / 2);
		int ypos = (int)(screen.getHeight() / 2 - getHeight() / 2);
		setLocation(xpos, ypos);

		mf.add(this);

	}

	public void init(){
		x = 500;
		y = 600;

		moveStatus = 2;
		//�ɸ��Ͱ� �����Ҷ� �ٶ󺸴� ������ �Ʒ����Դϴ�.
		// 0 : ����, 1 : ������, 2 : �Ʒ���, 3 : ����

	}

	public void start(){ // �⺻���� ���ó��
		System.out.println("��ŸƮ");
		mf.addKeyListener(this);
		th = new Thread(this);
		th.start();

	}

	public void run(){ // ������ �޼ҵ�, ���� ����
		while(true){
			try{
				keyProcess();
				repaint();
				collision();
				//serverInput();
				Thread.sleep(20);
				cnt++;

				/*if(!m.isVisible()) {
               while(this.isVisible() == false) {
                  th.wait();
               }
            }*/


			}catch(Exception e){
				return;
			}
		}

	}

	public void paint(Graphics g) { //������۸��� ����մϴ�.
		buffimg = createImage(1024, 768);
		gc = buffimg.getGraphics();
		update(g);
	}

	public void update(Graphics g) {
		//���� ���۸��� �̿��� ���ۿ� �׷������� �����ɴϴ�.
		DrawImg();
		g.drawImage(buffimg, 0, 0, this);

	}

	/*private void DrawDialog(String text) {
      //��ȭâ ���� �޼ҵ�
      gc.drawImage(dialog, 0, 600, 1024, 200, this);
      gc.drawString(text, 400, 650);
   }
	 */
	public void DrawImg() {
		gc.setFont(new Font("Default", Font.BOLD, 20));
		gc.drawString(Integer.toString(cnt), 50, 50);
		if(cnt%50 == 0) {
			user.setScd(user.getScd() + 1);
		}
		
		gc.drawString(Integer.toString((playerMove)?1:0),200, 50);

		switch(num) {
		case 0 : {
			gc.drawImage(vill, 0, 0, 1024, 768, this);  
			if(dialogOn == true) {
				gc.setFont(new Font("����ü", Font.BOLD, 30));
				gc.setColor(Color.white);
				gc.drawString(dialogstr, nx, ny);
			}
			break;
		}
		case 1 : {
			gc.drawImage(center, 0, 0, 1024, 768, this); 
			if(dialogOn == true) {
				/*gc.setFont(new Font("����ü", Font.BOLD, 30));
				gc.setColor(Color.white);
				gc.drawString(dialogstr, nx, ny);*/
				switch(centernum) {
				case 1 :{
					escCtn=1;
					System.out.println("���� �̿�");
					y += 50;
					//this.market = new Market(mf,m);
					m.setVisible(false);
					mf.add(marketView);
					marketView.setVisible(true);
					dialogOn = false;
					candial = false;
					break;
				}
				case 2 :{
					escCtn=1;
					System.out.println("���� �̿�");
					y += 50;
					//this.market = new Market(mf,m);
					m.setVisible(false);
					mf.add(centerView);
					centerView.setVisible(true);
					dialogOn = false;
					candial = false;
					break;
				}
				}

			}

			break;
		}
		case 2 : gc.drawImage(pvp, 0, 0, 1024, 768, this); break;//SM_�߰�
		case 3 : gc.drawImage(lab, 0, 0, 1024, 768, this); {
			if(dialogOn == true) {
				/*gc.setFont(new Font("����ü", Font.BOLD, 30));
				gc.setColor(Color.white);
				gc.drawString(dialogstr, nx, ny);*/
				switch(oh) {
				case 1 :{
					//this.market = new Market(mf,m);
					if(ohOn == 0) {
						escCtn=1;
						System.out.println("��Ÿ�� ���ϸ�");
						y += 50;
						m.setVisible(false);
						mf.add(lp);
						lp.setVisible(true);
						dialogOn = false;
						candial = false;
						ohOn = 1;
					}
					break;
				}
				}
			}
			break;
		}


		case 4 : gc.drawImage(gym, 0, 0, 1024, 768, this); break;
		case 5 : gc.drawImage(huntfield, 0, 0, 1024, 768, this); break;
		case 6 : gc.drawImage(h_fire, 0, 0, 1024, 768, this); break;
		case 7 : gc.drawImage(h_water, 0, 0, 1024, 768, this); break;
		case 8 : gc.drawImage(h_jungle, 0, 0, 1024, 768, this); break;
		case 99 : gc.drawImage(mainPage, 0, 0, 1000, 720, this);
		gc.setFont(new Font("����ü", Font.BOLD, 20));
		gc.drawString("������ �����Ϸ��� EnterŰ�� ��������!", 280, 300);break;


		}

		//���� �ܼ��� ���ѷ��� ���뿩�ο� �ɸ��� ���� üũ�� ���� 
		//������ ���鼭 �׽�Ʈ�� �뵵�� ���̴� �ؽ�Ʈ ǥ���Դϴ�.

		MoveImage(img, x, y, pWidth, pHeight);
		//�ɸ��͸� �ɾ�� ����� ���� �߰��� ���� �޼ҵ� �Դϴ�.
	}

	public void MoveImage(Image img, int x, int y, int width, int height) {
		//�ɸ��� �̹���, �ɸ��� ��ġ, �ɸ��� ũ�⸦ �޽��ϴ�. 
		//���� ���� �̿��ؼ� ���� �̹���Ĩ�¿��� �ɸ��͸� �߶�
		//ǥ���ϵ��� ����ϴ� �޼ҵ� �Դϴ�.

		gc.setClip(x  , y, width, height);
		//���� ��ǥ���� �ɸ����� ũ�� ��ŭ �̹����� �߶� �׸��ϴ�.

		if( playerMove ){ // �ɸ����� ������ ���θ� �Ǵ��մϴ�.
			if ( cnt / 10 % 4 == 0 ){ gc.drawImage(img,
					x - ( width * 0 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 1){ gc.drawImage(img,
					x - ( width * 1 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 2){  gc.drawImage(img,
					x - ( width * 2 ), y - ( height * moveStatus ), this);

			}else  if(cnt/10%4 == 3){ gc.drawImage(img,
					x - ( width * 1 ), y - ( height * moveStatus ), this);
			}
			//�ɸ����� ���⿡ ���� �ɾ�� ����� ���ϴ� 
			//�ɸ��� �̹����� �ð����� �̿��� ���������� �׸��ϴ�.

		}else {gc.drawImage(img, x - ( width * 1 ), 
				y - ( height * moveStatus ), this); 
		//�ɸ��Ͱ� �������� ������ ������ �ɸ��͸� �׸��ϴ�.
		}
	}

	public void keyProcess(){
		//���⼭�� �ܼ� �ɸ��Ͱ� �̵��ϴ� ��ǥ ����
		//�ɸ����� ������ ���ι� ������ üũ �մϴ�.
		playerMove = false;

		if (cantmove == false && keyUp && y > -10 && keyDown == false){
			playerMove = true;
			y -= movementSP;
			moveStatus = 0;
		}

		if (cantmove == false && keyDown && y < 690){
			y += movementSP;
			moveStatus = 2;
			playerMove = true;
		}

		if (cantmove == false && keyLeft && x > -20 && keyDown == false && keyUp == false){
			x -= movementSP;

			moveStatus = 3;
			playerMove = true;
		}

		if (cantmove == false && keyRight && x < 980 && keyDown == false && keyUp == false){
			x += movementSP;
			moveStatus = 1;
			playerMove = true;

		}

		if (sprint) {
			movementSP = 6;
		}
	}

	public void keyPressed(KeyEvent e) {
		this.bp = new BattlePage(mf, m,user);

		if(escCtn==0&&e.getKeyCode() == 27) { //SM_�߰�//�������� Market���� ���� �߻�
			System.out.println("esc ���� = �����޴�");

			m.setVisible(false);
			mf.add(ump);
			ump.setVisible(true);

		}


		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT :
			keyLeft = true;
			break;
		case KeyEvent.VK_RIGHT :
			keyRight = true;
			break;
		case KeyEvent.VK_UP :
			keyUp = true;
			break;
		case KeyEvent.VK_DOWN :
			keyDown = true;
			break;
		case KeyEvent.VK_D : 
			System.out.println("x : " + x + " y : " + y + " num : " + num);
			break;
		case KeyEvent.VK_ENTER :
			if(num == 99) {
				/*m.setVisible(false);
            mf.add(stp);
            stp.setVisible(true);
            run();*/
				num = 0;
				break;
			}

			if(escCtn==0 && candial == true && dialogOn == false) {
				dialogOn = true;
				System.out.println("���� ����" + dialogOn);
			}else if(dialogOn == true) {
				dialogOn = false;
				System.out.println("���� �ѹ��� ����");
			}
			break;

		case KeyEvent.VK_SHIFT :
			sprint = true;
			break;
		}

		//--------------------------------------
		//ü����
		if( num == 4 && (x > 390 && x<440) && (y>670)) {
//			m_vill.stop();
//			m_vill = new Music("village.mp3", false);
//			m_vill.start();
			num =0;
			x= 488;
			y = 150;
		}
		//����_ü������
		if( num ==0 &&(x >480 && x <510) && (y<148)) {
//			m_vill.stop();
//			m_vill = new Music("gym.mp3", false);
//			m_vill.start();
			num =4;
			x = 430;
			y=670;
		}
		//--------------------------------------
		//������
		if( num == 3 && (x > 500 && x<550) && (y>670)) {
//			m_vill.stop();
//			m_vill = new Music("village.mp3", false);
//			m_vill.start();
			num =0;
			x= 180;
			y = 140;
		}
		//����_��������
		if( num ==0 &&(x > 170 && x < 200) && (y<140)){
//			m_vill.stop();
//			m_vill = new Music("lab.mp3", false);
//			m_vill.start();
			num =3;
			x = 525;
			y=670; 
		}
		//--------------------------------------
		//����
		if( num == 1 && (x > 450 && x<500) && (y>670)) {
//			m_vill.stop();
//			m_vill = new Music("village.mp3", false);
//			m_vill.start();
			num =0;
			x= 765;
			y = 610;
		}
		//����_������
		if( num == 0 && (x > 750 && x< 780) && (y<610 && y>550)) {
//			m_vill.stop();
//			m_vill = new Music("center.mp3", false);
//			m_vill.start();
			num =1;
			x= 475;
			y = 670;   
		}
		//���Ϳ��� pvp���Ƿ�
		if(num == 1 && (x>415 &&x<520)&&(y<10)) {
			num=2;
			x=480;
			y=650;
		}
		/*//���Ϳ��� ���� �̿��ϱ�
		if(num == 1 && (x>190&&x<250)&&(y>350&&y<375)){
			escCtn=1;
			System.out.println("���� �̿�");
			//this.market = new Market(mf,m);
			m.setVisible(false);
			mf.add(marketView);
			marketView.setVisible(true);


			//ChangePanel.changePanel(mf, this, new Market(mf,m));
		}*/
		//pvp���ǿ��� ���ͷ�
		if(num==2) {
			if((x>450&&x<500)&&(y>670)){
				num=1;
				x=480;
				y=30;
			}
		}       


		//--------------------------------------
		//�����
		if(num == 5 && (x > 460 && x < 560) && (y < 0)) {
			
			num = 0;
			x = 490;
			y = 670;
		}
		//����_�������
		if(num == 0 && (x > 450 && x < 520) && (y > 670)) {
			num = 5;
			x = 510;
			y = 0;
		}
		//�����_��
		if(num == 5 && (x < 0) && (y > 260 & y < 410)) {
//			m_vill.stop();
//			m_vill = new Music("hunt.mp3", false);
//			m_vill.start();
			num = 6;
			x = 510;
			y = 0;
		}
		//�һ���� ����
		if(num == 6 && (x > 460 && x < 560) && (y < 0)) {
//			m_vill.stop();
//			m_vill = new Music("village.mp3", false);
//			m_vill.start();
			num = 5;
			x = 10;
			y = 335;
		}
		//�����_��
		if(num == 5 && (x > 979) && (y > 260 & y < 410)) {
//			m_vill.stop();
//			m_vill = new Music("hunt.mp3", false);
//			m_vill.start();
			num = 7;
			x = 510;
			y = 0;
		}
		//������� ����
		if(num == 7 && (x > 400 && x < 510) && (y < 0)) {
//			m_vill.stop();
//			m_vill = new Music("village.mp3", false);
//			m_vill.start();
			num = 5;
			x = 979;
			y = 335;
		}
		//�����_Ǯ
		if(num == 5 && (x > 460 && x < 560) && (y > 680)) {
//			m_vill.stop();
//			m_vill = new Music("hunt.mp3", false);
//			m_vill.start();
			num = 8;
			x = 510;
			y = 0;
		}
		//Ǯ����� ����
		if(num == 8 && (x > 450 && x < 580) && (y < 0)) {
//			m_vill.stop();
//			m_vill = new Music("village.mp3", false);
//			m_vill.start();
			num = 5;
			x = 510;
			y = 670;
		}
		//--------------------------------------
	}


	//---------------------------�̵��Ұ� ������Ʈ ����----------------------------------
	void collision()
	{
		Rectangle rect = new Rectangle(x, y, pWidth, pHeight);//�÷��̾� ũ��
		//���� ������Ʈ------------------------------------------------
		if(num == 0) {
			Rectangle building1 = new Rectangle(300, 430, 90, 135);//�ǹ�ũ��
			if(rect.intersects(building1)){canMove();}//�浹�˻�
			Rectangle buildingLABGYM = new Rectangle(60, -12, 580, 145);//������,ü����
			if(rect.intersects(buildingLABGYM)){canMove();}
			Rectangle buildingyard = new Rectangle(220, 140, 260, 70);
			if(rect.intersects(buildingyard)){canMove();}
			Rectangle buildingyard2 = new Rectangle(530, 140, 320, 70);
			if(rect.intersects(buildingyard2)){canMove();}
			Rectangle buildingyard3 = new Rectangle(630, 200, 200, 80);
			if(rect.intersects(buildingyard3)){canMove();}
			Rectangle buildingcenter = new Rectangle(630, 350, 300, 250);
			if(rect.intersects(buildingcenter)){canMove();}
			Rectangle buildingcenterfen1 = new Rectangle(810, 600, 120, 40);
			if(rect.intersects(buildingcenterfen1)){canMove();}
			Rectangle buildingcenterfen2 = new Rectangle(560, 600, 200, 40);
			if(rect.intersects(buildingcenterfen2)){canMove();}
			Rectangle buildingcenterfen3 = new Rectangle(560, 460, 90, 150);
			if(rect.intersects(buildingcenterfen3)){canMove();}
			Rectangle bunsu = new Rectangle(470, 310, 60, 60);
			if(rect.intersects(bunsu)){canMove();}
			Rectangle gapan = new Rectangle(120, 350, 60, 60);
			if(rect.intersects(gapan)){canMove();}
			Rectangle lamp = new Rectangle(495, 390, 20, 60);
			if(rect.intersects(lamp)){canMove();}
			Rectangle post = new Rectangle(440, 495, 10, 20);
			if(rect.intersects(post)){canMove();}

			Rectangle forest1 = new Rectangle(0, 0, 50, 768);
			if(rect.intersects(forest1)){canMove();}
			Rectangle forest2 = new Rectangle(150, 690, 300, 30);
			if(rect.intersects(forest2)){canMove();}
			Rectangle forest3 = new Rectangle(560, 690, 500, 30);
			if(rect.intersects(forest3)){canMove();}
			Rectangle forest4 = new Rectangle(990, 200, 5, 600);
			if(rect.intersects(forest4)){canMove();}
			Rectangle lake = new Rectangle(100, 470, 160, 120);
			if(rect.intersects(lake)){canMove();}
			Rectangle lake2 = new Rectangle(60, 580, 110, 40);
			if(rect.intersects(lake2)){canMove();}
			Rectangle lake3 = new Rectangle(140, 640, 1, 40);
			if(rect.intersects(lake3)){canMove();}
			Rectangle pump = new Rectangle(550, 270, 20, 20);
			if(rect.intersects(pump)){canMove();}
			Rectangle flower = new Rectangle(900, 183, 100, 25);
			if(rect.intersects(flower)){canMove();}

			Rectangle house = new Rectangle(860, 130, 30, 30);
			if(rect.intersects(house)){
				int result = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}else {
					y += 10;
					keyUp = false;
				}
			}//�浹�˻�
			/*Rectangle postevent = new Rectangle(420, 480, 40, 30);
			if(rect.intersects(postevent)){
				if(rect.intersects(postevent)){
					candial = true;
					dialogstr = "������ ��ü���̴�.";
					nx = (int) postevent.getX() - 50;
					ny = (int) postevent.getY() - 50;
				}else {
					System.out.println("����");
					candial = false;
					dialogOn = false;
				}
			}*/
		}
		//--------------------------------------------------------
		//�װ����� ������Ʈ------------------------------------------------
		if(num == 5) {
			Rectangle h_lamp = new Rectangle(515, 290, 25, 80);
			if(rect.intersects(h_lamp)){canMove();}
			Rectangle h_forest1 = new Rectangle(590, -12, 400, 65);
			if(rect.intersects(h_forest1)){canMove();}
			Rectangle h_forest2 = new Rectangle(0, -12, 460, 65);
			if(rect.intersects(h_forest2)){canMove();}
			Rectangle h_forest3 = new Rectangle(-21, 54, 40, 210);
			if(rect.intersects(h_forest3)){canMove();}
			Rectangle h_forest4 = new Rectangle(-21, 440, 40, 210);
			if(rect.intersects(h_forest4)){canMove();}
			Rectangle h_forest5 = new Rectangle(21, 645, 440, 65);
			if(rect.intersects(h_forest5)){canMove();}
			Rectangle h_forest6 = new Rectangle(585, 645, 440, 65);
			if(rect.intersects(h_forest6)){canMove();}
		}
		//���� ������Ʈ--------------------------------------------------
		if(num == 1) {
			Rectangle c_left = new Rectangle(0, 0, 420, 340);
			if(rect.intersects(c_left)){canMove();}
			Rectangle c_under = new Rectangle(0, 655, 450, 340);
			if(rect.intersects(c_under)){canMove();}
			Rectangle c_wallleft = new Rectangle(0, 0, 65, 740);
			if(rect.intersects(c_wallleft)){canMove();}
			Rectangle c_wallleft1 = new Rectangle(67, 430, 120, 140);
			if(rect.intersects(c_wallleft1)){canMove();}
			Rectangle c_wallleft2 = new Rectangle(280, 430, 140, 140);
			if(rect.intersects(c_wallleft2)){canMove();}
			Rectangle c_wallleft3 = new Rectangle(370, 340, 50, 140);
			if(rect.intersects(c_wallleft3)){canMove();}

			Rectangle c_right = new Rectangle(550, 0, 420, 340);
			if(rect.intersects(c_right)){canMove();}
			Rectangle c_under2 = new Rectangle(520, 655, 450, 340);
			if(rect.intersects(c_under2)){canMove();}
			Rectangle c_wallright = new Rectangle(940, 0, 65, 740);
			if(rect.intersects(c_wallright)){canMove();}
			Rectangle c_wallright1 = new Rectangle(790, 430, 120, 140);
			if(rect.intersects(c_wallright1)){canMove();}
			Rectangle c_wallright2 = new Rectangle(550, 430, 140, 140);
			if(rect.intersects(c_wallright2)){canMove();}
			Rectangle c_wallright3 = new Rectangle(550, 340, 50, 140);
			if(rect.intersects(c_wallright3)){canMove();}

			Rectangle c_test = new Rectangle(600, 340, 30, 30);
			/*if(rect.intersects(c_test)){
				candial = true;
				dialogstr = "�ȳ��ϼ���! ������ ���͵帱���?";
				nx = (int) c_test.getX() - 100;
				ny = (int) c_test.getY() - 50;
			}else {
				candial = false;
				dialogOn = false;
			}*/
			Rectangle c_test3 = new Rectangle(700, 340, 300, 30);
			if(moveStatus == 0 && rect.intersects(c_test3)){
				candial = true;
				centernum = 2;
			}
			Rectangle c_test2 = new Rectangle(0, 340, 300, 30);
			if(moveStatus == 0 && rect.intersects(c_test2)){
				candial = true;
				centernum = 1;
			}
		}

		//������ ������Ʈ ----------------------------------
		if(num == 3) {
			Rectangle docOh = new Rectangle(100, 450, 150, 80);
			if(moveStatus == 0 && rect.intersects(docOh)) {
				candial = true;
				oh = 1;
			}
			Rectangle l_wall = new Rectangle(50, 30, 670, 420);
			if(rect.intersects(l_wall)){canMove();}
			Rectangle l_wall2 = new Rectangle(250, 455, 470, 120);
			if(rect.intersects(l_wall2)){canMove();}
			Rectangle l_wall3 = new Rectangle(50, 451, 50, 220);
			if(rect.intersects(l_wall3)){canMove();}
			Rectangle l_wall4 = new Rectangle(50, 690, 450, 180);
			if(rect.intersects(l_wall4)){canMove();}
			Rectangle l_wall5 = new Rectangle(570, 690, 440, 180);
			if(rect.intersects(l_wall5)){canMove();}
		}



		//ü���� ��� ���� �ʿ�, �ӽ���------------------------------------------------
		if(num == 4) {
			Rectangle gym1 = new Rectangle(25, 640, 375, 40);
			if(rect.intersects(gym1)){canMove();}
			Rectangle gym2 = new Rectangle(5, 405, 5, 200);
			if(rect.intersects(gym2)){canMove();}
			Rectangle gym3 = new Rectangle(100, 525, 360, 50);
			if(rect.intersects(gym3)){canMove();}
			Rectangle gym4 = new Rectangle(480, 525, 520, 185);
			if(rect.intersects(gym4)){canMove();}
			Rectangle gym5 = new Rectangle(5, 385, 905, 70);
			if(rect.intersects(gym5)){canMove();}
			Rectangle gym6 = new Rectangle(100, 260, 900, 60);
			if(rect.intersects(gym6)){canMove();}
			Rectangle gym7 = new Rectangle(5, 125, 385, 65);
			if(rect.intersects(gym7)){canMove();}
			Rectangle gym8 = new Rectangle(510, -10, 390, 250);
			if(rect.intersects(gym8)){canMove();}

		}


		//Ǯ ����� ���-----------------------------------------------------
		if(num == 8) {
			Rectangle grasswater1 = new Rectangle(670, 235, 140, 55);//������,ü����
			if(rect.intersects(grasswater1)){canMove();}
			Rectangle grasswater2 = new Rectangle(160, 290, 140, 90);//������,ü����
			if(rect.intersects(grasswater2)){canMove();}
			Rectangle grasswall1 = new Rectangle(40, -15, 260, 180);//������,ü����
			if(rect.intersects(grasswall1)){canMove();}
			Rectangle grasswall2 = new Rectangle(745, -5, 260, 205);//������,ü����
			if(rect.intersects(grasswall2)){canMove();}

		}






		//---------------------------------------�� ����� ��ǥ�� 1
		if(num == 7 && playerMove == true) {
			Rectangle h_wrange = new Rectangle(25, 340, 900, 330);
			if(rect.intersects(h_wrange)){
				int hrand = new Random().nextInt(100);
				if(hrand == 30) {
//					m_vill.stop();
//					m_vill = new Music("battle.mp3", false);
//					m_vill.start();
					
					bm.randomP(user,this.num);
					cantmove = true;
					System.out.println("��Ʋ������");
					mf.add(bp);
					m.setVisible(false);
					//�̹����� �ʰԶߴ��� ��ġ���� �ؾ���

					bp.setVisible(true);

				}
			}
		}
		//------------------------------------------�� ����� ��ǥ�� 1
		if(num == 6 && playerMove == true) {
			Rectangle h_wrange = new Rectangle(60, 267, 840, 300);
			if(rect.intersects(h_wrange)){
				int hrand = new Random().nextInt(100);
				if(hrand == 30) {
//					m_vill.stop();
//					m_vill = new Music("battle.mp3", false);
//					m_vill.start();
					
					bm.randomP(user,this.num);
					cantmove = true;
					System.out.println("��Ʋ������");
					m.setVisible(false);
					mf.add(bp);
					bp.setVisible(true);
					cantmove = false;

				}
			}
		}
		//Ǯ ����� ��ǥ�� 1-----------------------------------------------
		if(num == 8 && playerMove == true) {
			Rectangle h_wrange1 = new Rectangle(135, 420, 765, 280);
			if(rect.intersects(h_wrange1)){
				int hrand = new Random().nextInt(100);
				if(hrand == 30) {
//					m_vill.stop();
//					m_vill = new Music("battle.mp3", false);
//					m_vill.start();
					
					bm.randomP(user,this.num);
					cantmove = true;
					System.out.println("��Ʋ������");
					m.setVisible(false);
					mf.add(bp);
					bp.setVisible(true);
					cantmove = false;
				}
			}

			Rectangle h_wrange2 = new Rectangle(130, 180, 590, 160);
			if(rect.intersects(h_wrange2)){
				int hrand = new Random().nextInt(100);
				if(hrand == 30) {
//					m_vill.stop();
//					m_vill = new Music("battle.mp3", false);
//					m_vill.start();
					
					bm.randomP(user,this.num);
					cantmove = true;
					System.out.println("��Ʋ������");
					m.setVisible(false);
					mf.add(bp);
					bp.setVisible(true);
					cantmove = false;
				}
			}
		}
		//ü���� ��Ʋ ù��°---------------------------------------------
		int ctn2 = 0;
		if(num == 4 && (ctn2 == ctn - 1)&& (x > 24 && x < 75) && (y > 200 && y < 240)){
			//x = 50;
			//y = 245;
			ctn += 1 ;

			System.out.println("Npc��Ʋ������");
			m.setVisible(false);
			mf.add(bp);
			bp.setVisible(true);
			run();
		}

		//ü���� ��Ʋ �ι�°
		if(num == 4 &&(ctn == 0)&& (x > 20 && x < 76) && (y > 580 && y < 615)){
			//x = 80;
			//y = 615;
			ctn += 1;

			System.out.println("Npc��Ʋ������");
			m.setVisible(false);
			mf.add(bp);
			bp.setVisible(true);
			run();
		}
		//����� ��Ʋ �߰��ؾ� ��




	}

//	public Music getM_vill() {
//		return m_vill;
//	}
//
//	public void setM_vill(Music m_vill) {
//		this.m_vill = m_vill;
//	}

	public void canMove() {
		if(moveStatus == 0) {
			y += movementSP;
		}else if(moveStatus == 2) {
			y -= movementSP;
		}else if(moveStatus == 3) {
			x += movementSP;
		}else if(moveStatus == 1) {
			x -= movementSP;
		}
	}

	//SM_�߰�
	//escCtn �ʵ� ������ �߰� Market���� escCtn���� �����ϱ� ���� ���   
	public int getEscCtn() {
		return escCtn;
	}

	public void setEscCtn(int escCtn) {
		this.escCtn = escCtn;
	}


	//battlepage get/set
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}



	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT :
			keyLeft = false;
			break;
		case KeyEvent.VK_RIGHT :
			keyRight = false;
			break;
		case KeyEvent.VK_UP :
			keyUp = false;
			break;
		case KeyEvent.VK_DOWN :
			keyDown = false;
			break;
		case KeyEvent.VK_SHIFT :
			sprint = false;
			movementSP = 3;
			break;

		}
	}

	public void keyTyped(KeyEvent e) {}

	public void setCantMove(boolean b) {
		this.cantmove = b;
	}

	public void serverInput() {
		try {
			String serverIP = "192.168.130.96";

			Socket socket = new Socket(serverIP,port);

			//	2. �������� ����� ��Ʈ���� �����Ѵ�.
			if(socket != null) {
				InputStream input = socket.getInputStream();
				OutputStream output = socket.getOutputStream();

				//	3. ������Ʈ���� �ٿ� ������ �����Ѵ�.
				BufferedReader br = new BufferedReader(new InputStreamReader(input));
				PrintWriter pw = new PrintWriter(output);
				//	4. ��Ʈ���� ���� �а� ���⸦ �Ѵ�.
				Scanner sc = new Scanner(System.in);

				//do {
				/*System.out.println("��ȭ �Է�: ");
					String message = sc.nextLine();*/
				int netx = x;
				int nety = y;
				pw.println("x : " + x + " , y : " + y);
				pw.flush();

				/*if(message.equals("exit")) {
						break;
					}
				 */
				String recieveMessage = br.readLine();
				System.out.println(recieveMessage);
				sc.nextLine();					


				//}while(true);

				//	5. ����� �����Ѵ�.
				//br.close();
				//pw.close();
				//socket.close();


			}



		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}