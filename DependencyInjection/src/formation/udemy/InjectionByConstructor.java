package formation.udemy;

public class InjectionByConstructor {

	public static void main(String[] args) {


		// This is dependency by constructor: whenever we create an Employee object, it will be dependent on the Address class.
		// If anything happens to the Address class (modified or deleted) Employee class and its objects will not work.
		System.out.println( new Employee(1, "Durand", "Robert", new Address("Patate Street", "Tomate District")));
		
		
		
		
		
		
		

	}

}
