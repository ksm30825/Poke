package model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.vo.Ball;
import model.vo.Item;
import model.vo.Recovery;
import model.vo.Stone;

public class ItemDao {
   
   private List<Item> iList = new ArrayList<Item>();
   
   public ItemDao() {
	      iList.add(new Ball("������ ��", 0, 50, 0, 0,"��ȹ�� �� ����Ѵ�.\nȮ���� ����", 70, 0));
	      iList.add(new Ball("���� ��", 1, 30, 0, 0,"��ȹ�� �� ����Ѵ�.\nȮ���� �߰��̴�.", 50, 1));
	      iList.add(new Ball("���� ��", 2, 10, 0, 0,"��ȹ�� �� ����Ѵ�.\nȮ���� ����.", 20, 2));
	      iList.add(new Recovery("��� ��ó��", 3, 30, 1, 0,"ü���� 100��ŭ ȸ���Ѵ�.", 0, 100));
	      iList.add(new Recovery("�߱� ��ó��", 4, 20, 1, 0,"ü���� 50��ŭ ȸ���Ѵ�.", 1, 50));
	      iList.add(new Recovery("�ʱ� ��ó��", 5, 10, 1, 0,"ü���� 30��ŭ ȸ���Ѵ�.", 2, 30));
	      iList.add(new Stone("����� ��", 6, 200, 2, 0,"�븻���ϸ��� ��ȭ �� �� ����Ѵ�.", 0));
	      iList.add(new Stone("ȭ���� ��", 7, 200, 2, 0,"�����ϸ��� ��ȭ �� �� ����Ѵ�.", 1));
	      iList.add(new Stone("�ٴ��� ��", 8, 200, 2, 0,"�����ϸ��� ��ȭ �� �� ����Ѵ�.", 2));
	      iList.add(new Stone("������ ��", 9, 200, 2, 0,"Ǯ���ϸ��� ��ȭ �� �� ����Ѵ�.", 3));
	      //outputItem();
	   }
   
   public void inputItem() {
      try {
         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("item.dat", true));
         oos.writeObject(iList);
         
      } catch (IOException e) {}
   }
   
   public void outputItem() {
      
      ObjectInputStream ois;
      try {
         ois = new ObjectInputStream(new FileInputStream("item.dat"));
         iList = (List<Item>) ois.readObject();
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }
   
   public List<Item> getIList() {
      return iList;
   }
   
   public void setIList(List<Item> iList) {
      this.iList = iList;
   }
}