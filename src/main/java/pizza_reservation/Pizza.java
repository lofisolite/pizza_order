package pizza_reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pizza {
	String base;
	String topping;
	String cheese;
	
	public Pizza(String base, String topping, String cheese) {
		this.base = base;
		this.topping = topping;
		this.cheese = cheese;
	}

	public String getBase() {
		return base;
	}

	public String getTopping() {
		return topping;
	}

	public String getCheese() {
		return cheese;
	}
	
	public void addPizzaInDB(Connection connection) throws SQLException {
		String newPizzaStatement = "INSERT INTO pizza (base, topping, cheese) VALUES ('"+ getBase() + "', '" + getTopping() +  "', '" + getCheese() +"')";
		try(Statement statement = connection.createStatement()){
			statement.executeUpdate(newPizzaStatement);
		}
	}

	public Pizza getPizzaFromDB(Connection connection) throws SQLException {
		String queryPizzaStatement = "SELECT * FROM pizza ORDER BY id DESC LIMIT 1";
		try(Statement statement = connection.createStatement()){
			
			ResultSet pizzaResult = statement.executeQuery(queryPizzaStatement);
			pizzaResult.next();
			String base    = pizzaResult.getString("base");
			String topping = pizzaResult.getString("topping");
			String cheese  = pizzaResult.getString("cheese");
			
			return new Pizza(base, topping, cheese);
		}
	}
}
