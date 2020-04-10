// November 13, 1996
// Andras Niedermayer

import java.awt.*;

class CircleColorButton extends Canvas
{
	Color fillColor = Color.blue;
	Color drawColor = Color.black;

	public CircleColorButton()
	{
	}

	public void paint(Graphics g)
	{
		Dimension d = size();
//		System.out.println("CircleButton.size = " + d );
//		System.out.println("painting CCB, size = " + d + ", parent=" + getParent());
		g.setColor(fillColor);
		g.fillOval(1,1,d.width-2,d.height-2);
		g.setColor(drawColor);
		g.drawOval(1,1,d.width-2,d.height-2);
	}

	protected void setFillColor(Color c)
	{
		fillColor = c;
	}

	protected void setDrawColor(Color c)
	{
		drawColor = c;
	}
}
