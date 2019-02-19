package model.vo;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable{

   private String pName;   //���ϸ� �̸�
   private int pNo;      //���ϸ� ��ȣ
   private int pLevel;      //���ϸ� ����
   private int pType;      //���ϸ� Ÿ�� -> 0 : �븻 / 1 : �� / 2 : �� / 3 : Ǯ
   private int pHp;      //���ϸ� HP
   private int pMaxHp;	 //���ϸ� maxHp
   private int pSpeed;      //���ϸ� ���ǵ�
   private Item pItem;      //���ϸ� ������
   private boolean pEvoType;   //���ϸ� ��ȭ ��� -> true : ��ȭ�� �� / false : ������
   private int grade;      //���ϸ� ��� -> 0 : �� / 1 : �� / 2 : ��

   private int exp;      //���� ����ġ
   private int pMaxExp;   //�ִ� ����ġ, �ƽ��� �����ϸ� ������
   private int setExp;      //������ ��� �Ǵ� ����ġ
   public int getpMaxHp() {
	return pMaxHp;
}



public void setpMaxHp(int pMaxHp) {
	this.pMaxHp = pMaxHp;
}




   private ArrayList<Skill> pSkill;      //���ϸ� ��ų


   public Pokemon() {}



   public Pokemon(String pName, int pNo, int pLevel, int pType, int pHp,int pMaxHp, int pSpeed, Item pItem, boolean pEvoType,
         int grade, int exp, int pMaxExp, int setExp, ArrayList<Skill> pSkill) {
      this.pName = pName;
      this.pNo = pNo;
      this.pLevel = pLevel;
      this.pType = pType;
      this.pHp = pHp;
      this.pSpeed = pSpeed;
      this.pItem = pItem;
      this.pEvoType = pEvoType;
      this.grade = grade;
      this.exp = exp;
      this.pMaxExp = pMaxExp;
      this.setExp = setExp;
      this.pSkill = pSkill;
      this.pMaxHp = pMaxHp;
   }

   public String getpName() {
      return pName;
   }


   public int getpNo() {
      return pNo;
   }


   public int getpLevel() {
      return pLevel;
   }


   public int getpType() {
      return pType;
   }


   public int getpHp() {
      return pHp;
   }


   public int getpSpeed() {
      return pSpeed;
   }


   public Item getpItem() {
      return pItem;
   }


   public boolean ispEvoType() {
      return pEvoType;
   }





   public int getGrade() {
      return grade;
   }


   public int getExp() {
      return exp;
   }


   public int getpMaxExp() {
      return pMaxExp;
   }


   public int getSetExp() {
      return setExp;
   }


   public ArrayList<Skill> getpSkill() {
      return pSkill;
   }


   public void setpName(String pName) {
      this.pName = pName;
   }


   public void setpNo(int pNo) {
      this.pNo = pNo;
   }


   public void setpLevel(int pLevel) {
      this.pLevel = pLevel;
   }


   public void setpType(int pType) {
      this.pType = pType;
   }


   public void setpHp(int pHp) {
      this.pHp = pHp;
   }


   public void setpSpeed(int pSpeed) {
      this.pSpeed = pSpeed;
   }


   public void setpItem(Item pItem) {
      this.pItem = pItem;
   }


   public void setpEvoType(boolean pEvoType) {
      this.pEvoType = pEvoType;
   }





   public void setGrade(int grade) {
      this.grade = grade;
   }


   public void setExp(int exp) {
      this.exp = exp;
   }


   public void setpMaxExp(int pMaxExp) {
      this.pMaxExp = pMaxExp;
   }


   public void setSetExp(int setExp) {
      this.setExp = setExp;
   }


   public void setpSkill(ArrayList<Skill> pSkill) {
      this.pSkill = pSkill;
   }
}