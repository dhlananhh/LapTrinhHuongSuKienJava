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
import entity.CauLacBo;
import entity.VanDongVien;

public class VanDongVien_DAO {
	public List<VanDongVien> getAllVDV(){
		List<VanDongVien> ds = new ArrayList<VanDongVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {
			String sql = "select * from VanDongVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ds.add(new VanDongVien(rs.getString(1), 
						rs.getString(2), 
						new CauLacBo(rs.getString(4)), 
						rs.getInt(3)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public List<VanDongVien> getAllVDVTheoCLB(String maCLB){
		List<VanDongVien> ds = new ArrayList<VanDongVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from VanDongVien where maCLB = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCLB);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ds.add(new VanDongVien(rs.getString(1), 
						rs.getString(2), 
						new CauLacBo(rs.getString(4)), 
						rs.getInt(3)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public boolean create(VanDongVien vdv) {
		Connection con = ConnectDB.getInstance().getCon();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into VanDongVien values(?,?,?,?)");
			stmt.setString(1, vdv.getMaVDV());
			stmt.setString(2, vdv.getTenVDV());
			stmt.setInt(3, vdv.getTuoi());
			stmt.setString(4, vdv.getClb().getMa());
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Mã vận động viên này đã tồn tại!");
		}
		return n>0;
	}
	public boolean delete(String maVDV) {
		Connection con = ConnectDB.getInstance().getCon();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from VanDongVien where maVDV = ?");
			stmt.setString(1, maVDV);
			n = stmt.executeUpdate();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Xóa thất bại!");
		}
		return n>0;
	}
}
