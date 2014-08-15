package domain;

import input.LineItems;

import java.util.Arrays;
import java.util.List;

public class Resource {

    private long serialNumber;
    private String firstName;
    private String employeeType;
    private String lastName;

    public Resource(LineItems lineItems) {
	lineItems.validate(4);

	serialNumber = Long.parseLong(lineItems.getValue(0));
	firstName = lineItems.getValue(1);
	lastName = lineItems.getValue(2);
	employeeType = lineItems.getValue(3);
    }

    public long getSerialNumber() {
	return serialNumber;
    }

    public String getFirstName() {
	return firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public String getEmployeeType() {
	return employeeType;
    }

    public List<String> getItemsAsList() {
	return Arrays.asList(Long.toString(serialNumber),
		firstName, lastName, employeeType);
    }
}
