package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.dao.ItemDao;
import model.vo.Ball;
import model.vo.Item;
import model.vo.Pokemon;
import model.vo.Recovery;
import model.vo.Stone;
import model.vo.User;

public class ItemManager {

	private ItemDao id = new ItemDao();
	private User user;

	public ItemManager(User user) {
		this.user = user;
	}

	public void decreaseGold(int check) {
		System.out.println("ItemMamager�� ��");
		//���� �Ѿ�, ���� ���� ���� �ݾ��� �����ͼ� �� ����ִ�
		System.out.println("�����Ѿ� : " + check);

		user.setuGold(user.getuGold() - check);

		System.out.println("���� �ݾ� : " + user.getuGold());
	}

	public void addInven(int iNo, int iAmount) {

		System.out.println("���� �κ��丮 �߰� ����");
		System.out.println("������ ������ ��ȣ : " + iNo + " / ���� : " + iAmount);
		Item item = new Item();
		item = id.getIList().get(iNo);
		item.setiAmount(iAmount);
		//���� �����۸���Ʈ ��������
		ArrayList<Item> ui_list = user.getUi_list();

		Item searchItem = null;

		for(int i=0; i<ui_list.size(); i++) {
			//���� ������ ����Ʈ�� �����Ϸ��� �������� �������
			if(ui_list.get(i).getiNo() == iNo) {
				searchItem = ui_list.get(i);
				iAmount = iAmount + ui_list.get(i).getiAmount();
				ui_list.get(i).setiAmount(iAmount);
				user.setUi_list(ui_list);
			}
		}
		if(searchItem == null) {
			//���� �����۸���Ʈ�� ������ �������� ���� �߰�
			user.getUi_list().add(item);
		}



		/*if(user.getUi_list().get(iNo).getiAmount()!=0) {
    	  iAmount = iAmount + user.getUi_list().get(iNo).getiAmount();
    	  user.getUi_list().set(iNo, item);
    	   user.getUi_list().get(iNo).setiAmount(iAmount);
      }*/




		for(int i = 0; i < user.getUi_list().size(); i++) {
			if(user.getUi_list().get(i) == null) {
				System.out.print(i + " : ��ĭ | ");
			}else {
				System.out.print(" " + i + " : " + user.getUi_list().get(i).getiName() + " / "
						+ user.getUi_list().get(i).getiAmount() + "�� |");
			}
		}

		//useItem(iNo);

	}
	
	public void subInven(int iNo, int iAmount) {
		
		System.out.println("���� �κ��丮 ���� ����");
		System.out.println("������ ������ ��ȣ : " + iNo + " / ���� : " + iAmount);
		Item item = new Item();
		item = id.getIList().get(iNo);
		item.setiAmount(iAmount);
		//���� �����۸���Ʈ ��������
		ArrayList<Item> ui_list = user.getUi_list();
		
		for(int i=0; i<ui_list.size();i++) {
			if(ui_list.get(i).getiNo()==iNo) {
				ui_list.get(i).setiAmount(ui_list.get(i).getiAmount()-1);
				if(ui_list.get(i).getiAmount()==0) {
					//������ 0�� �Ǹ� ����
					ui_list.remove(i);
				}
				
			}
		}
	}
	
	//0217-02
	public int useItem(int iNo,Pokemon poke) {
		//0217-02 Item item = id.getIList().get(iNo);
		
		ArrayList<Item> ui_list = user.getUi_list();
		Item useItem = null;
		
		for(int i=0; i<ui_list.size();i++) {
			if(ui_list.get(i).getiNo()==iNo) {
				useItem = ui_list.get(i);
				/*ui_list.get(i).setiAmount(ui_list.get(i).getiAmount()-1);*/
				if(ui_list.get(i).getiAmount()==0) {
					//������ 0�� �Ǹ� ����
					ui_list.remove(i);
				}
				
			}
		}
		
		if(useItem instanceof Ball) {
			useItem = (Ball)id.getIList().get(iNo);
			
			System.out.println(poke.getpName() + "���� " + useItem.getiName() + "���");
			subInven(iNo, useItem.getiAmount());
			//id.getIList().get(iNo).setiAmount(id.getIList().get(iNo).getiAmount() - 1);

			//--------�ܼ� Ȯ��
			System.out.println();
			System.out.println(id.getIList().get(iNo).getiName() + "�� ��ȹ Ȯ�� : " + ((Ball) useItem).getcProb());
			System.out.println("�� ��� �� �κ� Ȯ��");
			for(int j = 0; j < user.getUi_list().size(); j++) {
				if(user.getUi_list().get(j) == null) {
					System.out.print(j + " : ��ĭ | ");
				}else {
					System.out.print(" " + j + " : " + user.getUi_list().get(j).getiName() + " / "
							+ user.getUi_list().get(j).getiAmount() + "�� |");
				}
			}
			//--------�ܼ� Ȯ��

			return ((Ball) useItem).getcProb();

		}else if(useItem instanceof Recovery) {
			//���ϸ� ü�� ȸ��
			//maxHp���� �������� ��밡��
			if(poke.getpHp()<poke.getpMaxHp()) {
				subInven(iNo, useItem.getiAmount());
				poke.setpHp(poke.getpHp()+((Recovery) useItem).getAmount());
				//ȸ���Ŀ� ���� ü���� �ִ� ü�º��� ������ ������
				if(poke.getpHp()>poke.getpMaxHp()) {
					poke.setpHp(poke.getpMaxHp());
				}
			}else {
				JOptionPane.showMessageDialog(null, "ü���� ���� á���ϴ�", "����", JOptionPane.WARNING_MESSAGE);
			}
			
			
/*			useItem = (Recovery)id.getIList().get(iNo);
			id.getIList().get(iNo).setiAmount(id.getIList().get(iNo).getiAmount() - 1);
			

			//--------�ܼ� Ȯ��
			System.out.println();
			System.out.println(id.getIList().get(iNo).getiName() + "�� ȸ���� : " + ((Recovery) useItem).getAmount());
			System.out.println("ȸ���� ��� �� �κ� Ȯ��");
			for(int j = 0; j < user.getUi_list().size(); j++) {
				if(user.getUi_list().get(j) == null) {
					System.out.print(j + " : ��ĭ | ");
				}else {
					System.out.print(" " + j + " : " + user.getUi_list().get(j).getiName() + " / "
							+ user.getUi_list().get(j).getiAmount() + "�� |");
				}
			}
			//--------�ܼ� Ȯ��
*/
			return ((Recovery) useItem).getAmount();

		}else if(useItem instanceof Stone) {
			useItem = (Stone)id.getIList().get(iNo);
			
			if(((Stone) useItem).getStoneType() == poke.getpType() && poke.ispEvoType() == true) {
				System.out.println("��ȭ����");
				subInven(iNo, useItem.getiAmount());
				new EvolutionManager(user, poke);
			}else {
				if(poke.ispEvoType() == false) {
					JOptionPane.showMessageDialog(null, "��ȭ�� ���� ����� �� �ִ� ���ϸ��� �ƴմϴ�.", "����", JOptionPane.WARNING_MESSAGE);
				}
				if(((Stone) useItem).getStoneType() != poke.getpType() && poke.ispEvoType() == true) {
					JOptionPane.showMessageDialog(null, "��ȭ�� ���� ���ϸ� Ÿ���� ���� �ʽ��ϴ�.", "����", JOptionPane.WARNING_MESSAGE);
				}
			}
			return ((Stone) useItem).getStoneType();
		}

		return 0;
	}
	//0217-02 useItem �޼ҵ� �����ε�
	public int useItem(String iName,Pokemon poke) {
		int iNo=0;
		for(int i=0; i<id.getIList().size();i++) {
			if(id.getIList().get(i).getiName().equals(iName)) {
				iNo = id.getIList().get(i).getiNo();
			}
		}
		return useItem(iNo,poke);
	}
	
	
	//������ �̸��� ���Ͽ� ����
			public Item itemReturn(String iName) {
				Item item = null;
				ArrayList<Item> iList = user.getUi_list();
				for(int i=0; i<iList.size(); i++) {
					if(iList.get(i).getiName().equals(iName)) {
						item = iList.get(i);
					}
				}
				
				return item;
			}

}