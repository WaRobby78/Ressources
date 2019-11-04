package com.isty.arlo.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.isty.arlo.db.RessourcesInterface;
import com.isty.arlo.domain.Personne;

public class PanelPersonne extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private static JTable table;
	private static DefaultTableModel tableModel;
	private static JTextField inputId;
	private static JTextField inputNom;
	private static JTextField inputPrenom;
	private static JTextField inputJob;
	private static JButton buttonAdd;
	private static JButton buttonDel;
	
	
	public PanelPersonne() {
		// Tableau de personnes
		this.makeTable();
		JScrollPane js=new JScrollPane(table);
		js.setVisible(true);
		add(js);
		
		// Formulaire d'ajout d'une Personne
		JPanel panelGestion = new JPanel();
		
		JLabel labelId = new JLabel("Id");
		inputId = new JTextField();
		panelGestion.add(labelId);
		panelGestion.add(inputId);
		
		JLabel labelNom = new JLabel("Nom");
		inputNom = new JTextField();
		panelGestion.add(labelNom);
		panelGestion.add(inputNom);
		
		JLabel labelPrenom = new JLabel("Prénom");
		inputPrenom = new JTextField();
		panelGestion.add(labelPrenom);
		panelGestion.add(inputPrenom);
		
		JLabel labelJob = new JLabel("Job");
		inputJob = new JTextField();
		panelGestion.add(labelJob);
		panelGestion.add(inputJob);
		
		buttonAdd = new JButton("Ajouter la personne");
		buttonAdd.addActionListener(this);
		panelGestion.add(buttonAdd);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		
		buttonDel = new JButton("Supprimer la personne");
		buttonDel.addActionListener(this);
		buttonDel.setEnabled(false);
		panelGestion.add(buttonDel);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		this.add(panelGestion);
	}
	
	
	private void makeTable() {
		ArrayList<Personne> listePersonnes = null;
		String[] header = {"id", "nom", "prenom", "job"};
		Object[][] data = null;
		listePersonnes = RessourcesInterface.getAllPersonne();
		data = new Object[listePersonnes.size()][4];
		int row = 0;
		for(Personne personne : listePersonnes) {
			data[row][0] = personne.getId();
			data[row][1] = personne.getNom();
			data[row][2] = personne.getPrenom();
			data[row][3] = personne.getJob();
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
		ArrayList<Personne> listePersonnes = null;
		String[] header = {"id", "nom", "prenom", "job"};
		Object[][] data = null;
		listePersonnes = RessourcesInterface.getAllPersonne();
		data = new Object[listePersonnes.size()][4];
		int row = 0;
		for(Personne personne : listePersonnes) {
			data[row][0] = personne.getId();
			data[row][1] = personne.getNom();
			data[row][2] = personne.getPrenom();
			data[row][3] = personne.getJob();
			row ++;
		}
		tableModel.setDataVector(data, header);
		tableModel.fireTableDataChanged();
		buttonDel.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonAdd) {
			if(inputId.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Error: Il faut entrer un id à l'entité!");
				return;
			}

			if(inputNom.getText().equals("") || inputPrenom.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Error: Il faut entrer le nom et le prénom de la personne!");
				return;
			}
			
			for(Personne personne : RessourcesInterface.getAllPersonne()) {
				if(personne.getId().equals(inputId.getText())) {
					JOptionPane.showMessageDialog(null, "Error: L'id existe déjà!");
					return;
				}
			}
			if(RessourcesInterface.insertEntite(new Personne(inputId.getText(), inputNom.getText(), inputPrenom.getText(), inputJob.getText()), "personne"))
				this.updateTable();
			inputId.setText("");
			inputNom.setText("");
			inputPrenom.setText("");
			inputJob.setText("");
		}
		else if(e.getSource() == buttonDel && table.getSelectedRow() != -1) {
			if(RessourcesInterface.deleteEntite((String)tableModel.getValueAt(table.getSelectedRow(), 0), "personne"))
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
