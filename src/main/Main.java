package main;
import javax.swing.JFrame;
import utils.affichage.Vue;

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
