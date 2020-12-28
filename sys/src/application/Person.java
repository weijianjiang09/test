package application;

import javafx.beans.property.SimpleStringProperty;

public  class Person {
	public final SimpleStringProperty Num;
	public final SimpleStringProperty firstName;
	public final SimpleStringProperty lastName;
	public final SimpleStringProperty email;

	public Person(String num,String fName, String lName, String email) {
		this.Num = new SimpleStringProperty(num);
		this.firstName = new SimpleStringProperty(fName);
		this.lastName = new SimpleStringProperty(lName);
		this.email = new SimpleStringProperty(email);
	}
	public String getnum() {
		return Num.get();
	}

	public void setnum(String num) {
		Num.set(num);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String fName) {
		firstName.set(fName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String fName) {
		lastName.set(fName);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String fName) {
		email.set(fName);
	}
}


