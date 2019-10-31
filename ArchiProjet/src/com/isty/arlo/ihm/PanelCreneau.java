package com.isty.arlo.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.isty.arlo.db.DatabaseManager;
import com.isty.arlo.domain.Creneau;

public class PanelCreneau extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private static JTable table;
	private static DefaultTableModel tableModel;
	private static JComboBox<Integer> inputAnneeStart;
	private static JComboBox<String> inputMoisStart;
	private static JComboBox<Integer> inputJourStart;
	private static JComboBox<String> inputHeureStart;
	private static JComboBox<Integer> inputAnneeEnd;
	private static JComboBox<String> inputMoisEnd;
	private static JComboBox<Integer> inputJourEnd;
	private static JComboBox<String> inputHeureEnd;
	private static JButton buttonAdd;
	private static JButton buttonDel;
	
	
	public PanelCreneau() {
		// Tableau de créneaux
		this.makeTable();
		JScrollPane js=new JScrollPane(table);
		js.setVisible(true);
		add(js);
		
		// Formulaire d'ajout d'une Créneau
		JPanel panelGestion = new JPanel();
		JLabel labelDebut = new JLabel("DEBUT");
		inputAnneeStart = new JComboBox<Integer>();
		inputAnneeStart.addItem(2019);
		inputAnneeStart.addItem(2020);
		inputAnneeStart.addActionListener(this);
		inputMoisStart = new JComboBox<String>();
		inputMoisStart.addItem("Janvier");
		inputMoisStart.addItem("Février");
		inputMoisStart.addItem("Mars");
		inputMoisStart.addItem("Avril");
		inputMoisStart.addItem("Mai");
		inputMoisStart.addItem("Juin");
		inputMoisStart.addItem("Juillet");
		inputMoisStart.addItem("Août");
		inputMoisStart.addItem("Septembre");
		inputMoisStart.addItem("Octobre");
		inputMoisStart.addItem("Novembre");
		inputMoisStart.addItem("Décembre");
		inputMoisStart.addActionListener(this);
		inputJourStart = new JComboBox<Integer>();
		for(int i = 1; i <= 31; i++)
			inputJourStart.addItem(i);
		inputHeureStart = new JComboBox<String>();
		for(int i = 7; i <= 19; i++)
			inputHeureStart.addItem(i+"h");
		panelGestion.add(labelDebut);
		panelGestion.add(inputAnneeStart);
		panelGestion.add(inputMoisStart);
		panelGestion.add(inputJourStart);
		panelGestion.add(inputHeureStart);
		
		JLabel labelFin = new JLabel("FIN");
		inputAnneeEnd = new JComboBox<Integer>();
		inputAnneeEnd.addItem(2019);
		inputAnneeEnd.addItem(2020);
		inputAnneeEnd.addActionListener(this);
		inputMoisEnd = new JComboBox<String>();
		inputMoisEnd.addItem("Janvier");
		inputMoisEnd.addItem("Février");
		inputMoisEnd.addItem("Mars");
		inputMoisEnd.addItem("Avril");
		inputMoisEnd.addItem("Mai");
		inputMoisEnd.addItem("Juin");
		inputMoisEnd.addItem("Juillet");
		inputMoisEnd.addItem("Août");
		inputMoisEnd.addItem("Septembre");
		inputMoisEnd.addItem("Octobre");
		inputMoisEnd.addItem("Novembre");
		inputMoisEnd.addItem("Décembre");
		inputMoisEnd.addActionListener(this);
		inputJourEnd = new JComboBox<Integer>();
		for(int i = 1; i <= 31; i++)
			inputJourEnd.addItem(i);
		inputHeureEnd = new JComboBox<String>();
		for(int i = 7; i <= 19; i++)
			inputHeureEnd.addItem(i+"h");
		panelGestion.add(labelFin);
		panelGestion.add(inputAnneeEnd);
		panelGestion.add(inputMoisEnd);
		panelGestion.add(inputJourEnd);
		panelGestion.add(inputHeureEnd);
		
		buttonAdd = new JButton("Ajouter le créneau");
		buttonAdd.addActionListener(this);
		panelGestion.add(buttonAdd);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		
		buttonDel = new JButton("Supprimer le créneau");
		buttonDel.addActionListener(this);
		buttonDel.setEnabled(false);
		panelGestion.add(buttonDel);
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.PAGE_AXIS));
		this.add(panelGestion);
	}
	
	
	private void makeTable() {
		ArrayList<Creneau> listeCreneaux = null;
		String[] header = {"id", "date début", "date fin"};
		Object[][] data = null;
		listeCreneaux = DatabaseManager.getAllCreneau();
		data = new Object[listeCreneaux.size()][3];
		int row = 0;
		for(Creneau creneau : listeCreneaux) {
			data[row][0] = creneau.getId();
			data[row][1] = creneau.getStrStart();
			data[row][2] = creneau.getStrEnd();
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
		ArrayList<Creneau> listeCreneaux = null;
		String[] header = {"id", "date début", "date fin"};
		Object[][] data = null;
		listeCreneaux = DatabaseManager.getAllCreneau();
		data = new Object[listeCreneaux.size()][3];
		int row = 0;
		for(Creneau creneau : listeCreneaux) {
			data[row][0] = creneau.getId();
			data[row][1] = creneau.getStrStart();
			data[row][2] = creneau.getStrEnd();
			row ++;
		}
		tableModel.setDataVector(data, header);
		tableModel.fireTableDataChanged();
		buttonDel.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == inputAnneeStart) {
			if((Integer)inputAnneeStart.getSelectedItem()%4 == 0 && inputMoisStart.getSelectedIndex() == 1)
				inputJourStart.addItem(29);
			else if((Integer)inputAnneeStart.getSelectedItem()%4 != 0 && inputMoisStart.getSelectedIndex() == 1)
				inputJourStart.removeItem(29);
		}
		if(e.getSource() == inputMoisStart) {
			inputJourStart.removeAllItems();
			int indexMois = inputMoisStart.getSelectedIndex();
			if(indexMois%2 == 0) {
				for(int i = 1; i <= 31; i++)
					inputJourStart.addItem(i);
			}
			else if(indexMois%2 == 1 && indexMois != 1) {
				for(int i = 1; i <= 30; i++)
					inputJourStart.addItem(i);
			}
			else if(indexMois == 1) {
				for(int i = 1; i <= 28; i++)
					inputJourStart.addItem(i);
				if((Integer)inputAnneeStart.getSelectedItem()%4 == 0)
					inputJourStart.addItem(29);
			}
		}
		if(e.getSource() == buttonAdd) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, (Integer)inputAnneeStart.getSelectedItem());
			cal.set(Calendar.MONTH, inputMoisStart.getSelectedIndex());
			cal.set(Calendar.DAY_OF_MONTH, (Integer)inputJourStart.getSelectedItem());
			cal.set(Calendar.HOUR_OF_DAY, inputHeureStart.getSelectedIndex() + 7);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Date debut = cal.getTime();
			cal.set(Calendar.YEAR, (Integer)inputAnneeEnd.getSelectedItem());
			cal.set(Calendar.MONTH, inputMoisEnd.getSelectedIndex());
			cal.set(Calendar.DAY_OF_MONTH, (Integer)inputJourEnd.getSelectedItem());
			cal.set(Calendar.HOUR_OF_DAY, inputHeureEnd.getSelectedIndex() + 7);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Date fin = cal.getTime();
			
			if(debut.compareTo(fin) >= 0) {
				JOptionPane.showMessageDialog(null, "Error: La date de début ne peut pas être supérieur à celle de fin!");
			}
			else {
				if(DatabaseManager.insertEntite(new Creneau(debut, fin), "creneau"))
					this.updateTable();
			}
		}
		else if(e.getSource() == buttonDel && table.getSelectedRow() != -1) {
			if(DatabaseManager.deleteEntite((Integer)tableModel.getValueAt(table.getSelectedRow(), 0), "creneau"))
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
