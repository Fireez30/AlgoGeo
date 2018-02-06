package affichage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import test.Experiment;
import test.ReadWrite;


public class Vue extends JPanel {
	// n : le nombre de lignes
	// width, height : largeur, hauteur de la fenetre
	VueFigure vf;
	JTextArea messages;
	public Vue(int ww, int wh) {
		JSplitPane js = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vf = new VueFigure(ww,wh-200);
		js.add(vf);
		messages = new JTextArea("Zone d'affichage des messages");
		js.add(new JScrollPane(messages));
		this.add(js);
	}

	public void setExperiment(Experiment experiment) {
		this.vf.expe = experiment;		
	}

}
	

