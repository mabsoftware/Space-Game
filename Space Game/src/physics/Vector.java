package physics;

public class Vector
{
	private double myXComponent;
	private double myYComponent;
	
	public Vector(double xComponent, double yComponent)
	{
		myXComponent = xComponent;
		myYComponent = yComponent;
	}
	
	public double getXComponent()
	{
		return myXComponent;
	}
	
	public double getYComponent()
	{
		return myYComponent;
	}
	
	public void setXComponent(double x)
	{
		myXComponent = x;
	}
	
	public void setYComponent(double y)
	{
		myYComponent = y;
	}
	
	public void add(Vector v)
	{
		myXComponent += v.getXComponent();
		myYComponent += v.getYComponent();
	}
	
	public void subtract(Vector v)
	{
		myXComponent -= v.getXComponent();
		myYComponent -= v.getYComponent();
	}
	
	public void multiplyByScalar(double s)
	{
		myXComponent *= s;
		myYComponent *= s;
	}
}
