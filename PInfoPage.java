package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.BattleManager;
import controller.ItemManager;
import model.vo.Pokemon;
import model.vo.User;

public class PInfoPage extends JPanel{
	//0217-01
	private MainFrame mf;
	private Map m;
	private JPanel pip;
	private UserMenuPage ump;
	private JPanel oldPage;
	private BattlePage bp;
	private User user;
	private BattleManager bm = new BattleManager();
	private Thread th;
	//private NpcBattlePage nbp;

	private JLabel[] pInfo = new JLabel[4];
	private JTextArea[] pInfoText = new JTextArea[4];
	private JButton backButton = new JButton(new ImageIcon("images/userMenuImages/backButtonBasic.png"));

	
	public PInfoPage(MainFrame mf, JPanel oldPage, User user) {

		this.mf = mf;
		this.pip = this;
		//this.ump = ump;
		this.oldPage = oldPage; //JPanel 로 받아 유저메뉴/배틀페이지  페이지 받기
		this.user = user;

		pip.setOpaque(false);
		pip.setBounds(0, 0, 1024, 768);
		JLabel j = new JLabel();
		for(int i=0; i < pInfo.length; i++) {
			pInfo[i] = new JLabel();
			pInfo[i] = new JLabel(new ImageIcon("images/userMenuImages/pInfo4.png"));
		}
		for(int i=0; i < pInfo.length; i++) {
			pInfoText[i] = new JTextArea();

			pInfoText[i].setBackground(new Color(0,0,0,0));
		}

		bm.showP((PInfoPage) pip, user);


		mf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == UserMenuPage.ESC) {
					mf.remove(pip);
					pip.setVisible(false);
				}
			}
		});






		JLabel label = new JLabel();
		label.setText("  ");

		pip.setBackground(Color.WHITE);
		pip.setLayout(null);
		backButton.setBounds(900, 610, 90, 120);
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);

		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(new ImageIcon("images/userMenuImages/backButtonEntered.PNG"));
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(new ImageIcon("images/userMenuImages/backButtonBasic.PNG"));
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				mf.remove(pip);
				oldPage.setVisible(true);
				mf.requestFocus();

			}
		});
		
		
			pInfo[0].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					//선택된 포켓몬 이름
					bm.changeP(mf,oldPage, user, user.getUp_list().get(0));
					
				}
			});
			pInfo[1].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					bm.changeP(mf,oldPage, user, user.getUp_list().get(1));
					
				}
			});
			pInfo[2].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					bm.changeP(mf,oldPage, user, user.getUp_list().get(2));
					
				}
			});
			pInfo[3].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					bm.changeP(mf,oldPage, user, user.getUp_list().get(3));
					
				}
			});
		



		label.setBounds(450, 20, 200, 40);
		pInfo[0].setBounds(220, 30, 300, 180);
		pInfo[1].setBounds(560, 30, 300, 180);
		pInfo[2].setBounds(220, 360, 300, 180);
		pInfo[3].setBounds(560, 360, 300, 180);

		pInfoText[0].setBounds(180,220,280,130);
		pInfoText[1].setBounds(520,220,280,130);
		pInfoText[2].setBounds(180,535,280,130);
		pInfoText[3].setBounds(520,535,280,130);

		pip.add(label);

		pip.add(backButton);
		pip.add(pInfo[0]);
		pip.add(pInfo[1]);
		pip.add(pInfo[2]);
		pip.add(pInfo[3]);
		pip.add(pInfoText[0]);
		pip.add(pInfoText[1]);
		pip.add(pInfoText[2]);
		pip.add(pInfoText[3]);



	}




	//아이템 사용(유저인벤에서)연결
	public PInfoPage(MainFrame mf, JPanel oldPage, User user,String userItemName) {
		this(mf,oldPage,user);
		this.mf = mf;
		this.pip = this;
		//포켓몬 마우스 선택 이벤트 정리가 필요함
		//넘어온 페이지가 UserInvenPage일때만 적용
		
		JButton okBtn = new JButton("사용");
		JButton noBtn = new JButton("안사용");
		if(oldPage instanceof UserInvenPage) {
			pInfo[0].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					//선택된 포켓몬 이름
					String pName = pInfo[0].getName();
					itemUsedDialog(userItemName,pName);
				}
			});
			pInfo[1].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					String pName = pInfo[1].getName();
					itemUsedDialog(userItemName,pName);
				}
			});
			pInfo[2].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					String pName = pInfo[2].getName();
					itemUsedDialog(userItemName,pName);
				}
			});
			pInfo[3].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					String pName = pInfo[3].getName();
					itemUsedDialog(userItemName,pName);
				}
			});
		}

	}
	public void itemUsedDialog(String userItemName,String pName) {
		int result = JOptionPane.showConfirmDialog(null, "아이템 사용하시겠습니까??", "아이템 사용",JOptionPane.YES_NO_OPTION);
		
		Pokemon poke = null;
		ArrayList<Pokemon> up_list = (ArrayList<Pokemon>) user.getUp_list();
		
		for(int i=0; i<up_list.size(); i++) {
			if(up_list.get(i).getpName().equals(pName)) {
				 poke = up_list.get(i);
			}
		}
		
		if(result == 0) {
			new ItemManager(user).useItem(userItemName,poke);
			mf.remove(pip);
			oldPage.setVisible(true);
			mf.requestFocus();
		}
	}


	/*//Npc배틀페이지 연결
      public PInfoPage(MainFrame mf, NpcBattlePage nbp) {
         this.mf = mf;
         this.pip = this;
         this.nbp = nbp;
         pip.setOpaque(false);
         pip.setBounds(0, 0, 1024, 768);
         pm.setPokemon(new Pokemon(1,"이상해씨",50,3));
         pm.setPokemon(new Pokemon(4,"파이리",50,3));
         pm.setPokemon(new Pokemon(6,"리자몽",50,1));
         pm.setPokemon(new Pokemon(5,"리자드",50,2));
         for(int i=0; i < pInfo.length; i++) {
            pInfo[i] = new JLabel();
            pInfo[i] = new JLabel(new ImageIcon("images/userMenuImages/pInfo4.png"));
         }
         for(int i=0; i < pInfo.length; i++) {
            pInfoText[i] = new JTextArea();

            pInfoText[i].setBackground(new Color(0,0,0,0));
         }



         for(int i=0; i < pm.getPokemon().size(); i++) {
            System.out.println(i);
            String pName = pm.getPokemon().get(i).getPokemonName();
            String pNum = pm.getPokemon().get(i).getPokemonNumber()+"";
            String pSpeed = pm.getPokemon().get(i).getPokemonSpeed()+"";
            String pAtk = pm.getPokemon().get(i).getPokemonAtk()+"";
            String pHp = pm.getPokemon().get(i).getPokemonHp()+"";
            String pLevel = pm.getPokemon().get(i).getPokemonLevel()+"";

            pInfo[i].setIcon(new ImageIcon("images/userMenuImages/pBook/00"+pm.getPokemon().get(i).getPokemonNumber()+".png"));
            pInfoText[i].setText("\t포켓몬 이름 : "+ pName + "\n"
                           +"\t레    벨 : " + pLevel + "\n"
                           +"\t공 격 력 : " + pAtk + "\n"
                           +"\t스 피 드 : " + pSpeed + "\n"
                           +"\t체    력  : " + pHp + "\n"
                           );
         }
         BattleManager.showP(this);

         mf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
               if(e.getKeyCode() == UserMenuPage.ESC) {
                  mf.remove(pip);
                  pip.setVisible(false);
               }
            }
         });

         JLabel label = new JLabel();
         label.setText("  ");

         pip.setBackground(Color.WHITE);
         pip.setLayout(null);
         backButton.setBounds(900, 610, 90, 120);
         backButton.setBorderPainted(false);
         backButton.setFocusPainted(false);
         backButton.setContentAreaFilled(false);

         backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               backButton.setIcon(new ImageIcon("images/userMenuImages/backButtonEntered.PNG"));
               backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
               backButton.setIcon(new ImageIcon("images/userMenuImages/backButtonBasic.PNG"));
               backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
               mf.remove(pip);
               nbp.setVisible(true);
               mf.requestFocus();

            }
         });

         label.setBounds(450, 20, 200, 40);
         pInfo[0].setBounds(180, 30, 300, 160);
         pInfo[1].setBounds(520, 30, 300, 160);
         pInfo[2].setBounds(180, 380, 300, 160);
         pInfo[3].setBounds(520, 380, 300, 160);

         pInfoText[0].setBounds(180,220,300,130);
         pInfoText[1].setBounds(520,220,300,130);
         pInfoText[2].setBounds(180,570,300,130);
         pInfoText[3].setBounds(520,570,300,130);

         pip.add(label);

         pip.add(backButton);
         pip.add(pInfo[0]);
         pip.add(pInfo[1]);
         pip.add(pInfo[2]);
         pip.add(pInfo[3]);
         pip.add(pInfoText[0]);
         pip.add(pInfoText[1]);
         pip.add(pInfoText[2]);
         pip.add(pInfoText[3]);
      }
	 */




	public JLabel[] getpInfo() {
		return pInfo;
	}

	public void setpInfo(JLabel[] pInfo) {
		this.pInfo = pInfo;
	}

	public JTextArea[] getpInfoText() {
		return pInfoText;
	}

	public void setpInfoText(JTextArea[] pInfoText) {
		this.pInfoText = pInfoText;
	}




}





















