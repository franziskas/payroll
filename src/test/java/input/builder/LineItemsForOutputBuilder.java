package input.builder;

import static domain.resources.PayrollResource.OUTPUT_SEPERATOR;
import static java.text.MessageFormat.format;

public class LineItemsForOutputBuilder extends LineItemsBuilder {

    private static final String PAYROLL_TEMPLATE = " ordinary hours worked: {0} * {1} = {2} hours overtime worked: {3} * {4} = {5} Total: {6}";

    private static final int HOURLY_RATE = 10;
    private static final int OVERTIME_RATE = 15;
    private static final String CURRENCY = " Euro";
    private static final String HOURLY_WAGE = HOURLY_RATE + CURRENCY;
    private static final String OVERTIME_RENUMERATION = OVERTIME_RATE
	    + CURRENCY;
    private static final int OVERTIME_HOURS = 0;

    public static final String STANDARD_OUTPUT = format(PAYROLL_TEMPLATE,
	    REGULAR_HOURS, HOURLY_WAGE, getTotalStandardPay(REGULAR_HOURS),
	    OVERTIME_HOURS, OVERTIME_RENUMERATION,
	    getTotalOvertimePay(OVERTIME_HOURS),
	    getTotalPay(REGULAR_HOURS, OVERTIME_HOURS));

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
	return hours * HOURLY_RATE + CURRENCY;
    }

    private static String getTotalPay(int regularHours, int overtimeHours) {
	return (regularHours * HOURLY_RATE + overtimeHours * OVERTIME_RATE)
		+ CURRENCY;
    }

    private static String getTotalOvertimePay(int overtimeHours) {
	return overtimeHours * OVERTIME_RATE + CURRENCY;
    }

}
