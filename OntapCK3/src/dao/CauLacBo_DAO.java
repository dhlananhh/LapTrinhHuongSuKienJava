package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import entity.CauLacBo;

public class CauLacBo_DAO {
	public List<CauLacBo> getAllCLB(){
		List<CauLacBo> ds = new ArrayList<CauLacBo>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		try {
			String sql = "select * from CauLacBo";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ds.add(new CauLacBo(rs.getString(1), rs.getString(2)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
}
