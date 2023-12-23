package demoGUI;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditJTableDemo extends JFrame implements ActionListener {
	private JButton btnAdd, btnRemove, btnExit;
	private JTextField txtName, txtFirstName;
	private DefaultTableModel model;
	private JTable tblSV;
	
	
	public EditJTableDemo() {
		super("Table Demo");
		String[] cols = {"Họ Sinh viên", "Tên Sinh viên"};
		model = new DefaultTableModel(cols, 0);
		tblSV = new JTable(model);
		JScrollPane pane = new JScrollPane(tblSV);
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Ho"));
		txtFirstName = new JTextField(20);
		p1.add(txtFirstName);
		p1.add(new JLabel("Ten"));
		txtName = new JTextField(20);
		p1.add(txtName);
		
		JPanel p2 = new JPanel();
		btnAdd = new JButton("Thêm");
		btnRemove = new JButton("Xóa");
		btnExit = new JButton("Thoát");
		p2.add(btnAdd);
		p2.add(btnRemove);
		p2.add(btnExit);
		
		JPanel pTop, pBottom;
		pTop = new JPanel();
		add(p1, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		
		btnExit.addActionListener(this);
		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		
		setSize(600, 420);
		setLocationRelativeTo(null);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
			if (txtName.getText().equals("") || txtFirstName.getText().equals(""))
					JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu trước.");
			else {
				Object[] obj = new Object[2];
					obj[0] = txtFirstName.getText();
					obj[1] = txtName.getText();
					model.addRow(obj);
					this.txtName.setText("");
					this.txtFirstName.setText("");
			}
		} else if (o.equals(btnRemove)) {
			if (tblSV.getSelectedRow() == -1)
				JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xóa");
			else {
				if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này không ?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					model.removeRow(tblSV.getSelectedRow());
			}
		} else if (o.equals(btnExit)) {
			System.exit(0);
		}
	}

	
	public static void main(String[] args) {
		new EditJTableDemo().setVisible(true);
	}
}
