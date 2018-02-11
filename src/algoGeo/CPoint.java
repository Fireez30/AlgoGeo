package algoGeo;

public class CPoint {

		protected double x;
		protected double y;
		
		public CPoint() {
			x=0;
			y=0;
		}
		
		public CPoint(double px,double py) {
			x=px;
			y=py;
		}
		
		public double getX() {
			return this.x;
		}
		
		public double getY() {
			return this.y;
		}
}
