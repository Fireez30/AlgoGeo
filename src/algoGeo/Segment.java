package algoGeo;

import java.util.ArrayList;

public class Segment {

	double xa,xb,ya,yb;

	public double det(double xa, double ya, double xb, double yb, double xc, double yc)
	{
		CVector v1 = new CVector(xb-xa,yb-ya);
		CVector v2 = new CVector(xc-xa,yc-ya);

		return v1.x*v2.y-v1.y*v2.x;
	}

	public double tour(double vx,double vy,double wx, double wy,double px,double py)
	{
		double d = det(vx,vy,wx,wy,px,py);
		if (d>0)
			return 1;
		else if (d==0)
			return 0;
		else return 1;
	}
	
	public boolean testIntersection(double px, double py, double qx, double qy){
		double d1=tour(xa,ya,xb,yb,px,py);
		double d2=tour(xa,ya,xb,yb,qx,qy);
		
		if (d1*d2 > 0)
			return false;
		if (d1*d2 < 0)
		{
			double d3 = tour(px,py,qx,qy,xa,ya);
			double d4 = d3 = tour(px,py,qx,qy,xb,yb);
			
			if (d3*d4 > 0)
				return false;
			
			if (d3*d4 < 0)
				return (contains())
						
		}
	}
	
	public boolean contains(double px, double py){
			if (xa==xb && ya==yb)
				return xa==px && ya==py;
	}
	}
}
