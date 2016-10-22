import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class map extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private final int height=25;
	private final int width=25;
	double density;    //�����Ϸģʽ�»�ϸ���ܶ�
	static int [][] World={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	private static int [][] nextStatus=new int[World.length][World[0].length];
	static int [][] tempStatus=new int[World.length][World[0].length];
	private Timer time;
	private final int DELAY_TIME = 600;
	
	public map(){
		setLayout(null);
		JButton clear = new JButton("clear");
		this.add(clear);
		clear.setBounds(350,300,80,30);
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 for(int i=0;i<World.length;i++)
					 for(int j=0;j<World[0].length;j++)
						 tempStatus[i][j]=World[i][j];
				 repaint();
				 time.stop();
			}
		});
		
	}
	
	/*
	 * ���ƻ���
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0;i<World.length;i++)
			for(int j=0;j<World[0].length;j++){
				g.drawRect(j*width, i*height, width,height);
				if(tempStatus[i][j]==1)
				{
					g.fillRect(j*width, i*height, width, height);
				}
			}
	}
	
	/**
	 * �˺������ڸ�����Ϸ����changeCellStatus()����ʱ�����Ż��ƻ���
	 */
	private void startGame(){
		time = new Timer(DELAY_TIME, new ActionListener() {  
			 public void actionPerformed(ActionEvent e){
				
				 changeCellStatus();
				 repaint();
			 }
		 });
		time.start();
	}
	
	/**
	 * ���������������
	 */
//	 public void clear(){
		 
//		 for(int i=0;i<World.length;i++)
//			 for(int j=0;j<World[0].length;j++)
//				 tempStatus[i][j]=World[i][j];
//		 repaint();
//		 time.stop();
//	 }

	  /**
	   * ���������������ɻ�ϸ��
	   */
	public void setRandom(){
		
		final JButton jb=new JButton("start");
		this.add(jb);
		jb.setBounds(170,300,80,30);
		jb.setVisible(true);
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startGame();
				jb.setVisible(false);
			}
		});
		
		 double randomNum;           //������������������
			density=Double.parseDouble(JOptionPane.showInputDialog("�������ϸ��ռ�������ı�����0~1)"));   
			                                            //�����ܶ�
			for(int i=0;i<World.length;i++)                //����ѭ���������������
				for(int j=0;j<World[0].length;j++){
					randomNum=Math.random();
					if(randomNum<=density)
						tempStatus[i][j]=1;
					else 
						tempStatus[i][j]=0;
					}
			repaint();
	}
 
	 class TimerListener implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 repaint();
		 }

		
	 }
	 
	 
	/**
	 * ����ѡ���ϸ��
	 */
	 public void clickCheck2(){
		 final JButton jb=new JButton("start");
			this.add(jb);
			jb.setBounds(170,300,80,30);
			jb.setVisible(true);
			jb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					startGame();
					jb.setVisible(false);
				}
			});
		 this.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				int temp=e.getClickCount(),count=0;
				while(temp>count){
					tempStatus[e.getY()/height][e.getX()/width]=1;
					count++;
					repaint();
				}
			 }
		 });
		// startGame();
	 }
	 
	 /**
	  * ��Ŀ��ϸ����Χ�Ļ�ϸ��������ͳ�Ƹ�����������
	  * @param row
	  * @param col
	  * @return
	  */
	private  int neighborsCount(int row,int col){
			 int count=0,c=0,r=0;
			 for (r = row - 1; r <= row + 1; r++) {  
		            for (c = col - 1; c <= col + 1; c++) {  
		                if (r < 0 || r >= tempStatus.length || c < 0  
		                        || c >= tempStatus[0].length) {  
		                    continue;  
		                }  
		                if (tempStatus[r][c] == 1) {  
		                    count++;  
		                }  
		            }  
		        }  
		        if (tempStatus[row][col] == 1) {  
		            count--;  
		        }  
		        return count;  
	}
	
	
	/**
	 * ����Ŀ��ϸ����Χ��ϸ�������ж�Ŀ��ϸ���Ƿ�Ӧ������
	 */
	 private void changeCellStatus() {  
	        for (int row = 0; row < nextStatus.length; row++) {  
	            for (int col = 0; col < nextStatus[row].length; col++) {  
	  
	                switch (neighborsCount(row, col)) {  
	                case 0:  
	                case 1:  
	                case 4:  
	                case 5:  
	                case 6:  
	                case 7:  
	                case 8:  
	                    nextStatus[row][col] = 0;  
	                    break;  
	                case 2:  
	                    nextStatus[row][col] = tempStatus[row][col];  
	                    break;  
	                case 3:  
	                    nextStatus[row][col] = 1;  
	                    break;  
	                }  
	            }  
	        }  
	        copyWorldMap();  
	 }  
	 
	 private void copyWorldMap(){
		 for (int row = 0; row < nextStatus.length; row++)  
	            for (int col = 0; col < nextStatus[row].length; col++) 
	                tempStatus[row][col] = nextStatus[row][col];  
	            }
	 }


