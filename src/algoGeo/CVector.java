package algoGeo;

public class CVector {

		public double x,y;
		
		public CVector()
		{
			x=0;
			y=0;
		}
		
		public CVector(double p1, double p2)
		{
			x=p1;
			y=p2;
		}
		
		public CVector(CPoint p1,CPoint p2) {
			this.x = p2.getX()-p1.getX();
			this.y = p2.getY()-p1.getY();
		}
		
		public double getX() {
			return this.x;
		}
		
		public double getY() {
			return this.y;
		}
}
