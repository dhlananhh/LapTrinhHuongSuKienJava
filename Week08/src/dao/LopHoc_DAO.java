package dao;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LopHoc;


public class LopHoc_DAO {
	private static LopHoc_DAO instance = new LopHoc_DAO();
	
	
	public static LopHoc_DAO getInstance() {
        if (instance == null)
            instance = new LopHoc_DAO();
        return instance;
    }
	
	
	public List<LopHoc> getAllLopHoc() {
		List<LopHoc> dsLopHoc = new ArrayList<LopHoc>();
		Connection con = ConnectDB.getInstance().getConnection();
		
		try {
			String sql = "select * from LopHoc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maLop = rs.getString(1);
				String tenLop = rs.getString(2);
				String gvcn = rs.getString(3);
				LopHoc lh = new LopHoc(maLop, tenLop, gvcn);
				dsLopHoc.add(lh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsLopHoc;
	}
	
	
	public boolean addLopHoc (LopHoc lop) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into LopHoc values (?, ?, ?)");
			stmt.setString(1, lop.getMaLop());
			stmt.setString(2, lop.getTenLop());
			stmt.setString(3, lop.getGvcn());
		} catch (SQLException exc) {
			exc.printStackTrace();
		} finally {
			close(stmt);
		}
		return n > 0;
	}
	
	
	public boolean updateLopHoc (LopHoc lop) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String	sql = 	"update LopHoc "
						+ "set tenLop = ?, "
						+ "set gvcn = ?" 
						+ "where maLop = ?";
		int n = 0;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(2, lop.getTenLop());
			stmt.setString(3, lop.getGvcn());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	
	
	public boolean deleteLopHoc (String maLop) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from LopHoc where maLop = ?";
		int n = 0;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maLop);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return n > 0;
	}
	
	
	private void close (PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
