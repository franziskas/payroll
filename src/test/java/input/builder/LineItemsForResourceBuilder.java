package input.builder;

import static input.LineItems.INPUT_SEPERATOR;
import static input.LineItems.OUTPUT_SEPERATOR;

public class LineItemsForResourceBuilder extends LineItemsBuilder {
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";
    public static final String EMPLOYEE_TYPE = "Employee";
    public static final String FIRST_NAME2 = "first2";
    public static final String LAST_NAME2 = "last2";
    private static final String MANAGER_TYPE = "Manager";

    public LineItemsForResourceBuilder() {
	super(INPUT_SEPERATOR, SERIAL_NUMBER, FIRST_NAME, LAST_NAME,
		EMPLOYEE_TYPE);
    }

    public LineItemsForResourceBuilder withOtherValues() {
	setValues(LineItemsBuilder.OTHER_SERIAL_NUMBER, FIRST_NAME2,
		LAST_NAME2, MANAGER_TYPE);
	return this;
    }

    public LineItemsForResourceBuilder withOtherEmployee() {
	setValues(OTHER_SERIAL_NUMBER, FIRST_NAME2, LAST_NAME2, EMPLOYEE_TYPE);
	return this;
    }

    public LineItemsForResourceBuilder asOutput() {
	withSeperator(OUTPUT_SEPERATOR);
	switchFirstAndLastName();
	return this;
    }

    private void switchFirstAndLastName() {
	Object firstName = values[1];
	Object lastName = values[2];

	values[2] = firstName;
	values[1] = lastName;
    }

}
