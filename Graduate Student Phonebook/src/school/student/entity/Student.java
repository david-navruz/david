package school.student.entity;

public class Student {

	private String lastName;
	private String firstName;
	private String department;
	private String graduationYear;
	private String phone;
	private int index;

	public Student() {
	
	}

	public Student(String lastName, String firstName, String department, 
			String graduationYear, String phone) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.department = department;
		this.graduationYear = graduationYear;
		this.phone = phone;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Student [lastName=" + lastName + ", firstName=" + firstName + ", department=" + department
				+ ", graduationYear=" + graduationYear + ", phone=" + phone + ", index=" + index + "]";
	}

}
