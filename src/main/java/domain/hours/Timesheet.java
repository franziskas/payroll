package domain.hours;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.List;
import java.util.Map;

public class Timesheet {

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

}
