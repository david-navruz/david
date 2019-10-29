package formation.udemy;

public class Employee {

	private int id;
	private String lastName;
	private String firstName;
	private Address address; // with this, Employee class becomes dependent on the Address class
	// When we create an Employee object, this object will be dependent to the
	// Address class.

	
	
	// This is dependency by constructor: whenever we create an Employee object, it will be dependent on the Address class.
	// If anything happens to the Address class (modified or deleted) Employee class and its objects will not work.
	public Employee(int id, String lastName, String firstName, Address address) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
	}

	public Employee() {
		
	}
	
	public Employee(int id, String lastName, String firstName) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", address=" + address
				+ "]";
	}

}
