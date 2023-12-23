package countryInformation;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Country_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnContent, pnNorth, pnCenter, pnSouth;
	private JLabel lblCountryName, lblCapital, lblPop, lblDemocracy;
	private JTextField txtCountryName, txtCapital, txtPop;
	private JButton btnAdd, btnClear, btnUpdate, btnDelete, btnSearch;
	private JComboBox<String> cmbDemocracy;
	private DefaultTableModel model;
	private JTable table;
	private CountryList list;
	private CountryDatabase database;
	
	
	public Country_GUI() {
		super("Using JTable Component & IO Stream");
		database = new CountryDatabase();
		list = new CountryList();
		buildGUI();
//		try {
//			loadData();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	
	public void buildGUI() {
		setTitle("Using JTable Component & IO Stream");
		setSize(1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		createAndDisplayGUI();
	}
	
	
	public void createAndDisplayGUI() {
		// panel content
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		// panel north
		pnNorth = new JPanel();
		pnNorth.add(Box.createRigidArea(new Dimension(0, 40)));
		JLabel heading = new JLabel("Using JTable Component");
		pnNorth.add(heading);
		pnContent.add(pnNorth, BorderLayout.NORTH);
		heading.setFont(new Font("Arial", Font.BOLD, 25));
		heading.setForeground(Color.BLUE);
		
		// panel center
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		
		// panel center chia làm 2 phần: pnInfo và pnTable
		// pnInfo chứa các thông tin nhập của quốc gia
		JPanel pnInfo = new JPanel();
		pnInfo.setLayout(new GridLayout());
		pnCenter.add(pnInfo);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		//row 1
		lblCountryName = new JLabel("Enter country: ");
		txtCountryName = new JTextField(30);
		b1.add(lblCountryName);
		b1.add(txtCountryName);
		b1.add(Box.createHorizontalStrut(530));
		
		//row 2
		lblCapital = new JLabel("Enter capital: ");
		txtCapital = new JTextField(60);
		b2.add(lblCapital);
		b2.add(txtCapital);
		
		//row 3
		lblPop = new JLabel("Enter population: ");
		txtPop = new JTextField(60);
		b3.add(lblPop);
		b3.add(txtPop);
		
		//row 4
		lblDemocracy = new JLabel("Enter democracy: ");
		cmbDemocracy = new JComboBox<String>();
		cmbDemocracy.addItem("true");
		cmbDemocracy.addItem("false");
		b4.add(lblDemocracy);
		b4.add(cmbDemocracy);
		b4.add(Box.createHorizontalStrut(530));
		
		lblCountryName.setPreferredSize(lblDemocracy.getPreferredSize());
		lblCapital.setPreferredSize(lblDemocracy.getPreferredSize());
		lblPop.setPreferredSize(lblDemocracy.getPreferredSize());
		
		// thêm các box vào panel info
		pnInfo.add(b);
		b.add(b1);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b2);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b3);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		b.add(b4);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
		
		createTable();
		
		// panel south
		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add");
		btnClear = new JButton("Clear");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnSearch = new JButton("Search");
		
		pnSouth.add(btnAdd);
		pnSouth.add(btnClear);
		pnSouth.add(btnUpdate);
		pnSouth.add(btnDelete);
		pnSouth.add(btnSearch);
		
		// subscribe ActionListener
		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSearch.addActionListener(this);
		
		// create container
		Container container = getContentPane();
		container.add(pnContent);
	}
	
	
	public void createTable() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Country");
		model.addColumn("Capital");
		model.addColumn("Population");
		model.addColumn("Democracy");
		TableColumn column = new TableColumn();
		column.setPreferredWidth(100);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(970, 330));
		
		pnCenter.add(scrollPane);
		pnCenter.add(pnTable);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		new Country_GUI().setVisible(true);
	}
}
