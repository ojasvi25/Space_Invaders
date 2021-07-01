import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random; 

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpaceInvaders extends JFrame implements KeyListener {
	JPanel panel;
	int[]invadersX = new int[34];
	int[]invadersY = new int[34];
	int currentvertical = 50;
	int userX = 300;
	int InvadersMovement;
	int[] userBulletsX = new int[1000];
	int[] userBulletsY = new int[1000];
	int currentbullet = 0;
	int[] invaderBulletsX = new int[1000];
	int[] invaderBulletsY = new int[1000];
	int count = 0;
	int currentinvaderbullet = 0;
	int[] alive = new int[34];
	JLabel score;
	int currentscore = 0;
	boolean gameover = false;

	
	public SpaceInvaders() {
		super("Space Invaders");
		init();
		setVisible(true);
	}
	
	public void init()  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		for (int x= 0 ; x<alive.length ; x++) {
			alive[x] = 1 ;
		} 
		panel = new JPanel() {
			protected void paintComponent(Graphics g)
		{	BufferedImage img;


				if (gameover) {
					try {
						img = ImageIO.read(new File("C:\\Users\\ojasvi.talwar\\Downloads\\gameover.jpg"));
						g.drawImage(img, 0,0,700,600,null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }else {
				
				try {
					
					img = ImageIO.read(new File("C:\\Users\\ojasvi.talwar\\Downloads\\background.jpg"));	
					g.drawImage(img, 0,0,700,600,null);
					currentvertical =50 ;
					for(int i = 0; i<33;i++) {
						invadersX[i+1] = invadersX[i] + 40;
						invadersY[i] = currentvertical;
						if(i==10 || i==21) {
							currentvertical += 40;
							invadersX[i+1] = invadersX[0];
						}
						BufferedImage invader = ImageIO.read(new File("C:\\Users\\ojasvi.talwar\\Downloads\\spaceinvaders.png"));
						if (invadersX[0] <= 10) {
						 InvadersMovement = 10;
						}
						else if (invadersX[0] >= 150) {
							InvadersMovement = -10 ;
						}
						invadersX[i] = invadersX[i] + InvadersMovement;
						if (alive[i]== 1) {
							g.drawImage(invader, invadersX[i], invadersY[i], 26, 26, null);
						} 
			
						
						
						
					}
					BufferedImage user = ImageIO.read(new File("C:\\Users\\ojasvi.talwar\\Downloads\\user.png"));
					if(userX <= 20) {
						userX = 0;
					}else if (userX >= 570) {
						userX=570;
					}
					g.drawImage(user, userX, 400, 80, 80, null);
					for (int j=0 ; j< currentbullet ; j++) {
						g.setColor(Color.red);
						g.fillRect(userBulletsX[j], userBulletsY[j], 5, 10);
						userBulletsY[j] =userBulletsY[j] - 25 ;
					}
					if (count ==10) {
						Random r = new Random();
						int randomInvader = r.nextInt(33);
						if(alive[randomInvader]==1)
						{
							invaderBulletsX[currentinvaderbullet] = invadersX [randomInvader];
							invaderBulletsY[currentinvaderbullet] = invadersY [randomInvader];
		
						}
						currentinvaderbullet ++;
						count = 0;
					}
					for (int k=0; k<currentinvaderbullet; k++) {
						g.setColor(Color.ORANGE);
						g.fillRect(invaderBulletsX[k], invaderBulletsY[k], 5,10);
						invaderBulletsY[k] = invaderBulletsY[k] + 25;
						
					}

					for (int y = 0 ; y <33; y++) {
						for(int z = 0; z < currentbullet; z++) {
							if(alive[y]==1 && userBulletsX[z] >= invadersX[y] && userBulletsX[z] <= invadersX[y]+ 20 && userBulletsY[z] >= invadersY[y] && userBulletsY[z] <= invadersY[y])
							{	alive[y] = 0;
								userBulletsY[z] = -20;
								currentscore += 2;
								updatescore();

							}
						}
					}

					for(int w=0; w<currentinvaderbullet; w++)
					{
						if(invaderBulletsX[w] >= userX && invaderBulletsX[w] <= userX+ 60 && invaderBulletsY[w] >= 420 && invaderBulletsY[w] <= 440)
						{	gameover = true;
							invaderBulletsY[w] = -20;


						}
					}

					count++;
					repaint();
				}
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			    }	

		        } 

         };

	
        


		score = new JLabel("SCORE: " + currentscore );
		score.setFont(score.getFont().deriveFont(14.0f));
		score.setForeground(Color.RED);
		panel.add(score);
		add(panel);
		setSize(700,600);
		
	

	    
	
}	


	public static void main(String[] args) {
		// TODO Auto-generated method 
		new SpaceInvaders();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 37) {
			userX -= 5;
		}else if(e.getKeyCode() == 39) {
			userX += 5;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 32) {
			userBulletsX[currentbullet] = userX + 36;
		userBulletsY[currentbullet] = 400;
		currentbullet++;
		repaint();
		}
		
	}

	public void updatescore() {
		score.setText("SCORE: "+ currentscore);
	}
}







