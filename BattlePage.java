package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BattleManager;
import model.dao.PokemonDao;
import model.vo.User;
import music.Music;


public class BattlePage extends JPanel {
	//0217-01
	//0217-01  private JPanel bp;
	private BattlePage bp;
	private MainFrame mf;
	private Map m;
	private BattleSkillPage bsp;
	private PInfoPage pip;
	//0217-01 private JPanel uivp;
	private UserInvenPage uivp;
	private User user;
	private BattleManager bm = new BattleManager();
	private PokemonDao pd = new PokemonDao();

	private JLabel enPLabel;
	private JLabel myPLabel;
	private ImageIcon myPIcon;
	private ImageIcon enPIcon;
	private JLabel myHP = new JLabel();
	private JLabel enHP = new JLabel();

	//��ư �̹��� �ø���
	private Image fightButtonImage = new ImageIcon("images/�ο�ٹ�ư.png").getImage();
	private Image changeButtonImage = new ImageIcon("images/���ϸ��ư.png").getImage();
	private Image invenButtonImage = new ImageIcon("images/�����ư.png").getImage();
	private Image runButtonImage = new ImageIcon("images/�������ٹ�ư.png").getImage();
	private JButton mes = new JButton("����ĥ �� ����.  �ο���.");
	private Dialog blockrun = new Dialog(mf);



	//������ ���ư
	private Image backButtonImage = new ImageIcon("images/back.png").getImage();
	private JButton backButton = new JButton(new ImageIcon(backButtonImage));

	//�̹��� ��ưȭ
	private JButton fightButton = new JButton(new ImageIcon(fightButtonImage));
	private JButton changeButton = new JButton(new ImageIcon(changeButtonImage));
	private JButton invenButton = new JButton(new ImageIcon(invenButtonImage));
	private JButton runButton = new JButton(new ImageIcon(runButtonImage));

	private int mpnum;
	private int epnum;




	public Map getM() {
		return m;
	}


	public void setM(Map m) {
		this.m = m;
	}


	//Graphics
	//���� ��� �̹���
	Image background = Toolkit.getDefaultToolkit().getImage("images/battleViewImg/battleview.png");
	Image mground = Toolkit.getDefaultToolkit().getImage("images/battleViewImg/Ǯ�ʵ�1.png");
	Image eground = Toolkit.getDefaultToolkit().getImage("images/battleViewImg/Ǯ�ʵ�2.png");
	public JLabel getEnHP() {
		return enHP;
	}
	public void setEnHP(JLabel enHP) {
		this.enHP = enHP;
	}
	public JLabel getMyHP() {
		return myHP;
	}
	public void setMyHP(JLabel myHP) {
		this.myHP = myHP;
	}

	public BattlePage(MainFrame mf, JPanel panel, User user) {

		this.setSize(1024,768);
		this.bp = this;
		this.mf = mf;
		this.m = (Map)panel;
		//0217-01 this.pip = new PInfoPage(mf,this,user);
		//0217-01 this.bsp = new BattleSkillPage(mf, this,user);
		this.user = user;

		//��ư�� ����� ��ġ�ϱ� ����
		bp.setLayout(null);
		//this.pip = new PInfoPage(mf, this);

		myHP.setFont(new Font("����ü", Font.BOLD, 30));
		myHP.setBounds(830, 310, 50, 40);

		//this.add(myHP);
		this.setComponentZOrder(myHP, 0);


		enHP.setFont(new Font("����ü", Font.BOLD, 30));
		enHP.setBounds(760, 150, 100, 100);

		//this.add(enHP);
		this.setComponentZOrder(enHP, 0);

		fightButton.setBounds(180, 500, 300, 80);
		this.add(fightButton);
		settingButton(fightButton);
		fightButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				//0217-03 �ο�� ��ư Ŭ���� ��Ʋ��ų������ ������ �ѱ�
				if(user.getUp_list().get(0).getpHp() <= 0) {
					JPanel panel = bm.changeBP(mf, bp, pip, user);
					panel.setVisible(true);
					mf.add(panel);

				}else if(user.getUp_list().get(0).getpHp() >0){

					bsp = new BattleSkillPage(mf, bp,user);
					mf.add(bsp);
					bm.showS(bsp,user);
					bp.setVisible(false);
				}


				bp.setVisible(false);

			}
		});
		this.add(fightButton);


		changeButton.setBounds(550, 500, 300, 80);
		settingButton(changeButton);
		changeButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				bp.setVisible(false);
				//0217-01 mf.add(pip);
				mf.add(new PInfoPage(mf,bp,user)); //0217-01
			}
		});
		this.add(changeButton);



		invenButton.setBounds(180, 600, 300, 80);
		settingButton(invenButton);
		invenButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				bp.setVisible(false);
				//0217-01 uivp.setVisible(true);   
				//0217-01 mf.add(uivp);
				mf.add(new UserInvenPage(mf, bp, user,m,bm)); //0217-01

			}
		});
		this.add(invenButton);


		//m.setNum(4);
		if(m.getNum() == 4) {
			runButton.setBounds(550, 600, 300, 80);
			settingButton(runButton);
			runButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					blockrun.setBounds(600, 300, 200, 100 );
					blockrun.add(mes);

					blockrun.setVisible(true);

				}
			});
			this.add(runButton);      

			//���̾�α� �޼��� Ŭ���� �̺�Ʈ
			settingButton(mes);
			mes.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					blockrun.dispose();

				}
			});
			this.add(mes);      
		}else {
			runButton.setBounds(550, 600, 300, 80);
			settingButton(runButton);
			runButton.setBorderPainted(false);
			runButton.setFocusPainted(false);
			runButton.setContentAreaFilled(false);
			runButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					mf.remove(bp);
					m.setVisible(true);
					mf.requestFocus();
					m.setCantMove(false);
					m.getM_vill().stop();
					m.setM_vill(new Music("village.mp3",false));
					m.getM_vill().start();
					//((Map) m).start();
				}
			});
			this.add(runButton);
		}
		//������ �̺�Ʈ
		backButton.setBounds(780, 650, 300, 80);
		settingButton(backButton);
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				mf.remove(bp);
				m.setVisible(true);
				mf.requestFocus();
				m.setCantMove(false);
				//((Map) m).start();
			}
		});
		this.add(backButton);   
	}


	//�� ��� ���, ���ϸ� �ҷ����� �׷��Ƚ�
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 1012, 729, this);
		g.drawImage(mground, 150, 321, 280, 100, this);
		g.drawImage(eground, 670, 90, 250, 100, this);
		if(this.isVisible()) {
			mpnum = user.getUp_list().get(0).getpNo();
			Image mypokemon = Toolkit.getDefaultToolkit().getImage("images/poke/"+mpnum+"B.gif");
			g.drawImage(mypokemon, 235, 321, 90, 90, this);
			epnum = user.getEp_list().get(0).getpNo();
			Image enpokemon = Toolkit.getDefaultToolkit().getImage("images/poke/"+epnum+"F.gif");
			g.drawImage(enpokemon, 740, 60, 100, 100, this);

		}

		g.setColor(new Color(200,0,0));
		g.fillRect(874, 330, 120, 10);
		
		g.setColor(new Color(29,200,22));
		double num = (user.getUp_list().get(0).getpHp()/(double)user.getUp_list().get(0).getpMaxHp())*10;
		double fnum = 10-num;
		g.fillRect(874, 330, 120 -(int)(12*fnum), 10);

		Image myHp = Toolkit.getDefaultToolkit().getImage("images/battleViewImg/��HP��.png");
		g.drawImage(myHp, 710, 280, 300, 100, this);

		


		g.setColor(Color.black);
		g.setFont(new Font("����ü", Font.BOLD, 20));
		g.drawString(Integer.toString(user.getUp_list().get(0).getpHp()) + "    " + Integer.toString(user.getUp_list().get(0).getpMaxHp()), 875, 363);
		g.drawString(user.getUp_list().get(0).getpName(), 800, 320);
		g.drawString(Integer.toString(user.getUp_list().get(0).getpLevel()), 965, 320);

		//����



		if(this.isVisible()) {
			//����
			
			g.setColor(new Color(200,0,0));
			g.fillRect(125, 97, 120, 10);
			
			g.setColor(new Color(29,200,22));
			double ennum = (user.getEp_list().get(0).getpHp()/(double)user.getEp_list().get(0).getpMaxHp())*10;
			double enfnum = 10-ennum;
			g.fillRect(125, 97, 120 - (int)(12*enfnum), 10);

			Image enHp = Toolkit.getDefaultToolkit().getImage("images/battleViewImg/���HP��.png");
			g.drawImage(enHp, 0, 30, 300, 100, this);



			g.setColor(Color.black);
			g.setFont(new Font("����ü", Font.BOLD, 20));
			//g.drawString(Integer.toString(user.getEp_list().get(0).getpHp()) + "    " + Integer.toString(user.getEp_list().get(0).getpMaxHp()), 60, 74);
			g.drawString(user.getEp_list().get(0).getpName(), 60, 74);
			g.drawString(Integer.toString(user.getEp_list().get(0).getpLevel()), 230, 74);



		}

	}


	public void settingButton(JButton jb) {
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.setContentAreaFilled(false);
	}



}
