package input.builder;

import static input.LineItems.INPUT_SEPERATOR;
import static input.LineItems.OUTPUT_SEPERATOR;
import input.LineItems;

public class LineItemsBuilder {
    public static final long SERIAL_NUMBER = 123L;
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";
    public static final String EMPLOYEE_TYPE = "Employee";
    private static final long SERIAL_NUMBER2 = 1234L;
    public static final String FIRST_NAME2 = "first2";
    public static final String LAST_NAME2 = "last2";
    private static final String MANAGER_TYPE = "Manager";

    private String seperator;
    private Object[] values;

    private LineItemsBuilder(String seperator, Object... values) {
	this.seperator = seperator;
	this.values = values;
    }

    public LineItemsBuilder() {
	this(INPUT_SEPERATOR, SERIAL_NUMBER, FIRST_NAME, LAST_NAME,
		EMPLOYEE_TYPE);
    }

    public LineItemsBuilder withSeperator(String seperator) {
	this.seperator = seperator;
	return this;
    }

    public LineItems create() {
	String line = "";

	for (Object value : values) {
	    line += value;
	    line += seperator;
	}

	return new LineItems(line.substring(0, line.lastIndexOf(seperator)));
    }

    public LineItemsBuilder withOtherValues() {
	setValues(SERIAL_NUMBER2, FIRST_NAME2, LAST_NAME2,
		MANAGER_TYPE);
	return this;
    }

    public LineItemsBuilder withOtherEmployee() {
	setValues(SERIAL_NUMBER2, FIRST_NAME2, LAST_NAME2, EMPLOYEE_TYPE);
	return this;
    }

    private void setValues(Object... values) {
	this.values = values;
    }

    public LineItemsBuilder asOutput() {
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
