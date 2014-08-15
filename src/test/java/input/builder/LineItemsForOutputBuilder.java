package input.builder;

import static domain.resources.PayrollResource.OUTPUT_SEPERATOR;
import static java.text.MessageFormat.format;

public class LineItemsForOutputBuilder extends LineItemsBuilder {

    private static final String PAYROLL_TEMPLATE = " ordinary hours worked: {0} * {1} = {2}";
    private static final String CURRENCY = " Euro";
    private static final String HOURLY_WAGE = "10" + CURRENCY;
    public static final String STANDARD_OUTPUT = format(PAYROLL_TEMPLATE,
	    HOURS, HOURLY_WAGE, getTotalStandardPay(HOURS));

    protected LineItemsForOutputBuilder(String seperator, Object[] values) {
	super(seperator, values);
    }

    public LineItemsForOutputBuilder() {
	super(OUTPUT_SEPERATOR, SERIAL_NUMBER, LAST_NAME, FIRST_NAME
		+ STANDARD_OUTPUT);
    }

    public LineItemsForOutputBuilder withOtherEmployee() {
	setValues(OTHER_SERIAL_NUMBER, OTHER_LAST_NAME, OTHER_FIRST_NAME
		+ STANDARD_OUTPUT);
	return this;
    }

    private static String getTotalStandardPay(int hours) {
	return hours * 10 + CURRENCY;
    }
}
