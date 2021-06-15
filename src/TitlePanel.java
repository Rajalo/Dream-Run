import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
/**Similar to EndPanel, does the title card
 * 
 * @author Raghallaigh
 *
 */
public class TitlePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Background background = new Background();
	private Title title = new Title();
	private GreenButton gbutton = new GreenButton();
	private RedButton rbutton = new RedButton();
	public static int phase = 0; //same as End panel
	private int phaseDirection =0;
	public TitlePanel(){
        setBackground(Color.BLUE);
    }
	public void paintComponent(Graphics g){ //draws screen
        super.paintComponent(g);
   
		ImageObserver paintingChild = null;
		g.drawImage(background.getImage(), 0, 0, paintingChild);
		g.drawImage(title.getImage(), 250, 0, paintingChild);
		g.drawImage(gbutton.getImage(), 300, 400, paintingChild);
		g.drawImage(rbutton.getImage(), 650, 400, paintingChild);

    }

	public void step() { //steps through it all
		repaint();
		if (phase<-5) //closes game
		{
			System.exit(ABORT);
		}
		if (phase > 5) //starts game
		{
			phase = 0;
			ManagerPanel.nextState = true;
		}
		phase += phaseDirection;
	}

	public void mouseClicked(MouseEvent e) { //senses button presses
		int x=e.getX();
	    int y=e.getY();
		if (Math.abs(x-364)<64&&Math.abs(y-464)<50)
		{
			gbutton.press();
			phaseDirection = 1;
		}
		if (Math.abs(x-714)<64&&Math.abs(y-464)<50)
		{
			rbutton.press();
			phaseDirection = -1;
		}
	}
}
