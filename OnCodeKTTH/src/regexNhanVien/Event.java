package regexNhanVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Event implements ActionListener{
	Form gui;
	public Event(Form gui) {
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(gui.btnThem)) {
			if(validData() == true)
				JOptionPane.showMessageDialog(gui, "Thêm thành công");
		}	
	}
	public boolean validData(){
		String ma = gui.txtMa.getText();
		String ten = gui.txtTen.getText();
		String tuoi = gui.txtTuoi.getText();
		String email = gui.txtEmail.getText();
		if(ma.equals("") || ten.equals("") || tuoi.equals("") || email.equals("")){
			JOptionPane.showMessageDialog(gui, "Không được để trống");
			return false;
		}
		if(!ma.matches("NV[0-9]{8}")){
			JOptionPane.showMessageDialog(gui, "Mã nhân viên phải bắt đầu bằng 2 chữ cái “NV”, theo sau là 8 chữ số");
			gui.txtMa.requestFocus();
			return false;
		}
		if(!ten.matches("([A-Z][a-z]*)( [A-Z][a-z]*)*")){
			JOptionPane.showMessageDialog(gui, "Tên không hợp lệ");
			gui.txtTen.requestFocus();
			return false;
		}
		try {
			if(Integer.parseInt(tuoi)<18 || Integer.parseInt(tuoi)>60){
				JOptionPane.showMessageDialog(gui, "Tuổi nhân viên từ 18-60 tuổi");
				gui.txtTuoi.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(gui, "Tuổi phải nhập vào số");
			return false;
		}
		if(!email.matches("^[a-zA-Z][a-zA-Z0-9\\._]*@(yahoo|gmail|iuh)\\.[a-z]+$")){
			JOptionPane.showMessageDialog(gui, "Email phải nhập theo tiêu chuẩn. tenEmail@DomainName");
			gui.txtEmail.requestFocus();
			return false;
		}
		return true;
	}
}
