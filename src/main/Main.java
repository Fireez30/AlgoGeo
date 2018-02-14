package main;
import javax.swing.JFrame;

import algoGeo.CPoint;
import algoGeo.CVector;
import utils.affichage.Vue;
import utils.vecteur.PointVisible;
import utils.vecteur.Vecteur;

public class Main {
	
	public static void main(String s[]) {
		JFrame frame = new JFrame("Calcul d'enveloppe convexe");
		Vue v = new Vue(20,800,250);
		frame.add(v);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
		
}
