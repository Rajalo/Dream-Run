import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
/**Manages which JPanel should be shown, such as the game or the beginning animation
 * 
 * @author Reilly
 *
 */
public class ManagerPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private int state = 0;
	public static boolean nextState= false;
	private TitlePanel titlePanel = new TitlePanel();
	private RenderPanel renderPanel;//stores the playable game
	public static int score;//keeps track of the score for use in the endscreen and game
	private CardLayout cardLayout;
	private EndPanel endPanel;
	private AnimationPanel animePanel;
	public static int universalPhase = 0;
	//This down here is magical, keeps all the strings with the file names
	public static String[] imagePaths = {"/1under1.png","/1under2.png","/1under3.png","/1under4.png","/1under5.png","/1under6.png","/1under7.png","/1under8.png","/2under1.png","/2under2.png","/2under3.png","/2under4.png","/2under5.png","/2under6.png","/2under7.png","/2under8.png","/3under1.png","/3under2.png","/3under3.png","/3under4.png","/3under5.png","/3under6.png","/3under7.png","/3under8.png","/backunder1.png","/backunder2.png","/backunder3.png","/backunder4.png","/backunder5.png","/backunder6.png","/backunder7.png","/backunder8.png","/Walking Willow1.png","/Walking Willow2.png","/Walking Willow3.png","/Walking Willow4.png","/City1.png","/City2.png","/City3.png","/City4.png","/City5.png","/City6.png","/City7.png","/City8.png","/City9.png","/City10.png","/City11.png","/City12.png","/endscreen1.png","/endscreen2.png","/endscreen3.png","/endscreen4.png","/Exit button off1.png","/Exit button off2.png","/Exit button off3.png","/Exit button off4.png","/Exit button off5.png","/Exit button on1.png","/Exit button on2.png","/Exit button on3.png","/Exit button on4.png","/Exit button on5.png","/frontunder1.png","/frontunder2.png","/frontunder3.png","/frontunder4.png","/frontunder5.png","/frontunder6.png","/frontunder7.png","/frontunder8.png","/Goblin1.png","/Goblin2.png","/Goblin3.png","/Goblin4.png","/Go button off1.png","/Go button off2.png","/Go button off3.png","/Go button off4.png","/Go button off5.png","/Go button on1.png","/Go button on2.png","/Go button on3.png","/Go button on4.png","/Go button on5.png","/jacket1.png","/jacket2.png","/jacket3.png","/jacket4.png","/Player normal1.png","/Player normal2.png","/Player normal3.png","/Player normal4.png","/Player Jump1.png","/Player Jump2.png","/Player Jump3.png","/Player Jump4.png","/Player Jump5.png","/Player Jump6.png","/Player Jump7.png","/Player Jump8.png","/LongWhipper1.png","/LongWhipper2.png","/LongWhipper3.png","/LongWhipper4.png","/medo1.png","/medo2.png","/medo3.png","/medo4.png","/platformback1.png","/platformback2.png","/platformback3.png","/platformback4.png","/platformback5.png","/platformback6.png","/platformback7.png","/platformback8.png","/platformfront1.png","/platformfront2.png","/platformfront3.png","/platformfront4.png","/platformfront5.png","/platformfront6.png","/platformfront7.png","/platformfront8.png","/platformmiddle1.png","/platformmiddle2.png","/platformmiddle3.png","/platformmiddle4.png","/platformmiddle5.png","/platformmiddle6.png","/platformmiddle7.png","/platformmiddle8.png","/Spacebar1.png","/Spacebar2.png","/Spacebar3.png","/Spacebar4.png","/Spacebar5.png","/Spacebar6.png","/thermo1.png","/thermo2.png","/thermo3.png","/thermo4.png","/Title1.png","/Title2.png","/Title3.png","/Title4.png","/Whippersnapper1.png","/Whippersnapper2.png","/Whippersnapper3.png","/Whippersnapper4.png","/green elephant1.png" , "/green elephant2.png" , "/green elephant3.png" , "/green elephant4.png" , "/green elephant5.png" , "/green elephant6.png","/ice cream4.png","/ice cream1.png","/ice cream2.png","/ice cream3.png","/pepper1.png","/pepper2.png","/pepper3.png","/pepper4.png","/Player dabbathon1.png","/Player dabbathon2.png","/Player dabbathon3.png","/Player dabbathon4.png","/Player dabbathon5.png","/Player dabbathon6.png","/Player dabbathon7.png","/jeff1.png","/jeff2.png","/jeff3.png","/jeff4.png","/box6.png","/box1.png","/box2.png","/box3.png","/box4.png","/box5.png","/ejected elephant1.png","/ejected elephant2.png","/ejected elephant3.png"};
	public static BufferedImage[] images = new BufferedImage[184];
	public ManagerPanel() throws IOException
	{
		this.setLayout(new CardLayout());
        this.add(titlePanel, "Title");
		cardLayout = (CardLayout) this.getLayout();
		cardLayout.show(this, "Title");
		addMouseListener(this);//sets up all the listeners
		setFocusable(true);
		addKeyListener(this);
		Timer timer = new Timer(1000/30, this);
        timer.start();
        for (int i =0; i < 184; i++)//loads all the images for use (this is what made it super fast)
        {
        	BufferedImage image = null;
    		try {
    			System.out.println(imagePaths[i]);
    			URL url= getClass().getResource(imagePaths[i]);
    			image = ImageIO.read(url);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		images[i] = image;
        }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (nextState) //switches panel
		{
			state = (state+1)%4;
			switch (state)
			{
				case 0:
					titlePanel = new TitlePanel();
					this.add(titlePanel, "Title");
					cardLayout = (CardLayout) this.getLayout();
					cardLayout.show(this, "Title");
					break;
				case 1:
					animePanel = new AnimationPanel();
					this.add(animePanel, "Anime");
					cardLayout = (CardLayout) this.getLayout();
					cardLayout.show(this, "Anime");
					titlePanel = null;
					System.gc();
					break;
				case 2:
					renderPanel = new RenderPanel();
					this.add(renderPanel, "Game");
					cardLayout = (CardLayout) this.getLayout();
					cardLayout.show(this, "Game");
					animePanel = null;
					titlePanel = null;
					endPanel = null;
					System.gc();
					score = 0;
					break;
				case 3:
					renderPanel.Player.ridingElephant=false;
					endPanel = new EndPanel();
					this.add(endPanel, "End");
					cardLayout = (CardLayout) this.getLayout();
					cardLayout.show(this, "End");
					break;
			}
			nextState = false;
		}
		else {
			switch (state)//steps through current panel
			{
				case 0:
					titlePanel.step();
					break;
				case 1:
					animePanel.step();
					break;
				case 2:
					renderPanel.step();
					break;
				case 3:
					endPanel.step();
					break;
			}
			universalPhase++;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) { //sends MouseEvent to the current screen
		if (state == 0)
		{
			titlePanel.mouseClicked(e);
		}
		if (state == 3)
		{
			endPanel.mouseClicked(e);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (state == 2)//state 2 is the game, sends jump info
		{
			renderPanel.keyPressed(e);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (state == 2)
		{
			renderPanel.keyReleased(e)  ;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
