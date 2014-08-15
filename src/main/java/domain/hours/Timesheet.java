package domain.hours;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

public class Timesheet {
    private static final String PAYROLL_TEMPLATE = " ordinary hours worked: {0} * {1} = {2}";
    private static final String CURRENCY = " Euro";
    private static final String HOURLY_WAGE = "10" + CURRENCY;

    private Map<Long, Integer> regularHoursPerSerialNumber;
    private Map<Long, Integer> overtimeHoursPerSerialNumber;

    public Timesheet(List<WorkingHours> hours) {
	regularHoursPerSerialNumber = calculateHours(hours,
		WorkingHours::getRegularHours);
	overtimeHoursPerSerialNumber = calculateHours(hours,
		WorkingHours::getOvertimeHours);
    }

    private Map<Long, Integer> calculateHours(List<WorkingHours> hours,
	    ToIntFunction<? super WorkingHours> getHoursFunction) {
	return hours.stream()//
		.collect(//
			groupingBy(WorkingHours::getSerialNumber,//
				summingInt(getHoursFunction)));
    }

    public int getRegularHoursFor(long serialNumber) {
	return getHours(serialNumber, regularHoursPerSerialNumber);
    }

    private int getHours(long serialNumber, Map<Long, Integer> timesheet) {
	Integer hours = timesheet.get(serialNumber);

	if (hours == null)
	    return 0;

	return hours;
    }

    public String getOutput(long serialNumber) {
	int hours = getRegularHoursFor(serialNumber);
	return format(PAYROLL_TEMPLATE, hours, HOURLY_WAGE,
		getTotalStandardPay(hours));
    }

    private static String getTotalStandardPay(int hours) {
	return hours * 10 + CURRENCY;
    }

    public int getOvertimeHoursFor(long serialNumber) {
	return getHours(serialNumber, overtimeHoursPerSerialNumber);
    }
}
