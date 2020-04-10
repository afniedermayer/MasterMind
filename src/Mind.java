// December 22, 1996
// Andras Niedermayer

import java.applet.*;
import java.awt.Button;
import java.awt.Event;

public class Mind extends Applet
{
	public final static String BUTTON1 = "Guess Yourself";
	public final static String BUTTON2 = "Let Computer Guess";

	simplePlayer sP = null;
	Player P = null;

	public void init()
	{
		add("North", new Button(BUTTON1) );
		add("South", new Button(BUTTON2) );
	}

	public void destroy()
	{
		if( sP != null )
		{
			sP.dispose();
			sP = null;
		}
		if( P != null )
		{
			P.dispose();
			P = null;
		}
	}

	public boolean action(Event evt, Object arg)
	{
		if( arg.equals(BUTTON1) )
		{
			sP = new simplePlayer();
			return true;
		} else if( arg.equals(BUTTON2) )
		{
			P = new Player();
			return true;
		}
		return false;
	}
}


