package com.isty.arlo.ihm;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Gui extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;

	private static JTabbedPane onglets;
	
	private static JPanel panelSalle;
	private static JPanel panelPersonne;
	private static JPanel panelCreneau;
	private static PanelReservation panelReservation;
	
	
	public Gui() {
		onglets = new JTabbedPane();
	
		panelSalle = new PanelSalle();
		onglets.addTab("Salles", panelSalle);
		
		panelPersonne = new PanelPersonne();
		onglets.addTab("Personnes", panelPersonne);
		
		panelCreneau = new PanelCreneau();
		onglets.addTab("Créneaux", panelCreneau);
		
		panelReservation = new PanelReservation();
		onglets.addTab("Réservations", panelReservation);
		
		onglets.addMouseListener(this);
		
	}

	
	public void createFrame() {
		JFrame fenetre = new JFrame("ARLO IHM");
		fenetre.setMinimumSize(new Dimension(640, 480));
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(onglets, BorderLayout.CENTER);
		fenetre.add(this);
		
		
		fenetre.pack();
		fenetre.setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == onglets) {
			panelReservation.update();
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
