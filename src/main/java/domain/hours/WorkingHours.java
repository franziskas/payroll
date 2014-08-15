package domain.hours;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import input.LineItems;
import util.ValueObject;

public class WorkingHours extends ValueObject {

    private static final int OVERTIME_START = 8;
    private long serialNumber;
    private int regularHours;
    private int overtimeHours = 0;

    public WorkingHours(LineItems lineItems) {
	lineItems.validate(3);

	serialNumber = Long.parseLong(lineItems.getValue(0));
	int hoursWorked = validateAndCalculateHours(lineItems);

	setHours(hoursWorked);
    }

    private void setHours(int hoursWorked) {
	regularHours = hoursWorked;

	if (hoursWorked > OVERTIME_START) {
	    regularHours = OVERTIME_START;
	    overtimeHours = hoursWorked - OVERTIME_START;
	}
    }

    private int validateAndCalculateHours(LineItems lineItems) {
	String startTimestamp = lineItems.getValue(1);
	String endTimestamp = lineItems.getValue(2);

	validateTimestamps(startTimestamp, endTimestamp);

	return abs(getHoursOf(endTimestamp) - getHoursOf(startTimestamp));
    }

    private int getHoursOf(String timestamp) {
	return parseInt(timestamp.substring(8, 10));
    }

    private void validateTimestamps(String startTimestamp, String endTimestamp) {
	validateLength(startTimestamp);
	validateLength(endTimestamp);

	String startDate = getDateString(startTimestamp);
	String endDate = getDateString(endTimestamp);

	if (startDate.equals(endDate)) {
	    return;
	}
	throw new IllegalArgumentException(
		"Start and end date of the timestamp no not match: "
			+ startDate + ", " + endDate);
    }

    private void validateLength(String timestamp) {
	if (timestamp.length() < 12)
	    throw new IllegalArgumentException("Timestamp too short: "
		    + timestamp);
    }

    private String getDateString(String timestamp) {
	return timestamp.substring(0, 8);
    }

    public Long getSerialNumber() {
	return serialNumber;
    }

    public int getRegularHours() {
	return regularHours;
    }

    public int getOvertimeHours() {
	return overtimeHours;
    }

}
