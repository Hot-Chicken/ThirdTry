
import java.awt.event.*;
import javax.swing.*;
public class main extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 111151514654646451L;
	
	private map map1 = new map();


	
	
	
	
	public main(){

		
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);
		
		JMenu help = new JMenu("start");
		menu.add(help);
		
		JMenuItem copyright= new JMenuItem("版权");
		help.add(copyright);
		
		JMenuItem startgame = new JMenuItem("自由点击选择活细胞");
		help.add(startgame);
		
		JMenuItem random =new JMenuItem("random");
		help.add(random);
		
//		JMenuItem clear = new JMenuItem("clear");
//		help.add(clear);
		
		
		copyright.addActionListener(new crActionListener());
//		clear.addActionListener(new clearActionListener());
		random.addActionListener(new randomActionListener());
		startgame.addActionListener(new helpActionListener());

		this.setSize(430, 390);
		this.setTitle("the game of life by Or");
		this.add(map1);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	class crActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(null, "this made by 陈江滔和徐鑫诚");
		}
	}
	
//	class clearActionListener implements ActionListener{
//		public void actionPerformed(ActionEvent e){
//			map1.clear();
//		}
//	}
	
	class randomActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			map1.setRandom();
		}
	
	}
	
	class helpActionListener implements  ActionListener{
		public void actionPerformed(ActionEvent e){
			
			JOptionPane.showMessageDialog(null, "请点击方格选择活细胞");
			
			map1.clickCheck2();
			
			
		}
	}
	public static void main(String[] args){
		main game=new main();

		game.setVisible(true);
	}
}
