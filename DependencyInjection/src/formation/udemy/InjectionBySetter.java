package formation.udemy;

public class InjectionBySetter {

	public static void main(String[] args) {

		Address a1 = new Address("Flower Street", "Thomas District");
		
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setFirstName("Tom");
		emp1.setLastName("Jerry");
		emp1.setAddress(a1);
		
		System.out.println();
		
		
		
		
		
		
	}

}
