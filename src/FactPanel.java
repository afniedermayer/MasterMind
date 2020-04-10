// December 16, 1996
// Andras Niedermayer

import java.awt.*;

class FactPanel extends Panel
{
	GuessPanel gPanel;
	AnswerPanel aPanel;

	public FactPanel(Dimension d)
	{
		super();

//		System.out.println("constructing FactPanel with d = " + d);

		Dimension gSize = new Dimension( d.width/2, d.height );
//		System.out.println("constructing GuessPanel with:" + gSize);
		gPanel = new GuessPanel(gSize);

		Dimension aSize = new Dimension( d.width/4, 3*d.height/4 );
//		System.out.println("constructing AnswerPanel with " + aSize);
		aPanel = new AnswerPanel(aSize);

//		System.out.println("adding Panels");
		add(gPanel);
		add(aPanel);
	}

	public Guess getGuess()
	{
		return gPanel.getGuess();
	}

	public Answer getAnswer()
	{
		return aPanel.getAnswer();
	}

	public Fact getFact()
	{
		return new Fact( gPanel.getGuess(), aPanel.getAnswer() );
	}

	public void setGuess( Guess g )
	{
		gPanel.setGuess( g );
	}

	public void setAnswer( Answer a )
	{
		aPanel.setAnswer( a );
		aPanel.repaint();
	}

	public void setFact(Fact f)
	{
		gPanel.setGuess( f.getGuess() );
		aPanel.setAnswer( f.getAnswer() );
	}

	public void showAnswer(boolean b)
	{
		aPanel.show(b);
	}

	public void showGuess(boolean b)
	{
		gPanel.show(b);
	}

	public void enableAnswer(boolean b)
	{
		aPanel.enable(b);
	}

	public void enableGuess(boolean b)
	{
		gPanel.enable(b);
	}
}

