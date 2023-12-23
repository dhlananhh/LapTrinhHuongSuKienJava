package lyQuocMinh_21105601;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class Event implements ActionListener, MouseListener{
	private FrmDSNuoc gui;
	private ListCountry list = new ListCountry();
	public Event (FrmDSNuoc gui) {
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(gui.btnAdd)) {
			addCountry();
		}
		else if(o.equals(gui.btnClear)) {
			
		}
		else if(o.equals(gui.btnUpdate)) {
			
		}
		else if(o.equals(gui.btnDelete)) {
			
		}
		else if(o.equals(gui.btnSearch)) {
			
		}
		
	}
	
	private void addCountry() {
		String name, capital, popula;
		boolean democracy;
		name = gui.txtCountry.getText();
		capital = gui.txtCapital.getText();
		popula = gui.txtPopula.getText();
		democracy = gui.comboDemo.getSelectedItem().equals("true");
		
		Country country = new Country(name, capital, Integer.parseInt(popula), democracy);
		if(list.them(country)) {
			JOptionPane.showMessageDialog(gui, "Thêm thành công");
			String[] row = {name, capital, popula};
			gui.model.addRow(row);
		}
		else {
			JOptionPane.showMessageDialog(gui, "Country đã tồn tại");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
