package input.builder;

import static input.LineItems.SEPERATOR;
import input.LineItems;

public class LineItemsBuilder {
    public static final long SERIAL_NUMBER = 123L;
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";
    public static final String EMPLOYEE_TYPE = "employeeType";
    private static final long SERIAL_NUMBER2 = 1234L;
    private static final String FIRST_NAME2 = "first2";
    private static final String LAST_NAME2 = "last2";
    private static final String EMPLOYEE_TYPE2 = "employeeType2";

    private LineItems lineItems;

    private LineItemsBuilder(String seperator, Object... values) {
	String line = "";

	for (Object value : values) {
	    line += value;
	    line += seperator;
	}

	lineItems = new LineItems(
		line.substring(0, line.lastIndexOf(seperator)));
    }

    public LineItemsBuilder() {
	this(SEPERATOR, SERIAL_NUMBER, FIRST_NAME, LAST_NAME, EMPLOYEE_TYPE);
    }

    public LineItemsBuilder(String seperator) {
	this(seperator, SERIAL_NUMBER, FIRST_NAME, LAST_NAME, EMPLOYEE_TYPE);
    }

    public LineItems create() {
	return lineItems;
    }

    public LineItems createSecondInstance() {
	return new LineItemsBuilder(SEPERATOR, SERIAL_NUMBER2, FIRST_NAME2,
		LAST_NAME2, EMPLOYEE_TYPE2).create();
    }
}
