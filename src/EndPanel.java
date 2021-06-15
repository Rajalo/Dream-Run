import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
/**This class renders the End screen
 * 
 * @author Reilly
 *
 */
public class EndPanel extends JPanel {

	private static final long serialVersionUID = 1L;//Whenever you see this, it's because eclipse told me to do it.
	private Background background = new Background();
	private EndScreen endScreen = new EndScreen();
	private GreenButton gbutton = new GreenButton();
	private RedButton rbutton = new RedButton();
	private int phase = 0; //this bad boy let's us run an animation for the button clicking before doing the action
	private int phaseDirection =0; //this determines which action is enacted based on the button that is pressed
	private int textJump = 0; //just a fun little variable to make the score jump like the backgrounds
	private int[] textJumpLength = {0,5,10,15,7}; //these are the heights for that jump
	public EndPanel(){
        setBackground(Color.BLUE);
        setFocusable(true);
    }
	public void paintComponent(Graphics g){
        super.paintComponent(g);
   
		ImageObserver paintingChild = null;
		
		g.setColor(Color.RED); //readies  the text
	    g.setFont(new Font("Courier New",Font.BOLD,70));
	    
		g.drawImage(background.getImage(), 0, 0, paintingChild); //draws everything
		g.drawImage(endScreen.getImage(), 350, 30, paintingChild);
		g.drawImage(gbutton.getImage(), 450, 300, paintingChild);
		g.drawImage(rbutton.getImage(), 630, 300, paintingChild);
		
		g.drawString(ManagerPanel.score+"!", 480, 175-textJumpLength[textJump]);//writes the score

    }

	public void step() {
		textJump = (textJump+1)%5;
		repaint();
		if (phase<-5) //closes the game (there is an analagous one in TitlePanel)
		{
			System.exit(ABORT);
		}
		if (phase > 5)//starts the game
		{
			phase = 0;
			ManagerPanel.nextState = true;
		}
		phase += phaseDirection;
	}

	public void mouseClicked(MouseEvent e) { //takes MousEvent from ManagerPanel and determines whether a button was pressed
		int x=e.getX();
	    int y=e.getY();
		if (Math.abs(x-514)<64&&Math.abs(y-364)<50)
		{
			gbutton.press();
			phaseDirection = 1;
		}
		if (Math.abs(x-694)<64&&Math.abs(y-364)<50)
		{
			rbutton.press();
			phaseDirection = -1;
		}
	}
}
