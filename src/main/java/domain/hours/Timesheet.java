package domain.hours;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.List;
import java.util.Map;

public class Timesheet {
    private static final String PAYROLL_TEMPLATE = " ordinary hours worked: {0} * {1} = {2}";
    private static final String CURRENCY = " Euro";
    private static final String HOURLY_WAGE = "10" + CURRENCY;

    private Map<Long, Integer> hoursPerSerialNumber;

    public Timesheet(List<WorkingHours> hours) {
	hoursPerSerialNumber = hours.stream().collect(
		groupingBy(WorkingHours::getSerialNumber,
			summingInt(WorkingHours::getHours)));
    }

    public int getHoursFor(long serialNumber) {
	Integer hours = hoursPerSerialNumber.get(serialNumber);

	if (hours == null)
	    return 0;

	return hours;
    }

    public String getOutput(long serialNumber) {
	int hours = getHoursFor(serialNumber);
	return format(PAYROLL_TEMPLATE, hours, HOURLY_WAGE,
		getTotalStandardPay(hours));
    }

    private static String getTotalStandardPay(int hours) {
	return hours * 10 + CURRENCY;
    }

}
