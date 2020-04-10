// Thinker.java
// December 12, 1996
// by Andras Niedermayer

import java.util.*;

class Thinker
{
	static boolean faster = true;
	final static int minTestGuesses = 40;
	public static int maxSteps = 50000;

	Vector knownFacts = new Vector();

	boolean ready = false;  // two variables for multithreading
	int numPoss;

	public boolean isReady()
	{
		return ready;
	}

	public int numPoss()
	{
		return numPoss;
	}

	private int random(int i)
	{
		int r = (int)Math.floor( i*Math.random() );
		return (r == i) ? (i-1) : r ;
	}

	public int makeGuess(Guess Question)
	{
		ready = false;

		Vector possibleGuesses = new Vector();
		FreqL FL = new FreqL();

		if( knownFacts.size() == 0 && GuessColors.NumColors>=Guess.NumGuesses )
		{
			System.out.println("Thinker making random choice");
			for( int si = 0; si<Guess.NumGuesses; si++ )
			{
				int rn = random( GuessColors.NumColors-si );
				int col = 0;
				for( int j = -1; j<rn; j++, col++ )
					for( int k = 0; k<si; k++ )
						if( Question.getPeg(k) == col )
							col++;
				Question.setPeg(si,--col);
			}
			numPoss = GuessEnumerator.NumAllPos();
			return numPoss;
		}


		System.out.println("Thinker choosing possible guesses");

		for( GuessEnumerator GE = new GuessEnumerator(); GE.hasMoreElements(); )
		{
			Guess helpGuess = (Guess)GE.nextElement();
			if( knownFacts.size() == 0 )
				possibleGuesses.addElement(helpGuess);
			else if( Fact.allowGuess(knownFacts,helpGuess) )
				possibleGuesses.addElement(helpGuess);
		}

		if( possibleGuesses.size() == 0 )
			return 0;
		if( possibleGuesses.size() == 1 )
		{
			Question.copy((Guess)possibleGuesses.firstElement());
			return 1;
		}


		System.out.println("Number of possible guesses: " + possibleGuesses.size() );
//		System.out.println("Possible guesses: " + possibleGuesses );
		System.out.println("Thinker searching for best guess");

		numPoss = possibleGuesses.size();
		Question.copy((Guess)possibleGuesses.firstElement());
		ready = true;

		Guess maxQuestion = null;
		double maxValue = 0;
		double infoValue;

		Enumeration GE;
		int estSteps = GuessEnumerator.NumAllPos()*possibleGuesses.size();
		if( estSteps < maxSteps )
			GE = new GuessEnumerator();
		else
		{
			int testGuesses = maxSteps / possibleGuesses.size();
			testGuesses = Math.max( minTestGuesses, testGuesses );
			System.out.println("Number of testGuesses: " + testGuesses);
			GE = new randomEnumerator(new GuessEnumerator(),
											  GuessEnumerator.NumAllPos(), testGuesses );
		}

		for( ; GE.hasMoreElements(); )
		{
			FL.Clear();
			Guess helpGuess = (Guess)GE.nextElement();
			Answer helpAnswer = new Answer();
			for( Enumeration posE = possibleGuesses.elements(); posE.hasMoreElements(); )
			{
				Guess posGuess = (Guess)posE.nextElement();
				helpAnswer.compare(helpGuess, posGuess);
				FL.IncrP(helpAnswer);
			}
			infoValue = FL.infoValue();
			if( maxQuestion == null )
			{
				maxQuestion = (Guess)helpGuess;
				maxValue = infoValue;
//				System.out.println("First: " + maxQuestion + maxValue );
			} else if(  ( (FL.allBlacks()>0)  ?  (infoValue>=maxValue) :
														  (infoValue>maxValue) ) )
			{
				maxQuestion = (Guess)helpGuess;
				maxValue = infoValue;
//				System.out.println("Best yet: " + maxQuestion + maxValue );
			}
			Question.copy(maxQuestion);
		}
		Question.copy(maxQuestion);
		return possibleGuesses.size();
	}

	public void addFact(Fact f)
	{
		knownFacts.addElement( f );
	}

	public void clear()
	{
		knownFacts.removeAllElements();
	}

	public static void timeTest
		(Enumeration posE, Guess helpGuess, Answer helpAnswer, FreqL FL)
	{
		Guess posGuess = (Guess)posE.nextElement();
		helpAnswer.compare(helpGuess, posGuess);
		FL.IncrP(helpAnswer);
	}

}

class GuessEnumerator implements Enumeration
{
	int Position[];
	boolean hasMore = true;
	Guess rGuess = new Guess();

	public GuessEnumerator()
	{
		Position = new int[Guess.NumGuesses];
		for( int i = 0; i<Guess.NumGuesses; i++ )
			Position[i] = 0;
	}

	public boolean hasMoreElements()
	{
		return hasMore;
	}

	public Object nextElement()
	{
		rGuess = new Guess(Position);
		Incr(0);
		return rGuess;
	}

	void Incr(int p)
	{
		if( Position[p] == GuessColors.NumColors-1 )
		{
			if( p>=Guess.NumGuesses-1 )
				hasMore = false;
			else
			{
				Position[p] = 0;
				Incr(p+1);
			}
		} else
			Position[p]++;
	}

	public static int NumAllPos()
	{
		return (int)Math.pow( GuessColors.NumColors, Guess.NumGuesses );
	}
}

class randomEnumeration implements Enumeration
{
	int curPosition = 0;
	Vector basisVector;
	int numStepsLeft;

	public randomEnumeration(Vector basisVector, int numStepsLeft)
	{
//		System.out.println("randomEnumeration started");
		this.basisVector = basisVector;
		this.numStepsLeft = numStepsLeft;

		if( numStepsLeft > basisVector.size() )
		{
			System.out.println("randomEnumeration: bad construction parameters");
			this.numStepsLeft = basisVector.size();
		}
	}

	public boolean hasMoreElements()
	{
		return numStepsLeft>0;  // curPosition < basisVector.size();
	}

	public Object nextElement()
	{
		int index = (int)Math.floor( basisVector.size() * Math.random() );
		if( index == basisVector.size() )
			index--;
		numStepsLeft--;
		return basisVector.elementAt(index);
	}
}
