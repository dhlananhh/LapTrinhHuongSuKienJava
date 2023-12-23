package lyQuocMinh_21105601;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmDSNuoc extends JFrame{
	JPanel pnBorder, pnHead, pnCenter,pnTable, pnButton;
	JLabel lbCountry, lbCapital, lbPopula, lbDemocracy;
	JButton btnAdd, btnClear, btnUpdate, btnDelete, btnSearch;
	JTextField txtCountry, txtCapital, txtPopula;
	DefaultTableModel model;
	JComboBox comboDemo;
	JTable table;
	
	public void createGUI() {
		pnBorder =new JPanel();
		pnBorder.setLayout(new BorderLayout());
		JLabel lbTitle = new JLabel("Using JTable Component");
		Font font = new Font("Arial", Font.BOLD, 20);
		lbTitle.setFont(font);
		lbTitle.setForeground(Color.blue);
		pnHead = new JPanel();
		pnHead.add(lbTitle);
		pnBorder.add(pnHead, BorderLayout.NORTH);
		
		pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		Box b, b1 ,b2, b3, b4;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b.add(b4);
		b.add(Box.createVerticalStrut(10));
		pnCenter.add(b);
		pnCenter.add(Box.createVerticalStrut(5));
		
		lbCountry = new JLabel("Enter country:");
		lbCapital = new JLabel("Enter capital: ");
		lbPopula = new JLabel("Enter population:");
		lbDemocracy = new JLabel("Select democracy:");
		txtCountry = new JTextField();
		txtCapital = new JTextField();
		txtPopula = new JTextField();
		comboDemo = new JComboBox();
		comboDemo.addItem("true");
		comboDemo.addItem("false");
		
		b1.add(lbCountry);
		b1.add(txtCountry);
		b1.add(Box.createHorizontalStrut(300));
		b2.add(lbCapital);
		b2.add(txtCapital);
		b3.add(lbPopula);
		b3.add(txtPopula);
		b4.add(lbDemocracy);
		b4.add(comboDemo);
		b4.add(Box.createHorizontalStrut(300));
		
		lbPopula.setPreferredSize(lbDemocracy.getPreferredSize());
		lbCountry.setPreferredSize(lbDemocracy.getPreferredSize());
		lbCapital.setPreferredSize(lbPopula.getPreferredSize());
		
		pnBorder.add(pnCenter, BorderLayout.CENTER);
		taoBang();
		
		btnAdd = new JButton("Add");
		btnClear = new JButton("Clear");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnSearch = new JButton("Search");
		
		pnButton = new JPanel();
		pnButton.setLayout(new BoxLayout(pnButton, BoxLayout.X_AXIS));
		pnButton.add(Box.createHorizontalStrut(150));
		pnButton.add(btnAdd);
		pnButton.add(Box.createHorizontalStrut(5));
		pnButton.add(btnClear);
		pnButton.add(Box.createHorizontalStrut(5));
		pnButton.add(btnUpdate);
		pnButton.add(Box.createHorizontalStrut(5));
		pnButton.add(btnDelete);
		pnButton.add(Box.createHorizontalStrut(5));
		pnButton.add(btnSearch);
		
		pnBorder.add(pnButton, BorderLayout.SOUTH);
		
		this.add(pnBorder);	
	}
	public void taoBang() {
		pnTable = new JPanel();
		String[] column = {"Country", "Capital", "Population", "Democracy"};
		model = new DefaultTableModel(column, 0);
		table = new JTable(model);
		pnTable.add(table);
		pnCenter.add(pnTable);
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(650, 250));
		pnTable.add(sp);
	}
	public FrmDSNuoc () {
		this.setTitle("Using JTable Component & IO Stream");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createGUI();
		Event event = new Event(this);
		btnAdd.addActionListener(event);
	}
	public static void main(String[] args) {
		new FrmDSNuoc().setVisible(true);
	}
}
