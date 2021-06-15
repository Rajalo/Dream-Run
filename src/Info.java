import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Info {
	private int iceCreams;
	private int peppers;
	public Info()
	{
		iceCreams = 0;
		peppers = 0;
	}
	public void paintInfo(Graphics g)
	{
		g.setColor(Color.WHITE); //readies  the text
	    g.setFont(new Font("Courier New",Font.BOLD,30));
		g.drawString(ManagerPanel.score+"!", 20, 470);
		g.setColor(Color.GREEN);
		g.drawString("x"+ PlatformGenerator.scoreMultiplier, 20, 500);
		g.drawImage(ManagerPanel.images[ManagerPanel.universalPhase%6+175],120,520,null);
		g.drawImage(ManagerPanel.images[ManagerPanel.universalPhase%6+175],20,520,null);
		int moment = ((peppers>iceCreams)?160:156)+ManagerPanel.universalPhase/2%4;
		if (peppers+iceCreams>0)
		{
			for (int i =0; i < 2-(peppers+iceCreams)%2;i++)
			{
				g.drawImage(ManagerPanel.images[moment], 20+100*i +((peppers>iceCreams)?5:0), 510 +((peppers>iceCreams)?10:0),null);
			}
		}
	}
	public boolean getHelper()
	{
		return (iceCreams + peppers >= 2);
	}
	public void addIceCream()
	{
		iceCreams++;
		peppers = 0;
	}
	public void addPepper()
	{
		peppers++;
		iceCreams = 0;
	}
	public void summon()
	{
		iceCreams -= (iceCreams>=2)?2:0;
		peppers -= (peppers>=2)?2:0;
	}
}
