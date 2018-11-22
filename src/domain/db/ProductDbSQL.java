package domain.db;

import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDbSQL implements ProductDb {
	Properties properties;
	String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX31?currentSchema=r0612365";

	public ProductDbSQL(Properties properties) throws ClassNotFoundException {
		this.properties = new Properties();
		this.properties.setProperty("user", "local_r0612365");
		this.properties.setProperty("password", "hrO-0TTFLpWgYKye");
		this.properties.setProperty("ssl", "true");
		this.properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		Class.forName("org.postgresql.Driver");
	}


	@Override
	public Product get(String id) {
		Product product = new Product();
		String sql = "SELECT * FROM product WHERE productid = ?";
		try (Connection connection = DriverManager.getConnection(url, properties);
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				String name = result.getString("name");
				String description = result.getString("description");
				String price = result.getString("price");

				product = new Product(name, description, Double.parseDouble(price));
			}
			return product;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
	}
	}

	@Override
	public List<Product> getAll() {
			String sql = "SELECT * FROM product";
			try (Connection connection = DriverManager.getConnection(url, properties);
				 PreparedStatement statement = connection.prepareStatement(sql)) {
				ResultSet result = statement.executeQuery();
				List<Product> products = new ArrayList<>();

				while(result.next()) {
					String productid = result.getString("productid");
					String name = result.getString("name");
					String description = result.getString("description");
					String price = result.getString("price");

					Product product = new Product(productid, name, description, Double.parseDouble(price));
					products.add(product);
				}
				statement.executeQuery();
				return products;
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
	}

	@Override
	public void add(Product product) {

			String sql = "INSERT INTO product (productid, name, description, price)" +" VALUES(?,?,?,?)";

			try (Connection connection = DriverManager.getConnection(url, properties);
				 PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.setString(1, product.getProductid());
				statement.setString(2, product.getName());
				statement.setString(3, product.getDescription());
                statement.setDouble(4, product.getPrice());

				statement.execute();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

	}

	@Override
	public void update(Product product) {
			String sql = "UPDATE product SET name=?,description=?,price=? WHERE productid=?";

			try (Connection connection = DriverManager.getConnection(url, properties);
				 PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.setString(1, product.getProductid());
				statement.setString(2, product.getName());
				statement.setString(3, product.getDescription());
				statement.setDouble(4, product.getPrice());

				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
	}

	@Override
	public void delete(String id) {
			String sql = "DELETE FROM product WHERE productid=?" ;
			try (Connection connection = DriverManager.getConnection(url, properties);
				 PreparedStatement statement = connection.prepareStatement(sql);) {
				statement.setString(1, id);
				statement.execute();
			} catch (SQLException E) {
				throw new DbException(E.getMessage(), E);
			}
	}
}
