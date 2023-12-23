package connectDB;

import java.sql.Connection;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	
	
	public static ConnectDB getInstance() {
		return instance;
	}
}
