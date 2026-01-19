package Database;

import java.sql.Connection;
import java.sql.DriverManager;

import automation.constant.CT_pageURL;

public class DBConnection {
	private static final String URL = CT_pageURL.Database_connect;
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	// Tạo kết nối database (Connection).
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
