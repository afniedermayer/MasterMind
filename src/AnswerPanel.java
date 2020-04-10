// December 16, 1996
// Andras Niedermayer

import java.awt.*;

class AnswerPanel extends Panel
{
	static boolean Circloids = true;

	AnswerButton buttons[];
	int h;
	int w;

	public AnswerPanel(Dimension d)
	{
		super();
		h = d.height;
		w = d.width;
		buttons = new AnswerButton[Guess.NumGuesses];
		for( int i = 0; i<Guess.NumGuesses; i++ )
		{
			buttons[i] = new AnswerButton();
			resizeButton(buttons[i]);
			add("Center", buttons[i]);
		}
		show();
	}

	public void setAnswer(Answer a)
	{
		int i = 0;
		int Blacks = a.getBlacks();
		int Whites = a.getWhites();

		for(; Blacks>0; Blacks-- )
			buttons[i++].setBlack();

		for(; Whites>0; Whites-- )
			buttons[i++].setWhite();

		while( i<Guess.NumGuesses )
			buttons[i++].setEmpty();

		repaint();
	}

	public Answer getAnswer()
	{
		int Blacks = 0;
		int Whites = 0;

		for( int i = 0; i<Guess.NumGuesses; i++ )
			if( buttons[i].isBlack() )
				Blacks++;
			else if( buttons[i].isWhite() )
				Whites++;

		return new Answer(Blacks, Whites);
	}

	void resizeButton(AnswerButton ab)
	{
		if( Circloids == false )
			ab.resize(w/Guess.NumGuesses, h);
		else
		{
			int minWidth = w/Guess.NumGuesses;
			int sideLength = Math.min(minWidth,h);
			ab.resize(sideLength,sideLength);
		}
	}

	public boolean mouseDown(Event e, int x, int y)
	{
//		System.out.println("Answer yet: " + getAnswer() );
		return false;
	}
}

