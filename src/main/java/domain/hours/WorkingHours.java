package domain.hours;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import input.LineItems;

public class WorkingHours {

    private long serialNumber;
    private int hours;

    public WorkingHours(LineItems lineItems) {
	lineItems.validate(3);

	serialNumber = Long.parseLong(lineItems.getValue(0));
	this.hours = validateAndCalculateHours(lineItems);
    }

    private int validateAndCalculateHours(LineItems lineItems) {
	String startTimestamp = lineItems.getValue(1);
	String endTimestamp = lineItems.getValue(2);

	validateTimestamps(startTimestamp, endTimestamp);

	return abs(getHoursOf(endTimestamp) - getHoursOf(startTimestamp));
    }

    private int getHoursOf(String timestamp) {
	return parseInt(timestamp.substring(9, 10));
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

    public int getHours() {
	return hours;
    }

}
