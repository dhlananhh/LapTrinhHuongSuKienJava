package demoGUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListEditDemo extends JFrame implements ListSelectionListener, ActionListener {
	private static final long serialVersionUID = 1L;
    JButton btnAdd, btnRemove;
    JTextField txtName;
    DefaultListModel<String> listModelName;
    JList<String> listName;
    
    
    public ListEditDemo() {
        setTitle("JList Edit Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 320);
        setLocationRelativeTo(null);
        setResizable(false);

        listModelName = new DefaultListModel<String>();
        listName = new JList<String>(listModelName);
        add(new JScrollPane(listName), BorderLayout.CENTER);

        JPanel pRight, pTop, pBottom;

        // top panel
        pTop = new JPanel();
        pTop.add(new JLabel("Input Name"));
        pTop.add(txtName = new JTextField(15));

        // bottom panel
        pBottom = new JPanel();
        pBottom.add(btnAdd = new JButton("Add Item"));
        pBottom.add(btnRemove = new JButton("Remove Item"));

        // right panel
        pRight = new JPanel(new BorderLayout());
        pRight.add(pTop, BorderLayout.NORTH);
        pRight.add(pBottom, BorderLayout.CENTER);

        add(pRight, BorderLayout.EAST);

        // Lắng nghe sự kiện
        txtName.addActionListener(this);
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
        if (o == btnAdd) {
            String name = txtName.getText().trim();
            if (name.equalsIgnoreCase(""))
                JOptionPane.showMessageDialog(this, "Please input name!");
            else {
                listModelName.addElement(name);
                txtName.setText("");
            }
            focusTextField(txtName);
        } else if (o.equals(btnRemove))
            listModelName.removeElement(listName.getSelectedValue());
	}

	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void focusTextField(JTextField text) {
        text.selectAll();
        text.requestFocus();
        return;
    }

	
	public static void main(String[] args) {
		new ListEditDemo().setVisible(true);
	}
}
