package model.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.vo.Skill;

public class SkillDao implements Serializable{
   private List<Skill> sList = new ArrayList<Skill>();
   private int num = 0;   



   public List<Skill> getsList() {
	return sList;
}

public void setsList(List<Skill> sList) {
	this.sList = sList;
}

public SkillDao() {
      //ó�� ������ �Է¿�
      
      sList.add(new Skill("��߸��",100,0,0));
      sList.add(new Skill("�����ġ��",1,0,15));
      sList.add(new Skill("������ȭ",2,0,18));
      sList.add(new Skill("���ӻ�ġ��",3,0,13));
      sList.add(new Skill("������ġ",4,0,10));
      sList.add(new Skill("�ı�����",5,0,20));
      sList.add(new Skill("�Ҳɼ���",6,1,12));
      sList.add(new Skill("ȭ�����",7,1,16));
      sList.add(new Skill("��Ʈ������",8,1,14));
      sList.add(new Skill("�Ҵ빮��",9,1,18));
      sList.add(new Skill("������",10,2,12));
      sList.add(new Skill("���̵������",11,2,16));
      sList.add(new Skill("���������",12,2,14));
      sList.add(new Skill("�ĵ�Ÿ��",13,2,18));
      sList.add(new Skill("���Ѹ���",14,3,12));
      sList.add(new Skill("����ä��",15,3,14));
      sList.add(new Skill("�ٳ�������",16,3,16));
      sList.add(new Skill("�ֶ��",17,3,18));
	//outputSkill();
   }
   
   public void inputSkill() {
      try {
         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Skill.dat",true));
         
         oos.writeObject(sList);
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
   
   public void outputSkill() {
      try {
         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Skill.dat"));
         
         sList = (List<Skill>) ois.readObject();
         
      }catch(IOException e) {
         e.printStackTrace();
      }catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
   
}