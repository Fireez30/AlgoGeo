package utils.affichage;
import javax.swing.*;

import utils.couleurs.Couleur;
import utils.fileIo.ReadWritePoint;

import utils.vecteur.PointVisible;
import utils.vecteur.Vecteur;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

import java.util.ArrayList;

public class Vue extends JPanel implements MouseWheelListener, MouseListener, ActionListener{
	Color bgColor = Couleur.bg; // la couleur de fond de la fen�tre
	Color fgColor = Couleur.fg; // la couleur des lignes
	int width;
	int centre = 400;
	ArrayList<PointVisible> points = new ArrayList<PointVisible>();
	ArrayList<PointVisible> envellope = new ArrayList<PointVisible>();

	// n : le nombre de lignes
	// width, height : largeur, hauteur de la fen�tre
	public Vue(int n, int width,int rayon) {
		super();
		JButton b1 = new JButton("Enregistrer");
		b1.setActionCommand("b1");
		b1.addActionListener(this);
		JButton b2 = new JButton("Charger");
		b2.addActionListener(this);
		Box b = Box.createHorizontalBox();
		b.add(b1);b.add(Box.createHorizontalStrut(10));b.add(b2);
		add(b);
		setBackground(bgColor);
		this.width = width;
		setPreferredSize(new Dimension(width, width));
		System.out.println("initialisation avec n = "+n);
		initPoints(n, rayon);
		addMouseListener(this);
		addMouseWheelListener(this);
	}

	public ArrayList<PointVisible> Jarvis (ArrayList<PointVisible> sommets) {
		PointVisible PMin, PCourant, PSuivant = null;
		PMin = getMin(sommets);
		PCourant = PMin;
		ArrayList<PointVisible> EnveConv = new ArrayList<PointVisible>();
		EnveConv.add(PMin);


		while (! PMin.equals(PSuivant)) {
			PSuivant = getNext(PCourant, sommets);
			if (PSuivant == null) {
				System.out.println("PointsAlignés");
				EnveConv.clear();
				EnveConv.add(PMin);
				break;
			}
			EnveConv.add(PSuivant);
			PCourant = PSuivant;

		}
		return EnveConv;        
	}
	
	public PointVisible getMax(ArrayList<PointVisible> points) {
		PointVisible PMax = points.get(0);
		for (int i = 0; i < points.size(); ++i) {
			if (PMax.getY() < points.get(i).getY()) PMax = points.get(i);
			else if (PMax.getY() == points.get(i).getY())
				if (PMax.getX() < points.get(i).getX()) PMax = points.get(i);
		}
		return PMax;
	}
	
	/*
	public ArrayList<PointVisible> triPolaire(ArrayList<PointVisible> sommets, PointVisible origine){
		ArrayList<PointVisible> result = new ArrayList<PointVisible>();
		
		return result;
	}
	*/
	
	public ArrayList<PointVisible> Graham (ArrayList<PointVisible> sommets) {
		ArrayList<PointVisible> EnveConv = new ArrayList<PointVisible>();
		PointVisible PMax = getMax(sommets);
		
		
		EnveConv.add(sommets.get(0));
		EnveConv.add(sommets.get(1));
		
		for (int i = 2; i < sommets.size(); ++i) {
			while (EnveConv.size() >= 2 && tour(EnveConv.get(EnveConv.size()-2), EnveConv.get(EnveConv.size() - 1), sommets.get(i)) < 0) {
				EnveConv.remove(EnveConv.size() - 1);
			}
			EnveConv.add(sommets.get(i));
		}
		
		
		return EnveConv;
	}

	public PointVisible getNext(PointVisible PCourant, ArrayList<PointVisible> points) {
		PointVisible PSuivant = points.get(0);
		for (int i = 1; i < points.size(); ++i) {
			if (!PCourant.equals(points.get(i))) {
				
				if (tour(PCourant, PSuivant, points.get(i)) > 0) PSuivant = points.get(i);
				else if (tour(PCourant, PSuivant, points.get(i)) == 0)return null;
			}
		}
		return PSuivant;
	}

	public PointVisible getMin(ArrayList<PointVisible> points) {
		PointVisible PMin = points.get(0);
		for (int i = 0; i < points.size(); ++i) {
			if (PMin.getY() > points.get(i).getY()) PMin = points.get(i);
			else if (PMin.getY() == points.get(i).getY())
				if (PMin.getX() > points.get(i).getX()) PMin = points.get(i);
		}
		return PMin;
	}

	public double determinant(PointVisible A, PointVisible B, PointVisible point) {
		double deter1 = (B.getX() - A.getX()) * (point.getY() - A.getY());
		double deter2 = (B.getY() - A.getY()) * (point.getX() - A.getX());
		return deter1 - deter2;
	}

	public int tour (PointVisible A, PointVisible B, PointVisible point) {
		double deter = determinant(A, B, point);
		if (deter < 0)
			return -1;
		else if (deter > 0)
			return 1;
		else
			return 0;
	}

    public ArrayList<PointVisible> getPoints(){
        return this.points;
    }

    public double normePoints(PointVisible v1, PointVisible v2){
        return Math.pow((v2.getX()-v1.getX()),2)+Math.pow((v2.getY()-v1.getY()),2);
    }

    public void initPoints(int n, int r){
        int xp,yp;
        points = new ArrayList<PointVisible>();
        points.add(new PointVisible(centre,centre));
        points.get(0).setLabel("Point 0");
        while (points.size() < n){
            xp = random(centre-r, centre+r);
            yp = random(centre-r, centre+r);
            PointVisible tmp = new PointVisible(xp,yp);
            double norme = normePoints(points.get(0),tmp);
            if (norme < Math.pow(r,2)){
                points.add(new PointVisible(xp,yp));
                points.get(points.size()-1).setLabel("Point "+ points.size());
            }
        }

    }
	
	
	// m�thode utilitaire 
	// retourne un entier compris entre xmin et xmax
	int random(int xmin,int xmax){
		double dr = Math.random() * (double) (xmax - xmin) + (double) xmin;
		return (int) dr;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaintMode(); 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);	

		g2d.setColor(fgColor);

		for (PointVisible p: points) {
			p.dessine(g2d);
		}
		
		envellope = Jarvis(points);
		
		for (int i =0; i < envellope.size() - 1; i++){
			Vecteur c = new Vecteur(envellope.get(i),envellope.get(i+1));
			c.setLabel("");
			c.dessine(g2d);	
		}

	}
	
	public void addPoint(int x, int y) {
		points.add(new PointVisible(x, y));
		points.get(points.size() - 1).setLabel("Point" + (points.size() - 1));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int n = points.size();
		if (e.getButton() == MouseEvent.BUTTON1){
			initPoints(n, 250);
			repaint();
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			addPoint(e.getX(), e.getY());
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String testFile = "tmp.txt";
		ReadWritePoint rw = new ReadWritePoint(testFile);

		if(e.getActionCommand().equals("b1")){
			for (PointVisible s: points){
				rw.add(s.x+";" + s.y+";"+s.getLabel());
			}
			try {
				rw.write();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}else{
			initFromFile(testFile);
			repaint();
		}
	}
	public void initFromFile(String f){
		ReadWritePoint rw = new ReadWritePoint(f);
		try {
			points = rw.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

