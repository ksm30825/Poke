package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.w3c.dom.views.AbstractView;

import controller.MCManager;
import model.dao.PokemonDao;
import model.vo.Pokemon;
import model.vo.User;

public class LabPage extends JPanel {
	
	private MainFrame mf;
	private LabPage lp;
	private JPanel oldPage;
	private Map m;
	private Image labBackground = new ImageIcon().getImage();
	private PokemonDao pd = new PokemonDao();
	   
	private User user;
	private int count = 1;
	private boolean select = false;
	
	
	public LabPage(MainFrame mf, JPanel oldPage, User user) {
		this.lp = this;
		this.mf = mf;
		this.m=(Map)oldPage;
		this.user = user;
		
		//포켓몬 선택 전 박사 대화창
		String[] talkList = new String[4];
		talkList[0] = "안녕! " + user.getuName() + ". 환영하네.";
		talkList[1] = "밖은 위험하니까 포켓몬 한 마리를 데려가도록 하게.";
		talkList[2] = "포켓몬은 총 세마리일세. 포켓몬을 고르면 다시 불러주게.";
		talkList[3] = "(박사는 당신을 기다리고 있다.)";
		
		//포켓몬 선택 후 박사 대화창
		String[] talkList2 = new String[2];
		talkList2[0] = "좋은 포켓몬을 골랐군! 앞으로 잘 키워주길 바라네.";
		talkList2[1] = "이제 마을 밖으로 나가봐도 좋네. 가는 길에 상점도 들러보면 좋겠지.";
		
	    JTextField tTf = new JTextField(40);
	    tTf.setOpaque(false);
	    JLabel temp = new JLabel(new ImageIcon("images/dial.png"));
	    temp.setBounds(60, 535, 900, 150);
	    temp.setLayout(new BorderLayout());
	    tTf.setText(talkList[0]);
	    tTf.setBounds(60, 535, 900, 150);
	    tTf.setEditable(false);
	    tTf.setHorizontalAlignment(JTextField.CENTER);
	    tTf.setFont(new Font("돋움체", Font.BOLD, 25));
	   // tTf.setForeground(Color.white);
	   // tTf.setBackground(Color.black);
	    temp.add(tTf);
	    
	    
	    //스타팅 포켓몬 정보
	    int pNo = 0;
	    Pokemon[] poke = new Pokemon[3];
	    for(int i = 0; i < poke.length; i++) {
	    	poke[i] = pd.getpList().get(pNo);
	    	pNo += 3;
	    }
	    
	    //스타팅 포켓몬 이미지 = 이상해씨(1), 파이리(4), 꼬부기(7)
	    ImageIcon[] pStartList = new ImageIcon[3];
	    JLabel[] pStart = new JLabel[pStartList.length];
	    int x=270;
	    int y=150;
	    int num = 1;
	    for(int i = 0; i < 3; i++) {
	    	  
	    pStartList[i] = new ImageIcon("images/poke/"+num+"F.gif");
	    pStart[i] = new JLabel(pStartList[i]);
	    pStart[i].setBounds(x, y, 175, 300);
	    x+=148;
	    num += 3;
	    this.add(pStart[i]);
	    
	    }
	    
	    
	    //★선택시 set인거 add로 변경해야함.
	    pStart[0].addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = 1;
				if(select == false) {
					result = JOptionPane.showConfirmDialog(null, poke[0].getpName() + "를 데려가시겠습니까?", "스타팅 포켓몬",JOptionPane.YES_NO_OPTION);
				}
				
				if(result == 0) {
					user.getUp_list().add(0, poke[0]);
					select = true;
					System.out.println(select);
				}
			}
		});
	    
	    pStart[1].addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = 1;
				if(select == false) {
					result = JOptionPane.showConfirmDialog(null, poke[1].getpName() + "를 데려가시겠습니까?", "스타팅 포켓몬",JOptionPane.YES_NO_OPTION);
				}
				
				if(result == 0) {
					user.getUp_list().set(0, poke[1]);
					select = true;
					System.out.println(select);
				}
				
			}
		});
	    
	    pStart[2].addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = 1;
				if(select == false) {
					result = JOptionPane.showConfirmDialog(null, poke[2].getpName() + "를 데려가시겠습니까?", "스타팅 포켓몬",JOptionPane.YES_NO_OPTION);
				}
				
				if(result == 0) {
					user.getUp_list().set(0, poke[2]);
					select = true;
					System.out.println(select);
				}
			}
		});
	      
	      tTf.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			//대화창을 클릭하면 대사가 넘어감
			@Override
			public void mouseClicked(MouseEvent e) {
				if(count < talkList.length) {
					tTf.setText(talkList[count]);
					count++;
				}
				
				if(select == true) {
					System.out.println(select);
					count = 0;
					if(count < talkList2.length) {
						tTf.remove(tTf);
						tTf.setText(talkList2[count]);
						count++;
					}
				}
			}
		});
		
		//뒤로가기 버튼(임시)
		JButton back = new JButton("뒤로");
		
		back.setBounds(900, 200, 90, 120);
		back.setBorderPainted(false);
		//back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		//뒤로가기 버튼
		back.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//클릭시 이벤트
				mf.remove(lp);
				m.setVisible(true);
				mf.requestFocus();
				m.setEscCtn(0);
			}
		});
		
		this.add(back);
		//this.add(tTf);
		this.add(temp);
				
		this.setBackground(Color.white);
		this.setVisible(true);
		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);
		mf.add(this);
	}

}
