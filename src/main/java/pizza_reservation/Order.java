package pizza_reservation;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Order {
	public static Scanner scanner = null;
	
	public static void main(String[] args) throws Exception {
		scanner = new Scanner(System.in);
		
		final String[] INGREDIENTS_BASE    = { "viande", "poisson", "légumes" };
		final String[] INGREDIENTS_TOPPING = { "champignons", "olive", "ananas" };
		final String[] INGREDIENTS_CHEESE  = { "mozzarella", "emmental", "cheddar" };
		
		String base    = getUserInput("Que voulez vous comme type de pizza ? "
				+ "(saisir 'viande', 'poisson' ou 'légumes')", INGREDIENTS_BASE);
		String topping = getUserInput("Que voulez vous comme accompagnement ? "
				+ "(saisir 'champignons ', 'olive' ou 'ananas')", INGREDIENTS_TOPPING);
		String cheese  = getUserInput("Que voulez vous comme fromage ? "
				+ "(saisir 'mozzarella', 'emmental' ou 'cheddar')", INGREDIENTS_CHEESE);
		
		Pizza newPizza = new Pizza(base, topping, cheese);
		try {
			Connection connection = DatabaseConnection.getConnection();
			newPizza.addPizzaInDB(connection);
			Pizza PizzaFromDB = newPizza.getPizzaFromDB(connection);
			System.out.println(displayPizza(PizzaFromDB));
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getUserInput(String userRequest, String[] ingredientsArray) throws Exception {
		List<String> ingredientsList = Arrays.asList(ingredientsArray);
		System.out.println(userRequest);
		String userResponse = scanner.nextLine();
		try {
			if(!ingredientsList.contains(userResponse)) {
				throw new Error("Cet ingrédient n'est pas dans la liste, merci de relancer le programme.");
			}
		} catch(Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		return userResponse;	
	}
	
	public static String displayPizza(Pizza pizza) {
		return "Votre pizza est composé de " 
				+ pizza.getBase() + " pour la base, " 
				+  pizza.getTopping() + " pour l'accompagnement et " 
				+  pizza.getCheese() + " pour le fromage.";
	}

}
