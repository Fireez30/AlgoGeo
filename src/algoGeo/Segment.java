package algoGeo;

import java.util.ArrayList;

public class Segment {

	CPoint x,y;

	public Segment(CPoint p1,CPoint p2) {
		this.x=p1;
		this.y=p2;
	}
	
	public double det(CVector v1, CVector v2)
	{
		return v1.getX()*v2.getY()-v1.getY()*v2.getX();
	}

	public double tour(CPoint p1, CPoint p2,CPoint p3)
	{
		CVector v1 = new CVector(p1,p2);
		CVector v2 = new CVector(p1,p3);
		
		double d = det(v1,v2);
		if (d>0)
			return 1;
		else if (d==0)
			return 0;
		else return 1;
	}
	
	public boolean testIntersection(CPoint p1, CPoint p2){
		
		double d1=tour(this.x,this.y,p1);
		double d2=tour(this.x,this.y,p2);
		
		if (d1*d2 > 0)
			return false;
		if (d1*d2 < 0)
		{
			double d3 = tour(p1,p2,this.x);
			double d4 = d3 = tour(p1,p2,this.y);
			
			if (d3*d4 > 0)
				return false;
			
			if (d3*d4 < 0)
				return true;
						
		}
		Segment CD = new Segment(p1,p2);
		
		return (CD.contains(this.x) || CD.contains(this.y) || this.contains(p1) || this.contains(p2));//contains
	}
	
	public boolean appartient01(double d1) {
		return (d1 >= 0 && d1 <= 1);
	}
	
	public boolean contains(CPoint p1){
			if (this.x.getX()==this.y.getX() && this.x.getY()==this.y.getY())
				return (this.x.getX()==p1.getX() && this.x.getY()==p1.getY());
			if (this.x.getX()==p1.getX())
				return  (p1.getX()==this.x.getX() && appartient01((p1.getY()-this.y.getY())/(this.x.getY()-this.y.getY())));
			if (this.y.getY()==this.x.getY())
				return (p1.getY()==this.x.getY() && appartient01((p1.getX()-this.y.getX())/(this.x.getX()-this.y.getX())));
			return ((p1.getX()-this.y.getX())/(this.x.getX()-this.y.getX()) == (p1.getY()-this.y.getY())/(this.x.getY()-this.y.getY())) && appartient01((p1.getX()-this.y.getX())/(this.x.getX()-this.y.getX()));
	}
}
