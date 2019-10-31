package com.isty.arlo.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.isty.arlo.db.DatabaseManager;
import com.isty.arlo.domain.Salle;

public class PanelSalle extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private static JTable table;
	private static DefaultTableModel tableModel;
	private static JTextField inputNom;
	private static JButton buttonAdd;
	private static JButton buttonDel;
	
	
	public PanelSalle() {
		// Tableau de salles
		this.makeTable();
		JScrollPane js=new JScrollPane(table);
		js.setVisible(true);
		add(js);
		
		// Formulaire d'ajout d'une Salle
		JPanel panelGestion = new JPanel();
		JLabel labelNom = new JLabel("Nom");
		inputNom = new JTextField();
		panelGestion.add(labelNom);
		panelGestion.add(inputNom);
		
		buttonAdd = new JButton("Ajouter la salle");
		buttonAdd.addActionListener(this);
		panelGestion.add(buttonAdd);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		
		buttonDel = new JButton("Supprimer la salle");
		buttonDel.addActionListener(this);
		buttonDel.setEnabled(false);
		panelGestion.add(buttonDel);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		this.add(panelGestion);
	}
	
	
	private void makeTable() {
		ArrayList<Salle> listeSalles = null;
		String[] header = {"id", "nom"};
		Object[][] data = null;
		listeSalles = DatabaseManager.getAllSalle();
		data = new Object[listeSalles.size()][2];
		int row = 0;
		for(Salle salle : listeSalles) {
			data[row][0] = salle.getId();
			data[row][1] = salle.getNom();
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
		ArrayList<Salle> listeSalles = null;
		String[] header = {"id", "nom"};
		Object[][] data = null;
		listeSalles = DatabaseManager.getAllSalle();
		data = new Object[listeSalles.size()][2];
		int row = 0;
		for(Salle salle : listeSalles) {
			data[row][0] = salle.getId();
			data[row][1] = salle.getNom();
			row ++;
		}
		tableModel.setDataVector(data, header);
		tableModel.fireTableDataChanged();
		buttonDel.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonAdd) {
			if(DatabaseManager.insertEntite(new Salle(inputNom.getText()), "salle"))
				this.updateTable();
			
			inputNom.setText("");
		}
		else if(e.getSource() == buttonDel && table.getSelectedRow() != -1) {
			if(DatabaseManager.deleteEntite((Integer)tableModel.getValueAt(table.getSelectedRow(), 0), "salle"))
				this.updateTable();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == table) {
			buttonDel.setEnabled(true);
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
