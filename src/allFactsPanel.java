// December 18, 1996
// Andras Niedermayer

import java.awt.*;
import java.util.Vector;

class allFactsPanel extends Panel
{
	Vector allFacts = new Vector();
	FactPanel latestFact = null;
	int w, h;
	int nPosGuesses;

	public allFactsPanel( Dimension d, int npg )
	{
		super();
		nPosGuesses = npg;
		w = d.width;
		h = d.height;

		show();
	}

	public void addFact(Fact f)
	{
		if( latestFact != null )
			latestFact.disable();
//		System.out.println("allFactsPanel: w,h = " + w + " " + h);
		latestFact = new FactPanel(new Dimension(w,h/nPosGuesses));
		latestFact.setFact(f);
		allFacts.addElement(latestFact);
		add(latestFact);
		System.out.println("latestFact isShowing " + latestFact.isShowing() +
								 " isEnabled " + latestFact.isEnabled() +
								 " isValid " + latestFact.isValid() );

	}

	public void addFact()
	{
		addFact(new Fact());
	}

	public Fact getFact()
	{
		if( latestFact == null )
			return null;
		return latestFact.getFact();
	}

	public void setAnswer( Answer a )
	{
//		System.out.println( "setting Answer: " + a );
		if( latestFact != null )
			latestFact.setAnswer( a );
	}

	public void setGuess( Guess g )
	{
		if( latestFact != null )
			latestFact.setGuess( g );
	}

	public void setFact( Fact f )
	{
		if( latestFact != null )
			latestFact.setFact( f );
	}

	public void showAnswer( boolean b )
	{
		if( latestFact != null )
			latestFact.showAnswer(b);
	}

	public void showGuess( boolean b )
	{
		if( latestFact != null )
			latestFact.showGuess(b);
	}

	public void enableAnswer( boolean b )
	{
		if( latestFact != null )
			latestFact.enableAnswer(b);
	}

	public void enableGuess( boolean b )
	{
		if( latestFact != null )
			latestFact.enableGuess(b);
	}

	public void clear()
	{
		allFacts.removeAllElements();
		latestFact = null;
		removeAll();
	}
}
