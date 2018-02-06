package algoGeo;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import affichage.VisiblePoint;

public class Triangle {
	VisiblePoint sommets[];
	String label;

	public Triangle() {
		sommets = new VisiblePoint[3];
	}

	public Triangle(VisiblePoint p1, VisiblePoint p2, VisiblePoint p3, String l) {
		sommets = new VisiblePoint[3];
		sommets[0] = p1;
		sommets[1] = p2;
		sommets[2] = p3;
		label = l;
	}

	public void add(VisiblePoint p) {
		int j= 0;
		for (int i = 0; i<sommets.length; i++)
			if (sommets[i]!= null) j++;
		if (j< 3) sommets[j] = p;
	}

	public void dessinebase(Graphics2D g){
		g.drawLine((int) sommets[0].x , (int) sommets[0].y , (int) sommets[1].x, (int) sommets[1].y);
		g.drawLine((int) sommets[0].x , (int) sommets[0].y , (int) sommets[2].x, (int) sommets[2].y);
		g.drawLine((int) sommets[2].x , (int) sommets[2].y , (int) sommets[1].x, (int) sommets[1].y);
		for (int i = 0; i<sommets.length; i++)
			sommets[i].dessine(g);
	}

	public void dessine(Graphics2D g) {
		g.drawLine((int) sommets[0].x , (int) sommets[0].y , (int) sommets[1].x, (int) sommets[1].y);
		g.drawLine((int) sommets[0].x , (int) sommets[0].y , (int) sommets[2].x, (int) sommets[2].y);
		g.drawLine((int) sommets[2].x , (int) sommets[2].y , (int) sommets[1].x, (int) sommets[1].y);
		for (int i = 0; i<sommets.length; i++)
			sommets[i].dessine(g);
		VisiblePoint a = centreGravite();
		a.dessine(g);
		g.drawString(Aire()+"", (int) a.getX()+10,(int) a.getY());
		ArrayList<Triangle> t = trianglesGravite();
		for (Triangle tmp : t){
			tmp.dessinebase(g);
		}


		ArrayList<Triangle> t2 = trianglesMediane();
		for (Triangle tmp : t2){
			tmp.dessinebase(g);
		}
	}

	public double det(){
		ArrayList<Double> v1= new ArrayList<Double>();
		ArrayList<Double> v2 = new ArrayList<Double>();

		v1.add(0,sommets[1].getX()-sommets[0].getX());
		v1.add(1,sommets[1].getY()-sommets[0].getY());

		v2.add(0,sommets[2].getX()-sommets[0].getX());
		v2.add(1,sommets[2].getY()-sommets[0].getY());

		return v1.get(0)*v2.get(1)-v1.get(1)*v2.get(0);
	}

	public double Aire(){
		return  Math.round(Math.abs(0.5*det())*1000.0)/1000.0;
	}

	public VisiblePoint centreGravite(){
		VisiblePoint p;
		double cx,cy;

		cx = (sommets[0].getX()+sommets[1].getX()+sommets[2].getX())/3;
		cy = (sommets[0].getY()+sommets[1].getY()+sommets[2].getY())/3;

		p = new VisiblePoint(cx,cy);
		return p;
	}

	public ArrayList<Triangle> trianglesGravite(){
		ArrayList<Triangle> t = new ArrayList<Triangle>();
		VisiblePoint p = centreGravite();

		Triangle t1 = new Triangle(sommets[0],sommets[1],p,"");
		Triangle t2 = new Triangle(sommets[1],sommets[2],p,"");
		Triangle t3 = new Triangle(sommets[0],sommets[2],p,"");

		t.add(t1);
		t.add(t2);
		t.add(t3);

		return t;
	}

	public boolean Aligne(){
		if (this.Aire()==0)
			return true;
		else return false;
	}
	
	public ArrayList<Triangle> trianglesMediane(){
		ArrayList<Triangle> result = new ArrayList<Triangle>();
		VisiblePoint G = centreGravite();
		VisiblePoint midAB = new VisiblePoint((sommets[0].getX()+sommets[1].getX())/2,(sommets[0].getY()+sommets[1].getY())/2);
		VisiblePoint midAC = new VisiblePoint((sommets[0].getX()+sommets[2].getX())/2,(sommets[0].getY()+sommets[2].getY())/2);
		VisiblePoint midBC = new VisiblePoint((sommets[1].getX()+sommets[2].getX())/2,(sommets[1].getY()+sommets[2].getY())/2);

		Triangle t1,t2,t3,t4,t5,t6;

		t1 = new Triangle(sommets[1],midAB,G,"");
		t6 = new Triangle(sommets[1],midBC,G,"");
		t2 = new Triangle(sommets[0],midAB,G,"");
		t3 = new Triangle(sommets[0],midAC,G,"");
		t4 = new Triangle(sommets[2],midAC,G,"");
		t5 = new Triangle(sommets[2],midBC,G,"");

		result.add(t1);
		result.add(t2);
		result.add(t3);
		result.add(t4);
		result.add(t5);
		result.add(t6);

		return result;
	}
}

