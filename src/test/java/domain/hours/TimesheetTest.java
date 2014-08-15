package domain.hours;

import static input.builder.LineItemsBuilder.OTHER_SERIAL_NUMBER;
import static input.builder.LineItemsBuilder.REGULAR_HOURS;
import static input.builder.LineItemsBuilder.SERIAL_NUMBER;
import static input.builder.LineItemsForWorkingHoursBuilder.OVERTIME;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import input.builder.LineItemsBuilder;
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
    private static final WorkingHours WORKING_HOURS_OVERTIME = new WorkingHours(
	    new LineItemsForWorkingHoursBuilder().withOvertime().create());

    @Test
    public void givenAnEmptyListOfWorkingHoursItReturnsZeroHoursForSerialNumber() {
	Timesheet timesheet = new Timesheet(NO_WORKING_HOURS);

	assertThat(timesheet.getRegularHoursFor(SERIAL_NUMBER), is(0));
    }

    @Test
    public void givenAnWorkingHoursForDifferentEmployeeItReturnsOnlyHoursForCorrectSerialNumber() {
	Timesheet timesheet = new Timesheet(
		asList(WORKING_HOURS_DIFFERENT_EMPLOYEE));

	assertThat(timesheet.getRegularHoursFor(SERIAL_NUMBER), is(0));
	assertThat(timesheet.getRegularHoursFor(OTHER_SERIAL_NUMBER),
		is(LineItemsBuilder.REGULAR_HOURS));
    }

    @Test
    public void givenMultipleWorkingHoursForSameEmployeeItReturnsSumOfHours() {
	Timesheet timesheet = new Timesheet(asList(WORKING_HOURS,
		WORKING_HOURS_DIFFERENT_DAY));

	assertThat(timesheet.getRegularHoursFor(SERIAL_NUMBER),
		is(REGULAR_HOURS * 2));
    }

    @Test
    public void givenAnWorkingHoursWithOvertimeItDevidesRegularHoursAndOvertime() {
	Timesheet timesheet = new Timesheet(asList(WORKING_HOURS_OVERTIME));

	assertThat(timesheet.getRegularHoursFor(SERIAL_NUMBER),
		is(REGULAR_HOURS));
	assertThat(timesheet.getOvertimeHoursFor(SERIAL_NUMBER), is(OVERTIME));
    }
}
