// randomEnumerator
// January 19, 1997
// by Andras Niedermayer

import java.util.Enumeration;
import java.util.Vector;

class randomEnumerator implements Enumeration
{
	Enumeration origEnum;
	int remainingElements;
	int neededElements;

	public randomEnumerator(Enumeration origEnum, int existingElements,
		int neededElements)
	{
		this.origEnum = origEnum;
		this.remainingElements = existingElements;
		this.neededElements = neededElements;
	}

	public boolean hasMoreElements()
	{
		return origEnum.hasMoreElements() && remainingElements>0
			&& neededElements>0;
	}

	public Object nextElement()
	{
		if( remainingElements <= neededElements )
		{
			remainingElements--;
			neededElements--;
			return origEnum.nextElement();
		}
		int dif = remainingElements - neededElements;
		double difStep = (double)dif/(double)neededElements;
		int random = (int)Math.floor( difStep * Math.random() + 0.5 );
		if( random > dif )
			random = dif;
		while( random-- > 0 )
		{
			origEnum.nextElement();   // throw away Elements
			remainingElements--;
		}
		remainingElements--;
		neededElements--;
		return origEnum.nextElement();
	}
}


