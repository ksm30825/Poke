package model.dao;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import model.vo.Item;
import model.vo.Pokemon;
import model.vo.Skill;

public class PokemonDao implements Serializable{
   private List<Pokemon> pList = new ArrayList<Pokemon>();

   public PokemonDao() {
	   
      pList.add(new Pokemon("�̻��ؾ�",1,0,3,0, 0,0, new Item(), false,0, 0, 0, 0, new ArrayList<Skill>()));
      pList.add(new Pokemon("�̻���Ǯ",2,0,3,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("�̻��ز�",3,0,3,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("���̸�",4,0,1,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("���ڵ�",5,0,1,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("���ڸ�",6,0,1,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("���α�",7,0,2,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("��Ϻα�",8,0,2,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("�źϿ�",9,0,2,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("����",17,0,0,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("������",18,0,0,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("����",19,0,0,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("��Ʈ��",20,0,0,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("������",21,0,0,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("����帱��",22,0,0,0, 0, 0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      pList.add(new Pokemon("��ī��",23,0,0,0, 0,0, new Item(), false,0, 0, 0, 0,new ArrayList<Skill>()));
      
	   //outputPokemon();
      
   }
   
   public void inputPokemon() {
      try {
         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pokemon.dat",true));
         
         oos.writeObject(pList);
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public void outputPokemon() {
      try {
         ObjectInputStream oos = new ObjectInputStream(new FileInputStream("pokemon.dat"));
         
         pList = (List<Pokemon>) oos.readObject();
         
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   public List<Pokemon> getpList() {
      return pList;
   }

   public void setpList(List<Pokemon> pList) {
      this.pList = pList;
   }

}