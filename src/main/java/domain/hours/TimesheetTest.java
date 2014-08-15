package domain.hours;

import static input.builder.LineItemsBuilder.OTHER_SERIAL_NUMBER;
import static input.builder.LineItemsBuilder.SERIAL_NUMBER;
import static input.builder.LineItemsForWorkingHoursBuilder.HOURS;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import input.builder.LineItemsForWorkingHoursBuilder;

import java.util.List;

import org.junit.Test;

public class TimesheetTest {
    private static final List<WorkingHours> NO_WORKING_HOURS = emptyList();
    private static final WorkingHours WORKING_HOURS = new WorkingHours(
	    new LineItemsForWorkingHoursBuilder().create());
    private static final WorkingHours WORKING_HOURS_DIFFERENT_DAY = new WorkingHours(
	    new LineItemsForWorkingHoursBuilder().forDifferentDay().create());
    private static final WorkingHours WORKING_HOURS_DIFFERENT_EMPLOYEE = new WorkingHours(
	    new LineItemsForWorkingHoursBuilder().withOtherValues().create());

    @Test
    public void givenAnEmptyListOfWorkingHoursItReturnsZeroHoursForSerialNumber() {
	Timesheet timesheet = new Timesheet(NO_WORKING_HOURS);

	assertThat(timesheet.getHoursFor(SERIAL_NUMBER), is(0));
    }

    @Test
    public void givenAnWorkingHoursForDifferentEmployeeItReturnsOnlyHoursForCorrectSerialNumber() {
	Timesheet timesheet = new Timesheet(
		asList(WORKING_HOURS_DIFFERENT_EMPLOYEE));

	assertThat(timesheet.getHoursFor(SERIAL_NUMBER), is(0));
	assertThat(timesheet.getHoursFor(OTHER_SERIAL_NUMBER), is(HOURS));
    }

    @Test
    public void givenMultipleWorkingHoursForSameEmployeeItReturnsSumOfHours() {
	Timesheet timesheet = new Timesheet(asList(WORKING_HOURS,
		WORKING_HOURS_DIFFERENT_DAY));

	assertThat(timesheet.getHoursFor(SERIAL_NUMBER), is(HOURS * 2));
    }
}
