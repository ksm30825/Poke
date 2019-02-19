package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.dao.UserDao;

public class FadeOut extends JPanel{

	private MainFrame mf;
	private Map m;
	private FadeOut fade;
	private int alpha = 0;
	private int increment = 5;
	private int starton = 0;
	
	public FadeOut(MainFrame mf){
		this.mf = mf;
		this.fade = this;

		this.setVisible(true);
		this.setBounds(0,0,1024,768);
		mf.add(this);
		this.setBackground(new Color(0, 0, 0,0));

		

	}
	public void fadeout(MainFrame mf) {
		Timer timer = new Timer(40, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				alpha += increment;
				if (alpha > 255 && starton == 0) {
					//increment = -increment;
					starton = 1;
					//fade.setVisible(false);
					mf.remove(fade);
					UserDao ud = new UserDao("이름이름");
					ud.saveUser(); 
					mf.add(new Map(mf, ud.getUserList().get(0)));
				}
				/*if (alpha <= 0) {
					alpha = 0;
					increment = -increment;
				}*/
				fade.setBackground(new Color(0, 0, 0, alpha));
				
			}
		});
		timer.start();
		if(alpha == 255) {
			timer.stop();
			
		}
	}

}
