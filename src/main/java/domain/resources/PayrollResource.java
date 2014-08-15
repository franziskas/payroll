package domain.resources;

import static java.util.Arrays.asList;
import input.LineItems;

import java.util.List;

import util.ValueObject;

public class PayrollResource extends ValueObject {

    private long serialNumber;
    private String firstName;
    private String employeeType;
    private String lastName;

    public PayrollResource(LineItems lineItems) {
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
	return asList(Long.toString(serialNumber), lastName, firstName,
		employeeType);
    }
}
