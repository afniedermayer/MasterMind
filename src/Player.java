// December 23, 1996
// Andras Niedermayer

import java.awt.*;

class Player extends Frame implements Runnable
{
	boolean inApplet = true;

	Thread runner;

	allFactsPanel facts;
	Label status;
	Button Enter;

	final static int w = 300;
	final static int h = 500;
	final static int maxGuesses = 6;
	final static String QUIT  = "Quit";
	final static String ENTER = "Enter";
	final static String NEW   = "New";

	Thinker brain = new Thinker();

	public Player()
	{
		super("Master Mind Player");
		pack();
		setBackground(Color.lightGray);
		resize(w,h);

		add("North", new NumberStepsField() );

		Dimension d = new Dimension();
		d.width = size().width;
		d.height = 3*size().height/4;
		facts = new allFactsPanel(d, maxGuesses);
		add("Center", facts);

		Panel p = new Panel();
		status = new Label("PLEASE ANSWER.");
		p.add("North", status );

		p.add(new Button(QUIT));
		Enter = new Button(ENTER);
		p.add(Enter);
		p.add(new Button(NEW));
		add("South", p);

		show();

		start();
	}

	public void start()
	{
		System.out.println("Starting Thread");
		if( runner == null )
		{
			System.out.println("runner == null");
			runner = new Thread(this);
			runner.start();
		}
	}

	public void stop()
	{
		if( runner != null )
		{
			runner.stop();
			runner = null;
			System.out.println("Thread stopped");
		}
	}

	public void run()
	{
		System.out.println("Thread running");
		Guess bestGuess = new Guess();
		System.out.println("Thread just about making a guess");
		status.setText( "THINKING ... " );
		Enter.disable();
		int numPoss = brain.makeGuess( bestGuess );
		if( numPoss == 0 )
		{
			Warning youCheated = new Warning(this, "Scandal", "You cheated ! ! !", true);
//			renew();
			System.gc();
//			return;
		} else if( numPoss == 1 )
		{
			facts.addFact();
			facts.setGuess( bestGuess );
			facts.disable();
			Enter.disable();
			show();
			demonstrateGuess IveGotIt =
				new demonstrateGuess(this, "Really Good News", "I've got it ! ! !", bestGuess, true);
//			renew();
			System.gc();
//			return;
		} else {
			System.out.println(numPoss + " possibilities left");
			facts.addFact();
			facts.setGuess( bestGuess );
			show();
			facts.enableGuess( false );
			repaint();
		}
		Enter.enable();
		status.setText("PLEASE ANSWER.");
		runner = null;
	}

	public boolean action(Event evt, Object arg)
	{
		if( arg.equals(QUIT) || evt.id == Event.WINDOW_DESTROY )
		{
			dispose();
			if( inApplet == false )
				System.exit(0);
			return true;
		} else if( arg.equals(NEW) )
		{
			renew();
			return true;
		} else if( arg.equals(ENTER) )
		{
			Fact f = facts.getFact();

			if( f.getAnswer().allBlacks() )
			{
				demonstrateGuess IveGotIt =
					new demonstrateGuess(this, "Good News", "I've got it ! ! !", f.getGuess(), true);
				System.gc();
				return true;
			}

			brain.addFact( f );
			start();
			return true;
		}
		return super.handleEvent(evt);
	}

	void renew()
	{
		facts.clear();
		facts.enable();
		Enter.enable();
		stop();
		brain = new Thinker();
		start();
		show();
	}

	public static void main(String argv[])
	{
		new Player().inApplet = false;
	}
}

class Warning extends Dialog
{
	boolean showMe = true;
	static int h = 200;
	static int w = 400;

	String text;

	public Warning(Frame parent, String s, String text, boolean modal)
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
		if( showMe )
			show();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
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
/*			if( p instanceof Player )
			{
				Player pp = (Player) p;
				pp.renew();
			}
*/

			dispose();
			return true;
		}
		return false;
	}
}

class demonstrateGuess extends Warning
{
	boolean showMe = false;

	public demonstrateGuess(Frame parent, String s, String text, Guess g, boolean modal)
	{
		super(parent,s,text,modal);
		Dimension d = new Dimension(3*w/4, h/4);
		GuessPanel gp = new GuessPanel(d);
		gp.setGuess(g);
		add("North", gp);
		gp.disable();
		show();
	}
}
