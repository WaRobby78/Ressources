package com.isty.arlo.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.isty.arlo.db.RessourcesInterface;
import com.isty.arlo.domain.Creneau;
import com.isty.arlo.domain.Personne;
import com.isty.arlo.domain.Reservation;
import com.isty.arlo.domain.Salle;

public class PanelReservation extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private static JTable table;
	private static DefaultTableModel tableModel;
	private static JTextField inputId;
	private static JComboBox<String> inputPersonne;
	private static JComboBox<String> inputSalle;
	private static JComboBox<String> inputCreneau;
	private static JComboBox<String> inputModifCreneau;
	private static JButton buttonAdd;
	private static JButton buttonDel;
	private static JButton buttonModifCreneau;
	
	
	public PanelReservation() {
		// Tableau de salles
		this.makeTable();
		JScrollPane js = new JScrollPane(table);
		js.setVisible(true);
		add(js);
		
		// Formulaire d'ajout d'une Salle
		JPanel panelGestion = new JPanel();
		
		JLabel labelId = new JLabel("Id");
		inputId = new JTextField();
		panelGestion.add(labelId);
		panelGestion.add(inputId);
		
		JLabel labelPersonne = new JLabel("Personne");
		panelGestion.add(labelPersonne);
		inputPersonne = new JComboBox<String>();
		for(Personne personne : RessourcesInterface.getAllPersonne()) {
			inputPersonne.addItem("[" + personne.getId() + "] " + personne.getNom() + " " + personne.getPrenom());
		}
		panelGestion.add(inputPersonne);
		
		
		JLabel labelSalle = new JLabel("Salle");
		panelGestion.add(labelSalle);
		inputSalle = new JComboBox<String>();
		for(Salle salle : RessourcesInterface.getAllSalle()) {
			inputSalle.addItem("[" + salle.getId() + "] " + salle.getNom());
		}
		panelGestion.add(inputSalle);
		
		
		JLabel labelCreneau = new JLabel("Cr�neau");
		panelGestion.add(labelCreneau);
		inputCreneau = new JComboBox<String>();
		inputModifCreneau = new JComboBox<String>();
		for(Creneau creneau : RessourcesInterface.getAllCreneau()) {
			inputCreneau.addItem("[" + creneau.getId() + "] " + creneau.getStrStart() + " --> " + creneau.getStrEnd());
			inputModifCreneau.addItem("[" + creneau.getId() + "] " + creneau.getStrStart() + " --> " + creneau.getStrEnd());
		}
		panelGestion.add(inputCreneau);
		
		
		buttonAdd = new JButton("Ajouter la r�servation");
		buttonAdd.addActionListener(this);
		panelGestion.add(buttonAdd);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		
		inputModifCreneau.setEnabled(false);
		panelGestion.add(inputModifCreneau);
		buttonModifCreneau = new JButton("Modifier le cr�neau");
		buttonModifCreneau.setEnabled(false);
		buttonModifCreneau.addActionListener(this);
		panelGestion.add(buttonModifCreneau);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		
		buttonDel = new JButton("Supprimer la r�servation");
		buttonDel.addActionListener(this);
		buttonDel.setEnabled(false);
		panelGestion.add(buttonDel);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		
		
		add(panelGestion);
	}
	
	private void makeTable() {
		ArrayList<Reservation> listeReservation = null;
		String[] header = {"id", "personne", "salle", "cr�neau d�but", "cr�neau fin"};
		Object[][] data = null;
		listeReservation = RessourcesInterface.getAllReservation();
		data = new Object[listeReservation.size()][5];
		int row = 0;
		for(Reservation reservation : listeReservation) {
			data[row][0] = reservation.getId();
			data[row][1] = reservation.getPersonne().getNom() + " " + reservation.getPersonne().getPrenom();
			data[row][2] = reservation.getSalle().getNom();
			data[row][3] = reservation.getCreneau().getStrStart();
			data[row][4] = reservation.getCreneau().getStrEnd();
			row ++;
		}
		
		tableModel = new DefaultTableModel(data, header) {
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		table = new JTable();
		table.setModel(tableModel);
		table.setAutoscrolls(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(this);
	}
	
	public void updateTable() {
		ArrayList<Reservation> listeReservation = null;
		String[] header = {"id", "personne", "salle", "cr�neau d�but", "cr�neau fin"};
		Object[][] data = null;
		listeReservation = RessourcesInterface.getAllReservation();
		data = new Object[listeReservation.size()][5];
		int row = 0;
		for(Reservation reservation : listeReservation) {
			data[row][0] = reservation.getId();
			data[row][1] = reservation.getPersonne().getNom() + " " + reservation.getPersonne().getPrenom();
			data[row][2] = reservation.getSalle().getNom();
			data[row][3] = reservation.getCreneau().getStrStart();
			data[row][4] = reservation.getCreneau().getStrEnd();
			row ++;
		}
		tableModel.setDataVector(data, header);
		tableModel.fireTableDataChanged();
		buttonDel.setEnabled(false);
		buttonModifCreneau.setEnabled(false);
		inputModifCreneau.setEnabled(false);
	}
	
	private String getIdFromComboBoxItem(String item) {
		String getId = "";
		for(int i = 1; Character.isDigit(item.charAt(i)); i++)
			getId+= item.charAt(i);
		return getId;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonAdd) {
			if(inputId.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Error: Il faut entrer un id � l'entit�!");
				return;
			}

			if(inputPersonne.getSelectedItem() == null || inputSalle.getSelectedItem() == null || inputCreneau.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Error: Il faut que tous les champs soient renseign�s!");
				return;
			}
			
			for(Reservation reservation : RessourcesInterface.getAllReservation()) {
				if(reservation.getId().equals(inputId.getText())) {
					JOptionPane.showMessageDialog(null, "Error: L'id existe d�j�!");
					return;
				}
			}
			
			String idPersonne = getIdFromComboBoxItem((String) inputPersonne.getSelectedItem());
			Personne personne = RessourcesInterface.getPersonneFromId(idPersonne);
			String idSalle = getIdFromComboBoxItem((String) inputSalle.getSelectedItem());
			Salle salle = RessourcesInterface.getSalleFromId(idSalle);
			String idCreneau = getIdFromComboBoxItem((String) inputCreneau.getSelectedItem());
			Creneau creneau = RessourcesInterface.getCreneauFromId(idCreneau);
			
			if(RessourcesInterface.insertEntite(new Reservation(inputId.getText(), personne, salle, creneau), "reservation"))
				this.updateTable();
			else
				JOptionPane.showMessageDialog(null, "Error: La r�servation existe d�j�!");
			inputId.setText("");
		}
		else if(e.getSource() == buttonDel && table.getSelectedRow() != -1) {
			if(RessourcesInterface.deleteEntite((String)tableModel.getValueAt(table.getSelectedRow(), 0), "reservation"))
				this.updateTable();
		}
		else if(e.getSource() == buttonModifCreneau && table.getSelectedRow() != -1) {
			String idReservation = (String)tableModel.getValueAt(table.getSelectedRow(), 0);
			String idCreneau = getIdFromComboBoxItem((String) inputModifCreneau.getSelectedItem());
			if(RessourcesInterface.updateCreneauReservation(idReservation, idCreneau))
				this.updateTable();
		}
	}

	
	public void update() {
		inputPersonne.removeAllItems();
		for(Personne personne : RessourcesInterface.getAllPersonne()) {
			inputPersonne.addItem("[" + personne.getId() + "] " + personne.getNom() + " " + personne.getPrenom());
		}
		
		inputSalle.removeAllItems();
		for(Salle salle : RessourcesInterface.getAllSalle()) {
			inputSalle.addItem("[" + salle.getId() + "] " + salle.getNom());
		}
		
		inputCreneau.removeAllItems();
		inputModifCreneau.removeAllItems();
		for(Creneau creneau : RessourcesInterface.getAllCreneau()) {
			inputCreneau.addItem("[" + creneau.getId() + "] " + creneau.getStrStart() + " --> " + creneau.getStrEnd());
			inputModifCreneau.addItem("[" + creneau.getId() + "] " + creneau.getStrStart() + " --> " + creneau.getStrEnd());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == table) {
			buttonDel.setEnabled(true);
			buttonModifCreneau.setEnabled(true);
			inputModifCreneau.setEnabled(true);
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
