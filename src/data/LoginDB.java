package data;

import java.sql.*;

public class LoginDB {

	public static String getSalt(String username) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();

		String query = "SELECT * FROM UserPass WHERE Username = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getString("Salt");
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(connection);
		}
		return "";
	}
}