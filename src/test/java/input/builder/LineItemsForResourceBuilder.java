package input.builder;

import static input.LineItems.INPUT_SEPERATOR;

public class LineItemsForResourceBuilder extends LineItemsBuilder {
    public static final String EMPLOYEE_TYPE = "Employee";
    private static final String MANAGER_TYPE = "Manager";

    public LineItemsForResourceBuilder() {
	super(INPUT_SEPERATOR, SERIAL_NUMBER, LineItemsBuilder.FIRST_NAME,
		LineItemsBuilder.LAST_NAME, EMPLOYEE_TYPE);
    }

    public LineItemsForResourceBuilder withOtherValues() {
	setValues(LineItemsBuilder.OTHER_SERIAL_NUMBER,
		LineItemsBuilder.OTHER_FIRST_NAME, LineItemsBuilder.OTHER_LAST_NAME,
		MANAGER_TYPE);
	return this;
    }

    public LineItemsForResourceBuilder withOtherEmployee() {
	setValues(OTHER_SERIAL_NUMBER, LineItemsBuilder.OTHER_FIRST_NAME,
		LineItemsBuilder.OTHER_LAST_NAME, EMPLOYEE_TYPE);
	return this;
    }

}
