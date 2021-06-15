
import java.awt.image.BufferedImage;
/**Manages all the variables for the man, the myth, the legend, Player (protagonist)
 * 
 * @author Reilly
 *
 */
public class Player {
	
	private int PlayerX;
    private int PlayerY;
    private double PlayerXSpeed;
    private double PlayerYSpeed;
    private double PlayerYAcc;
    private int PlayerRestingY; //y value where he stops accelerating down
    private double PlayerLowerX;//bounds for back and forth movement
    private double PlayerUpperX;
    private boolean isJumping;
    private int PlayerMetaPhase;//determines which animation to run
    public static boolean canJump;
    private double PlayerXDirection;
    private boolean isDead;
    public static boolean ridingElephant;
    private Elephant elephant;
    public Player(){
    	PlayerX = 100;
        PlayerY = 350;
        PlayerXSpeed = 5;
        PlayerYSpeed = 0;
        PlayerYAcc = 0;
        PlayerRestingY = 350; //y value where he stops accelerating down
        PlayerLowerX = 64;//bounds for back and forth movement
        PlayerUpperX = 100;
        isJumping = false;
        PlayerMetaPhase = 1;//determines which animation to run
        canJump = true;
        PlayerXDirection =1;
        isDead = false;
        ridingElephant = false;
        elephant = null;
    }
    public double getResting()
    {
    	return PlayerRestingY;
    }
    public void setResting(int newRestSpot)
    {
    	PlayerRestingY = newRestSpot;
    }
    public void step(){
        //move the Player on the X axis
        //if the Player goes off the edge, bounce it by reversing its speed
        if (PlayerX < PlayerLowerX || PlayerX > PlayerUpperX)
        {
        	PlayerXDirection *= -1;
        }
        if (isJumping)
        {
        	PlayerXSpeed = 1;
        }
        PlayerX += PlayerXDirection*PlayerXSpeed; //makes him move
        PlayerY += PlayerYSpeed;
        PlayerYSpeed += PlayerYAcc;
        if(!isJumping&&!isDead && PlayerY > PlayerRestingY -30 && PlayerY < PlayerRestingY +40+(ridingElephant?80:0))//sets resting kinematics
        {
            PlayerYSpeed = 0;
            PlayerYAcc = 0;
            PlayerY = (int) PlayerRestingY;
            PlayerMetaPhase = 1;
            canJump=true;
            PlayerXSpeed = 5;
        }
        else { //sets airtime kinematics
        	PlayerYAcc = 1+((ridingElephant)?1:0);
        }
        if (RenderPanel.scoreTrail%40==39)
        {
        	ridingElephant = (ridingElephant)?false:true;
        	ManagerPanel.score++;
        }
        if (elephant != null)
        {
        	elephant.step();
        	if (elephant.getY()>600)
        	{
        		elephant = null;
        	}
        }
    }

    public BufferedImage getImage()
	{
        if (ridingElephant)
        {
        	int imageId = 150;
        	int limit = 1;
        	int speed = 2;
        	switch (PlayerMetaPhase)
        	{
        	case 1:
        		limit =4;
        		break;
        	case 2:
        		limit =4;
        		speed = 1;
        		break;
        	}
        	int moment = ManagerPanel.universalPhase/speed%(limit)+imageId;
    		return ManagerPanel.images[moment];
        }
        else
        {
        	int imageId =0;
        	int limit;
        	int speed = 2;
        	switch (PlayerMetaPhase)
        	{
        	case 1:
        		imageId = 88; //imageId for Player normal.png
        		limit = 4;
        		break;
        	case 2:
        		imageId = 92;//imageId for Player jumping.png
        		limit = 8;
        		break;
        	case 4:
        		imageId = 164;
        		limit = 7;
        		speed = 1;
        		break;
        	default:
        		imageId = 88;
        		limit = 1; //sets him still
        	}
        	int moment = ManagerPanel.universalPhase/speed%(limit)+imageId;
    		return ManagerPanel.images[moment];
        }
	}
    public int getX()
    {
    	return PlayerX;
    }
    public int getY()
    {
    	return PlayerY-((ridingElephant)?96:0);
    }
    public void jump() { //lets him jump
		if (!isDead&&canJump&&PlayerY>-40)
		{
			isJumping= true;
			PlayerYSpeed = -20-((ridingElephant)?10:0);
			PlayerMetaPhase = 2;
		}
		else
		{
			canJump=false; //prevents him jumping to infinity
			PlayerMetaPhase =4;
		}
		if (!canJump&&PlayerY>0&&ridingElephant)
		{
			canJump=true;
			ridingElephant = false;
			elephant = new Elephant(PlayerY+40,PlayerX);
			jump();
		}
	}
    public void setY(int y)
    {
    	PlayerY = y;
    }
    public Elephant getElephant()
    {
    	return elephant;
    }
    public void die() //sets his death kinematics
    {
    	if (!ridingElephant)
    	{
    		isDead = true;
    		canJump =false;
    		PlayerYSpeed = 25;
    		PlayerYAcc = 4;
    		PlayerMetaPhase=4;
    	}
    }
	public void jumpReleased() { //prevents double jump
		if (isJumping)	
		{
			PlayerMetaPhase = 0;
			isJumping = false;
			canJump = false;
		}
	}

}