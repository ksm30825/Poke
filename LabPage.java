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
		
		//���ϸ� ���� �� �ڻ� ��ȭâ
		String[] talkList = new String[4];
		talkList[0] = "�ȳ�! " + user.getuName() + ". ȯ���ϳ�.";
		talkList[1] = "���� �����ϴϱ� ���ϸ� �� ������ ���������� �ϰ�.";
		talkList[2] = "���ϸ��� �� �������ϼ�. ���ϸ��� ���� �ٽ� �ҷ��ְ�.";
		talkList[3] = "(�ڻ�� ����� ��ٸ��� �ִ�.)";
		
		//���ϸ� ���� �� �ڻ� ��ȭâ
		String[] talkList2 = new String[2];
		talkList2[0] = "���� ���ϸ��� �����! ������ �� Ű���ֱ� �ٶ��.";
		talkList2[1] = "���� ���� ������ �������� ����. ���� �濡 ������ �鷯���� ������.";
		
	    JTextField tTf = new JTextField(40);
	    tTf.setOpaque(false);
	    JLabel temp = new JLabel(new ImageIcon("images/dial.png"));
	    temp.setBounds(60, 535, 900, 150);
	    temp.setLayout(new BorderLayout());
	    tTf.setText(talkList[0]);
	    tTf.setBounds(60, 535, 900, 150);
	    tTf.setEditable(false);
	    tTf.setHorizontalAlignment(JTextField.CENTER);
	    tTf.setFont(new Font("����ü", Font.BOLD, 25));
	   // tTf.setForeground(Color.white);
	   // tTf.setBackground(Color.black);
	    temp.add(tTf);
	    
	    
	    //��Ÿ�� ���ϸ� ����
	    int pNo = 0;
	    Pokemon[] poke = new Pokemon[3];
	    for(int i = 0; i < poke.length; i++) {
	    	poke[i] = pd.getpList().get(pNo);
	    	pNo += 3;
	    }
	    
	    //��Ÿ�� ���ϸ� �̹��� = �̻��ؾ�(1), ���̸�(4), ���α�(7)
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
	    
	    
	    //�ڼ��ý� set�ΰ� add�� �����ؾ���.
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
					result = JOptionPane.showConfirmDialog(null, poke[0].getpName() + "�� �������ðڽ��ϱ�?", "��Ÿ�� ���ϸ�",JOptionPane.YES_NO_OPTION);
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
					result = JOptionPane.showConfirmDialog(null, poke[1].getpName() + "�� �������ðڽ��ϱ�?", "��Ÿ�� ���ϸ�",JOptionPane.YES_NO_OPTION);
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
					result = JOptionPane.showConfirmDialog(null, poke[2].getpName() + "�� �������ðڽ��ϱ�?", "��Ÿ�� ���ϸ�",JOptionPane.YES_NO_OPTION);
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
			
			//��ȭâ�� Ŭ���ϸ� ��簡 �Ѿ
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
		
		//�ڷΰ��� ��ư(�ӽ�)
		JButton back = new JButton("�ڷ�");
		
		back.setBounds(900, 200, 90, 120);
		back.setBorderPainted(false);
		//back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		//�ڷΰ��� ��ư
		back.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				//Ŭ���� �̺�Ʈ
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
