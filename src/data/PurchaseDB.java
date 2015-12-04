package data;

import java.sql.*;
import java.util.ArrayList;

import beans.Purchase;

public class PurchaseDB {

	public static int insert(Purchase purchase) {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();

		String query = "INSERT INTO Purchase (FirstName, LastName, CCNumber, TotalPrice) " +
				"VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, purchase.getFirstName());
			ps.setString(2, purchase.getLastName());
			ps.setString(3, purchase.getCcNumber());
			ps.setDouble(4, purchase.getTotalPrice());
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			pool.freeConnection(connection);
		}
	}

	public static ArrayList<Purchase> getPurchases() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();

		String query = "SELECT * FROM Purchase";
		try (PreparedStatement ps = connection.prepareStatement(query);
				 ResultSet rs = ps.executeQuery();) {
			Purchase purchase = null;
			while (rs.next()) {
				purchase = new Purchase();
				purchase.setId(rs.getInt("PurchaseId"));
				purchase.setFirstName(rs.getString("FirstName"));
				purchase.setLastName(rs.getString("LastName"));
				purchase.setCcNumber(rs.getString("CCNumber"));
				purchase.setTotalPrice(rs.getDouble("TotalPrice"));
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
		return purchases;
	}

	public static int delete(int purchaseId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();

		String query = 
				"DELETE FROM Purchase WHERE PurchaseID = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {   
			ps.setInt(1, purchaseId);
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			pool.freeConnection(connection);
		}
	}
}