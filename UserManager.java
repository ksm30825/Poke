package controller;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import model.vo.User;

public class UserManager {
	User user;
	Font font = new Font("", Font.BOLD, 30);
	
	public UserManager(User user) {
		this.user = user;
	}
	
	public void userItemSet() {
		
	}
	public void saveUser() {
		
	}
	
	public void firstSelectPokemon(User user) { 
		//ù ���ϸ� �����ϴ� �޼ҵ�
	}
	
	public JLabel getUserGold() {
		JLabel goldLabel = new JLabel();
		goldLabel.setFont(font);
		goldLabel.setText(user.getuGold()+"");
		return goldLabel;
	}

	public void setUserGold(int gold) {
		user.setuGold(gold);
	}
	//���� �г��� �󺧷� ����� ����
	public JLabel viewUserName() {
		JLabel userNameLabel = new JLabel();
		userNameLabel.setText(user.getuName());
		userNameLabel.setFont(font);
		return userNameLabel;
	}
	public String viewUserTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh�� mm��");
		long diff = user.getuDate().getTime() - new Date().getTime();
		String time = sdf.format(diff);
		return time;
		
	}
	//���� �����ð� �󺧷� ����� ����
	public JLabel viewCreateTime() {
		JLabel createTimeLabel = new JLabel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh�� mm��");
		String time = sdf.format(user.getuDate());
		createTimeLabel.setText(time);
		createTimeLabel.setFont(new Font("",Font.CENTER_BASELINE, 18));
		return createTimeLabel;
	}
	//���� ���ϸ� �� �󺧷� ����� ����
	public JLabel viewUserGetPoke() {
		JLabel getPokeLabel = new JLabel();
		getPokeLabel.setText(user.getTp_list().size() + "����");
		return getPokeLabel;
	}
}
