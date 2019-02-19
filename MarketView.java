package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.MCManager;
import model.dao.ItemDao;
import model.dao.UserDao;
import model.vo.Item;
import model.vo.User;

//������ ���� �ϴ� ���� Ŭ����

//��ũ�Ѹ� �߰� �ϸ� list�� ������ ����...
//�ذ����� �𸣰���
public class MarketView extends JPanel {
   private MainFrame mf;
   private JPanel marketView;
   private MCManager mc;
   private ItemDao id = new ItemDao();
   private User user;
   
   private JPanel listPanel;
   private Map m;
   private String itemTemp;//������ ������� ��ȣ �Űܼ� ���//���� ���� X
   private String iName;
   private String iNo;//������ ������ �ش� ��ȣ
   private int iAmount;//������ ������ ����
   
   private Image backButtonImage = new ImageIcon("images/maketViewImages/marketViewBack.png").getImage();
   private JButton backButton = new JButton(new ImageIcon(backButtonImage));
   private Image marketBackground = new ImageIcon("images/maketViewImages/marketView.gif").getImage();

   
   public MarketView(MainFrame mf, JPanel oldPage, User user) {
	  mc = new MCManager(user);
	  this.user = user;
      this.mf=mf;
      this.marketView=this;
      this.m=(Map)oldPage;
      this.setLayout(null);
      this.setBounds(0, 0, 1024, 768);
      
      listPanel =new JPanel();
      
      String[] iNameList = new String[id.getIList().size()];
      for(int i=0 ; i<iNameList.length ; i++) {
         iNameList[i]=id.getIList().get(i).getiName();
      }      
      
      String[] iPriceList = new String[id.getIList().size()];
      for(int i=0 ; i<iPriceList.length ; i++) {
         iPriceList[i]=id.getIList().get(i).getiPrice() + "G";
         
      }
      
      ImageIcon[] iImgList = new ImageIcon[id.getIList().size()];
      int num = 0;
      for(int i=0 ; i<iImgList.length ; i++) {
    	  iImgList[i] = new ImageIcon("images/itemImages/i00" + num + ".png");
    	  num++;
       } 
      
      JList itemPrice = new JList(iPriceList);
      itemPrice.setFont(new Font(getName(),Font.BOLD,15));
      itemPrice.setBounds(677, 210, 47, 250);
      itemPrice.setEnabled(false);
      itemPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      JList itemName = new JList(iNameList);
      itemName.setFont(new Font(getName(),Font.BOLD,15));
      itemName.setBounds(424, 210, 255, 250);
      itemName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      JLabel presentGold = new JLabel("���� ���� �ݾ�");
      presentGold.setBounds(300, 335, 100, 50);
      presentGold.setFont(new Font(getName(),4,12));
      
      JTextField userGold = new JTextField(40);
      userGold.setText(user.getuGold() + "G");
      userGold.setLocation(300, 372);
      userGold.setSize(80,35);
      userGold.setEditable(false);
      userGold.setHorizontalAlignment(JTextField.CENTER);
      presentGold.add(userGold);
      JLabel selected = new JLabel("���� �׸�");
      selected.setBounds(265, 525, 100, 50);
      
      JTextField selectedItem = new JTextField(40);
      selectedItem.setLocation(356, 524);
      selectedItem.setSize(100, 50);
      
      JLabel itemImg = new JLabel();
      itemImg.setBounds(326, 286, 50, 50);
      
      
      itemName.addListSelectionListener(new ListSelectionListener() {
         
         @Override
         public void valueChanged(ListSelectionEvent e) {
            selectedItem.setText(itemName.getSelectedValue()+"");
            selectedItem.setHorizontalAlignment(JTextField.CENTER);
            itemImg.setIcon(iImgList[itemName.getSelectedIndex()]);
         }
      });
      
      JLabel amount = new JLabel("���� �Է�");
      amount.setBounds(530, 525, 100, 50);
      
      JTextField selectedAmount = new JTextField(40);
      selectedAmount.setLocation(598, 524);
      selectedAmount.setSize(100, 50);
      selectedAmount.setHorizontalAlignment(JTextField.RIGHT);
      amount.add(selectedAmount);
      
      
      JButton btn = new JButton("����");
      btn.setBounds(697, 524, 60, 50);
      
      //������ �����
      JLabel sayResult = new JLabel("������ ����");
      sayResult.setBounds(256, 600, 100, 50);
      
      
      JTextField resultItem = new JTextField(40);
      resultItem.setLocation(355, 595);
      resultItem.setSize(100, 50);
      resultItem.setEditable(false);
      
      JTextField resultAmount = new JTextField(40);
      resultAmount.setLocation(457, 595);
      resultAmount.setSize(300, 50);
      resultAmount.setEditable(false);

      
      btn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {//���Ź�ư ����
            
           //���� ������ ���� MCManager�� ���� ������ �ʱⰪ ��������
            //MCManager���� ��
        	 
        	 resultItem.setText(selectedItem.getText());
        	 resultItem.setHorizontalAlignment(JTextField.CENTER);
        	 
        	 resultAmount.setText(selectedAmount.getText() + "���� �����Ͽ����ϴ�.");
        	 resultAmount.setHorizontalAlignment(JTextField.CENTER);
        	 
            iAmount = Integer.parseInt(selectedAmount.getText());
           
            mc.useMarket(iName, iAmount);
            
            mc.useMarket(selectedItem.getText(), iAmount);
            userGold.setText(user.getuGold()+"G");
            
            if(mc.getResultNo()!=null) {
                resultAmount.setText(mc.getResultNo());
                userGold.setText(user.getuGold()+"G");
            }
         }
      });
      
      
      backButton.setBounds(905, 657, 90, 60);
      settingButton(backButton);
      
      
      backButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) { }
         @Override
         public void mouseExited(MouseEvent e) {}
         @Override
         public void mousePressed(MouseEvent e) {
        	 
        	 resultItem.setText(null);
        	 resultAmount.setText(null);
        	 
        	 mf.remove(marketView);
            
        	 m.setVisible(true);
        	 mf.requestFocus();
        	 m.setEscCtn(0);
            
         }
      });

      
      this.add(itemName);
      this.add(itemPrice);
      this.add(selected);
      this.add(itemImg);
      
      this.add(selectedItem);
      this.add(selectedAmount);
      
      this.add(amount);
      this.add(sayResult);
      
      this.add(resultItem);
      this.add(resultAmount);
      this.add(btn);
      
      this.add(backButton);
      
      this.add(presentGold);
      this.add(userGold);
      
      this.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("x : " + e.getX() + " / y : " + e.getY());
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
      
   }
   
   public void paintComponent(Graphics g) {
	   
	      g.drawImage(marketBackground, 0, 0, 1024, 729, this);
   }
   
   public void settingButton(JButton jb) {
      jb.setBorderPainted(false);
      jb.setFocusPainted(false);
      jb.setContentAreaFilled(false);
   }
   
}