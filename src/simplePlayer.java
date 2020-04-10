// December 19, 1996
// Andras Niedermayer

import java.awt.*;
import java.applet.*;

class simplePlayer extends Frame
{
	boolean inApplet = true;

	allFactsPanel facts;
	static int w = 400;
	static int h = 400;
	int maxFacts = 11;
	Guess computerGuess;
	Frame cheatFrame = null;

	public simplePlayer()
	{
		super("Master Mind");
		pack();
		setBackground(Color.lightGray);
		resize(300,500);

		Panel buttons = new Panel();
		buttons.add( new Button("Quit")  );
		buttons.add( new Button("New")   );
		buttons.add( new Button("Enter") );
		buttons.add( new Button("Cheat") );
		add("South", buttons);

//		System.out.println("Applet size: " + size() );
		facts = new allFactsPanel(size(), maxFacts);
		facts.addFact();
		facts.enableAnswer(false);
		add("Center", facts );

		show();

		computerGuess = new Guess();
		computerGuess.setRandom();
	}

	public boolean action(Event evt, Object arg)
	{
		if( arg.equals("Quit") )
		{
			if( cheatFrame != null )
				cheatFrame.dispose();
			dispose();
			if( inApplet == false )
				System.exit(0);
			return true;
		} else if( arg.equals("New") )
		{
			renew();
			return true;
		} else if( arg.equals("Enter") )
		{
			giveAnswer();
			return true;
		} else if( arg.equals("Cheat") )
		{
			cheat();
			return true;
		}
		return super.handleEvent(evt);
	}

	public boolean handleEvent(Event evt)
	{
		switch(evt.id)
		{
			case Event.WINDOW_DESTROY:
			{
				if( cheatFrame != null )
					cheatFrame.dispose();
				dispose();
				System.exit(0);
				return true;
			}
			default:
				return super.handleEvent(evt);
		}
	}

	void cheat()
	{
		if( cheatFrame != null )    // cheatFrame has already been created
			return;
		cheatFrame = new Frame("Combination to be guessed");
		cheatFrame.pack();
		cheatFrame.resize( 200, 100 );
		Dimension d = new Dimension(150,80);
		GuessPanel gp = new GuessPanel( d );
		gp.setGuess( computerGuess );
		cheatFrame.add("Center", gp);
		gp.disable();
		cheatFrame.show();
	}

	void renew()
	{
		facts.clear();  // = new allFactsPanel(size(), maxFacts);
		facts.addFact();
		facts.enableAnswer(false);
		show();
		computerGuess.setRandom();

		if( cheatFrame != null )
		{
			cheatFrame.dispose();
			cheatFrame = null;
		}
	}

	void giveAnswer()
	{
		Guess userGuess = facts.getFact().getGuess();

		Answer computerAnswer = new Answer();
		computerAnswer.compare( computerGuess, userGuess );

		System.out.println("computerGuess: " + computerGuess  );
		System.out.println("userGuess: " + userGuess );
		System.out.println("computerAnswer: " + computerAnswer );

		facts.setAnswer( computerAnswer );
		facts.enableAnswer( true );

		if( computerAnswer.allBlacks() )
		{
			(new youVeGotIt(this, "Information", "You've got it right ! ! !", true)).show();
//			renew();
			return;
		}

		show();
//		System.out.println("currentPosition of facts.latestFact: " + facts.getFact() );

		facts.addFact();
		facts.enableAnswer( false );

		show();

		facts.repaint();
	}

	public static void main( String argv[] )
	{
/*		Frame f = new Frame("Master Mind");
		f.pack();
		f.resize(w,h);
		simplePlayer sP = new simplePlayer();
		sP.resize(w, 3*h/4);
		f.add("Center", sP);
		f.show();
		sP.init();
*/
		new simplePlayer().inApplet = false;
	}
}

class youVeGotIt extends Dialog
{
	static int h = 200;
	static int w = 300;

	String text;

	public youVeGotIt(Frame parent, String s, String text, boolean modal)
	{
		super(parent, s, modal);
//		this.parent = parent;
		if( modal )
			parent.disable();
		this.text = text;
		Panel p = new Panel();
		p.add( new Button("OK") );
		add( "South", p );
		resize(w, h);
	}

	public void paint(Graphics g)
	{
		g.setFont( new Font("Times New Roman", Font.BOLD, 16) );
		g.setColor( Color.red );
		g.drawString( text, 80, 90 );
	}

	public boolean action(Event evt, Object arg)
	{
		if( arg.equals("OK") )
		{
			Container p = getParent();
			p.enable();
			if( p instanceof simplePlayer )
			{
				simplePlayer spp = (simplePlayer) p;
				spp.renew();
			}
			dispose();
			return true;
		}
		return false;
	}
}

