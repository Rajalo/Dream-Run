import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.net.URL;

import javax.sound.sampled.*;
/**Its the main method bro
 * 
 * @author Raghallaigh
 *
 */
public class Main{

	public static JFrame frame = new JFrame("Dream Run");
	public static Clip clip;
	
    public static void main(String[] args) throws Throwable, Throwable {
    	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        ManagerPanel panel = new ManagerPanel(); //makes the JPanel that runs the game (I couldn't get cards to work with JFrame)
        frame.add(panel, BorderLayout.CENTER);
		
		try { //loops 8-bit Livin La Vida Loca, Credit to 8BitUzz for making this
			URL url= Main.class.getResource("music.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        clip.setLoopPoints(0, -1);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
        frame.setSize(1200, 675);
        frame.setVisible(true);
    }
}//