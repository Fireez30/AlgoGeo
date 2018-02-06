package test;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import affichage.VisiblePoint;
import affichage.Vue;
import algoGeo.Triangle;
import test.Experiment;

public class Main {
	public static void main(String s[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Geometrie - triangles et cercles");
				String importFile = "data/triangles.csv";
				Vue v = new Vue(800,800);
				Experiment e = new Experiment(importFile,v);
				JSplitPane js = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				js.add(e);
				js.add(v);
				frame.add(js);
				frame.pack();
				frame.setLocation(200,200);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}			
		});
		
		VisiblePoint A = new VisiblePoint(2,1);
		VisiblePoint B = new VisiblePoint(3,3);
		VisiblePoint C = new VisiblePoint(-1,2);
		
		Triangle t1 = new Triangle(A,B,C,"");
		
		System.out.println("Aire : "+t1.Aire());
		
		System.out.println("Calcul des 3 triangles ...");
		//------------------------------------------------------------
		System.out.println();
		ArrayList<Triangle> t3 = t1.trianglesGravite();
		System.out.println("Aire sous triangle 1 : "+t3.get(0).Aire());
		System.out.println("Aire sous triangle 2 : "+t3.get(1).Aire());
		System.out.println("Aire sous triangle 3 : "+t3.get(2).Aire());
		
		if (t3.get(0).Aire()+t3.get(1).Aire()+t3.get(2).Aire() == t1.Aire())
			System.out.println("Somme des aires des 3 triangles bien égale a l'aire totale");
		else System.out.println("Somme des aires des 3 triangles non égale a l'aire totale");
		//-------------------------------------------------------------
		System.out.println("Calcul des 6 triangles ...");
		
		ArrayList<Triangle> t6 = t1.trianglesMediane();
		System.out.println("Aire sous triangle 1 : "+t6.get(0).Aire());
		System.out.println("Aire sous triangle 2 : "+t6.get(1).Aire());
		System.out.println("Aire sous triangle 3 : "+t6.get(2).Aire());
		System.out.println("Aire sous triangle 4 : "+t6.get(3).Aire());
		System.out.println("Aire sous triangle 5 : "+t6.get(4).Aire());
		System.out.println("Aire sous triangle 6 : "+t6.get(5).Aire());
		
		if (t6.get(0).Aire()+t6.get(1).Aire()+t6.get(2).Aire()+t6.get(3).Aire()+t6.get(4).Aire()+t6.get(5).Aire() == t1.Aire())
			System.out.println("Somme des aires des 6 triangles bien égale a l'air totale ");
		else System.out.println("Somme des aires des 6 triangles non égale a l'air totale ");
		
		
	}

}
