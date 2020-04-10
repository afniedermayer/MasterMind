// January 31, 1996
// Andras Niedermayer

import java.awt.*;

class NumberStepsField extends java.awt.Panel
{
	TextField numSteps;

	public NumberStepsField()
	{
		super();
		add("North", new Label("Maximum of steps"));
		String s = new Integer( Thinker.maxSteps ).toString();
		numSteps = new TextField( s, s.length()+4 );
		add("Center", numSteps );
		add("South", new Button("Set") );
	}

	public boolean action( Event evt, Object arg )
	{
		if( arg.equals("Set") )
		{
			int Value = Integer.parseInt(numSteps.getText());
			if( Value <= 0 )
			{
				Thinker.faster = false;
				System.out.println("Thinker faster = false");
			}
			else {
				Thinker.maxSteps = Value;
				Thinker.faster = true;
				System.out.println("Thinker faster = true; Value = " + Value);
			}
			return true;
		}
		return false;
	}
}
	