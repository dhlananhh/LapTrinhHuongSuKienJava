package demoGUI;


import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class JListDemo extends JFrame implements ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private JList<String> lstEntry;
    private JTextField txtSelected;
	
	
    public JListDemo() {
    	setTitle("JList Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 230);
        setLocationRelativeTo(null);
        setResizable(false);

        String[] entries = { "Entry 1", "Entry 2", "Entry 3", "Entry 4", "Entry 5", "Entry 6" };
        lstEntry = new JList<String>(entries);
        lstEntry.setVisibleRowCount(4);
        lstEntry.setPreferredSize(new Dimension(60, 120));

        JScrollPane listPane = new JScrollPane(lstEntry);
        JLabel lblSelected = new JLabel("Last Selected: ");

        txtSelected = new JTextField(15);

        JPanel pCen = new JPanel();
        pCen.setBorder(BorderFactory.createTitledBorder(null, "Simple JList"));
        pCen.add(listPane);
        add(pCen, BorderLayout.CENTER);

        JPanel pSouth = new JPanel();
        pSouth.setBorder(BorderFactory.createTitledBorder(null, "List Selection"));
        pSouth.add(lblSelected);
        pSouth.add(txtSelected);
        add(pSouth, BorderLayout.SOUTH);

        lstEntry.addListSelectionListener(this);
    }
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		txtSelected.setText(lstEntry.getSelectedValue().toString());
	}
	
	
	public static void main(String[] args) {
		new JListDemo().setVisible(true);
	}
}
