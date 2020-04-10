// November 19, 1996
// by Andras Niedermayer

import java.util.Vector;

class Fact
{
	private Guess fGuess;
	private Answer fAnswer;

	public Fact(int newPegs[], int b, int w)
	{
		fGuess = new Guess(newPegs);
		fAnswer = new Answer(b,w);
	}

	public Fact()
	{
		fGuess = new Guess();
		fAnswer = new Answer();
	}

	public Fact(Guess g, Answer a)
	{
		fGuess = g;
		fAnswer = a;
	}

	public static boolean allowsGuess(Fact f, Guess g)
	{
		Answer a = new Answer();
		a.compare(f.fGuess,g);
		return a.equals(f.fAnswer);
	}

	public static boolean allowGuess(Vector factVector, Guess g)
	{
		for( int i = 0; i<factVector.size(); i++ )
		{
			Object o = factVector.elementAt(i);
			if( o instanceof Fact)
			{
				Fact f = (Fact)o;
				if( allowsGuess(f,g) == false )
					return false;
			}
		}
		return true;
	}

	public Guess getGuess()
	{
		return fGuess;
	}

	public Answer getAnswer()
	{
		return fAnswer;
	}

	public String toString()
	{
		return "Guess: " + fGuess + " Answer: " + fAnswer;
	}

	public boolean equals(Fact f)
	{
		return fGuess.equals(f.fGuess) && fAnswer.equals(f.fAnswer);
	}
}
