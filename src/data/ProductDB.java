package data;

import java.sql.*;
import java.util.ArrayList;

import beans.Product;

public class ProductDB {

	public static ArrayList<Product> searchProducts(String searchString) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		ArrayList<Product> products = new ArrayList<Product>();

		// Create the query string using ? to identify parameters
		String query = "SELECT * FROM Product WHERE ProductDescription LIKE ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, "%" + searchString + "%");
			try (ResultSet rs = ps.executeQuery()) {
				Product product = null;
				while (rs.next()) {
					product = new Product();
					product.setCode(rs.getString("ProductCode"));
					product.setDescription(rs.getString("ProductDescription"));
					product.setPrice(rs.getDouble("ProductPrice"));
					products.add(product);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
		return products;
	}

	public static Product getProduct(String productCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		Product product = null;

		String query = "SELECT * FROM Product WHERE ProductCode = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, productCode);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					product = new Product();
					product.setCode(rs.getString("ProductCode"));
					product.setDescription(rs.getString("ProductDescription"));
					product.setPrice(rs.getDouble("ProductPrice"));
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
		return product;
	}

	public static int deleteProduct(String productCode) {

		// Implement...
		return 0;
	}

	public static int insertProduct(Product product) {

		// Implement...
		return 0;
	}

}