package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConnectDB.ConnectDB;
import entity.CauThu;
import entity.ViTriThiDau;

public class CauThu_DAO {
	public List<CauThu> getAllCauThu(){
		List<CauThu> ds = new ArrayList<CauThu>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {
			String sql = "select * from CauThu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ds.add(new CauThu(rs.getString(1),
						rs.getString(2),
						new ViTriThiDau(rs.getString(4))
						, rs.getInt(3)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public List<CauThu> getAllCauThuTheoVTTD(String maVTTD){
		List<CauThu> ds = new ArrayList<CauThu>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from CauThu where maVTTD = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maVTTD);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new CauThu(rs.getString(1),
						rs.getString(2),
						new ViTriThiDau(rs.getString(4))
						, rs.getInt(3)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public boolean create(CauThu ct) {
		Connection con = ConnectDB.getInstance().getCon();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into CauThu values(?,?,?,?)");
			stmt.setString(1, ct.getMaCT());
			stmt.setString(2, ct.getTenCT());
			stmt.setInt(3, ct.getTuoi());
			stmt.setString(4, ct.getVttd().getMa());
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Mã cầu thủ này đã tồn tại");
		}
		return n>0;
	}
	public boolean delete(String mact) {
		Connection con = ConnectDB.getInstance().getCon();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from CauThu where maCT = ?");
			stmt.setString(1, mact);
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
}
