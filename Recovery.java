package model.vo;

import java.awt.Image;

public class Recovery extends Item {
   
   private int rType;   //ȸ���� ���� -> 0 : �� / 1 : �� / 2 : ��
   private int rAmount;   //ȸ����
   
   public Recovery() {}
   
   public Recovery(String iName, int iNo, int iPrice, int iType, int iAmount, String iInfo, int rType, int rAmount) {
	super(iName, iNo, iPrice, iType, iAmount, iInfo);
	this.rType = rType;
	this.rAmount = rAmount;
}

public int getrType() {
      return rType;
   }

   public int getAmount() {
      return rAmount;
   }

   public void setrType(int rType) {
      this.rType = rType;
   }

   public void setAmount(int amount) {
      this.rAmount = amount;
   }

}