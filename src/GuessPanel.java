// December 13, 1996
// Andras Niedermayer

import java.awt.*;

class GuessPanel extends Panel
{
	static boolean Circloids = true;

	GuessButton buttons[];
	int h;
	int w;

	public GuessPanel(Dimension d)
	{
		super();
		h = d.height;
		w = d.width;
		buttons = new GuessButton[Guess.NumGuesses];
		for( int i = 0; i<Guess.NumGuesses; i++ )
		{
			buttons[i] = new GuessButton();
			System.out.println("resizing Button from in GuessPanel constructor");
			resizeButton(buttons[i]);
			add("Center", buttons[i]);
		}
		show();
	}

	void resizeButton(GuessButton gb)
	{
		if( Circloids == false )
			gb.resize(w/Guess.NumGuesses, h);
		else
		{
			int minWidth = w/Guess.NumGuesses;
			int sideLength = Math.min(minWidth,h);
			gb.resize(sideLength,sideLength);
		}
	}

	public void setGuess(Guess g)
	{
		for( int i = 0; i<Guess.NumGuesses; i++ )
			buttons[i].setColor( g.getPeg(i) );
		repaint();
	}

	public Guess getGuess()
	{
		Guess g = new Guess();
		for( int i = 0; i<Guess.NumGuesses; i++ )
			g.setPeg(i, buttons[i].getColor() );
		return g;
	}

	public boolean mouseDown(Event e, int x, int y)
	{
//		System.out.println("Guess = " + getGuess() );
//		System.out.println("GuessPanel size: " + size() );
//		System.out.println("preferred size: " + preferredSize() );
//		System.out.println("minimum size: " + minimumSize() );
		return false;
	}
}

