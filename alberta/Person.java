package alberta;
public class Person {
	private String fullName;
	private String id;

	public Person(String fullName, String id) {
		this.fullName = fullName;
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getID() {
		return id;
	}
}