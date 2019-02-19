package model.vo;

import java.awt.Image;

public class Stone extends Item {
	
   private int stoneType;	//���� Ÿ�� -> 1 : �� / 2 : �� / 3 : Ǯ
   
   public Stone() {}
   
   public Stone(String iName, int iNo, int iPrice, int iType, int iAmount, String iInfo, int stoneType) {
      super(iName, iNo, iPrice, iType, iAmount, iInfo);
      this.stoneType = stoneType;
   }

   public int getStoneType() {
	return stoneType;
   }

   public void setStoneType(int stoneType) {
	this.stoneType = stoneType;
   }
   
   

}