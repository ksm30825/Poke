package model.vo;

import java.io.Serializable;

//����ȭ ó��
public class Skill implements Serializable {

   private String sName;   //��ų�̸�
   private int sNum;      //��ų �ѹ�
   private int sType;      //��ųŸ��
   private int sAtt;      //��ų ���ݷ�
   
   public Skill() {}
   
   public Skill(String sName, int sNum, int sType, int sAtt) {
      this.sType = sType;
      this.sNum = sNum;
      this.sName = sName;
      this.sAtt = sAtt;
   }

   public String getsName() {
      return sName;
   }
   
   public int getsNum() {
      return sNum;
   }
   
   public int getsType() {
      return sType;
   }

   public int getsAtt() {
      return sAtt;
   }
   
   
   public void setsName(String sName) {
      this.sName = sName;
   }
   public void setsNum(int sNum) {
      this.sNum = sNum;
   }
   
   public void setsType(int sType) {
      this.sType = sType;
   }
   public void setsAtt(int sAtt) {
      this.sAtt = sAtt;
   }
   
}