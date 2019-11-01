package com.isty.arlo.ihm;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.isty.arlo.db.RessourcesInterface;

public class Gui extends JPanel implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;

	private static JTabbedPane onglets;
	
	private static PanelSalle panelSalle;
	private static PanelPersonne panelPersonne;
	private static PanelCreneau panelCreneau;
	private static PanelReservation panelReservation;
	
	private static JButton buttonResetDB;
	
	
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
		
		buttonResetDB = new JButton("Reset database");
		buttonResetDB.addActionListener(this);
		
	}

	
	public void createFrame() {
		JFrame fenetre = new JFrame("ARLO IHM");
		fenetre.setMinimumSize(new Dimension(640, 480));
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(onglets);
		add(buttonResetDB);
		fenetre.add(this);
		
		
		fenetre.pack();
		fenetre.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonResetDB) {
			RessourcesInterface.createTable("personne");
			panelPersonne.updateTable();
			RessourcesInterface.createTable("salle");
			panelSalle.updateTable();
			RessourcesInterface.createTable("creneau");
			panelCreneau.updateTable();
			RessourcesInterface.createTable("reservation");
			panelReservation.update();
			panelReservation.updateTable();
		}
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
