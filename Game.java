import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	static int x = 50;
    static int y = 50;
    static int score = 0;
    static int xSpeed = (int)(Math.random() * 3) + 5;
    static int ySpeed = (int)(Math.random() * 3) + 5;
    static boolean negativeY = false;
    static boolean negativeX = false;
    static Ellipse2D oval = new Ellipse2D.Double(x, y, 40, 40);
    static JLabel scoreDisplay = new JLabel("Score: " + String.valueOf(score));
    
    static class MouseEvents implements MouseListener {
    	public static String color = "red";
		
		public void mousePressed(MouseEvent e) {
			if (oval.contains(e.getX(), e.getY())) {
				double randint = Math.random();
					
				if (color == "red") {
					if (randint <= 0.6) {
						color = "blue";
					} else {
						color = "green";
					}
				} else if (color == "blue") {
					if (randint <= 0.6) {
						color = "red";
					} else {
						color = "blue";
					}					
				} else {
					if (randint <= 0.6) {
						color = "red";
					} else {
						color = "blue";
					}
				}
				
				score++;
	        	scoreDisplay.setText("Score: " + String.valueOf(score));
			}
		}
		
		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}
		
		public void mouseExited(MouseEvent e) {
			
		}
		
		public void mouseClicked(MouseEvent e) {
			
		}
	}

    static MouseEvents mouse = new MouseEvents();
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (MouseEvents.color == "red") {
        	g2d.setColor(Color.RED);
        } else if (MouseEvents.color == "blue") {
        	g2d.setColor(Color.BLUE);
        } else {
        	g2d.setColor(Color.GREEN);        	
        }
        
        oval = new Ellipse2D.Double(x, y, 40, 40);
        g2d.fill(oval);
    }
    
    public static void main(String[] args) throws InterruptedException {
        scoreDisplay.setFont(new Font("Verdana", 1, 25));
    	JFrame frame = new JFrame("Sample Frame");
    	frame.addMouseListener(mouse);
        Game game = new Game();
        frame.add(game);
        game.add(scoreDisplay);
        int frameWidth = 900;
        int frameHeight = 900;
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (game.getWidth() > 50 && game.getHeight() > 50) {
        	frameWidth--;
        	frameHeight--;
        	frame.setSize(frameWidth, frameHeight);
        	
        	if (x >= game.getWidth() || negativeX == true) {
        		if (x >= game.getWidth()) {
        			xSpeed = ((int)(Math.random() * 3) + 5);
        		}
        		
        		negativeX = true;
        		x -= xSpeed;
        	} 
        	if (x <= 0 || negativeX == false) {
        		if (x <= 0) {
        			xSpeed = (int)(Math.random() * 3) + 5;
        		}
        		
        		negativeX = false;
        		x += xSpeed;
        	} 
        	if (y >= game.getHeight() || negativeY == true) {
        		if (y >= game.getHeight()) {
        			ySpeed = (int)(Math.random() * 3) + 5;
        		}
        		
        		negativeY = true;
        		y -= ySpeed;
        	}
        	if (y <= 0 || negativeY == false) {
        		if (y <= 0) {
        			ySpeed = (int)(Math.random() * 3) + 5;
        		}
        		
        		negativeY = false;
        		y += ySpeed;
        	}
        	
        	game.repaint();
            Thread.sleep(10);
        }
        
        frame.removeMouseListener(mouse);;
    }
}