package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.dao.PokemonDao;
import model.vo.Pokemon;
import model.vo.Recovery;
import model.vo.User;

public class EvolutionManager {
	
	private User user;
	private PokemonDao pd = new PokemonDao();
	
	private int evoHP = 50;
	private int evoSpeed = 10;
	
	/*public EvolutionManager(User user) {
		this.user = user;
	}*/
	
	public EvolutionManager(User user, Pokemon poke) {
		
		System.out.println("��ȭ�ǵ� ��ȭ ������");
		
		String beforePName = poke.getpName();
		Pokemon evoP = null;
		
		for(int i = 0; i < pd.getpList().size(); i++) {
			if(poke.getpNo() == pd.getpList().get(i).getpNo()) {
				evoP = pd.getpList().get(i+1);
			}
		}
		
		poke.setpName(evoP.getpName());
		poke.setpNo(evoP.getpNo());
		poke.setpMaxHp(poke.getpMaxHp() + evoHP);
		poke.setpSpeed(poke.getpSpeed() + evoSpeed);
		poke.setGrade(poke.getGrade() -1);
		
		JOptionPane.showMessageDialog(null, beforePName + "��/�� " + evoP.getpName() + "���� ��ȭ�߽��ϴ�.", "��ȭ����", JOptionPane.WARNING_MESSAGE);
	}
}
