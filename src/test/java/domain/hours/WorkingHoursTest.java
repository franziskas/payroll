package domain.hours;

import static input.builder.LineItemsBuilder.REGULAR_HOURS;
import static input.builder.LineItemsBuilder.SERIAL_NUMBER;
import static input.builder.LineItemsForWorkingHoursBuilder.DATE;
import static input.builder.LineItemsForWorkingHoursBuilder.OTHER_DATE;
import static input.builder.LineItemsForWorkingHoursBuilder.OVERTIME;
import static input.builder.LineItemsForWorkingHoursBuilder.TIMESTAMP_TOO_SHORT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;
import input.LineItems;
import input.builder.LineItemsForWorkingHoursBuilder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WorkingHoursTest {

    @Rule
    public ExpectedException expectedException = none();

    private static final LineItems INPUT = new LineItemsForWorkingHoursBuilder()
	    .create();
    private static final LineItems INPUT_DIFFERENT_DATES = new LineItemsForWorkingHoursBuilder()
	    .withTimestampsOnDifferentDates().create();
    private static final LineItems INPUT_TOO_SHORT = new LineItemsForWorkingHoursBuilder()
	    .withTimestampTooShort().create();
    private static final LineItems EMPTY_INPUT = new LineItems("");
    private static final LineItems INPUT_OVERTIME = new LineItemsForWorkingHoursBuilder()
	    .withOvertime().create();
    private static final int OVERTIME_LIMIT = 8;

    @Test
    public void givenWrongNumberOfLineItemsItThrowsAnIllegalArgumentException() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Input does not have 3 values: "
		+ EMPTY_INPUT.getValues().toString());

	new WorkingHours(EMPTY_INPUT);
    }

    @Test
    public void givenTimestampsOnDifferentDaysItThrowsAnIllegalArgumentException() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException
		.expectMessage("Start and end date of the timestamp no not match: "
			+ DATE + ", " + OTHER_DATE);

	new WorkingHours(INPUT_DIFFERENT_DATES);
    }

    @Test
    public void givenTimestampsThatAreTooShortThrowsAnIllegalArgumentException() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Timestamp too short: "
		+ TIMESTAMP_TOO_SHORT);

	new WorkingHours(INPUT_TOO_SHORT);
    }

    @Test
    public void givenCorrectNumberOfLineItemsItCalculatesWorkingHours() {
	WorkingHours hours = new WorkingHours(INPUT);

	assertThat(hours.getSerialNumber(), is(SERIAL_NUMBER));
	assertThat(hours.getRegularHours(), is(REGULAR_HOURS));
    }

    @Test
    public void giveOvertimeItCalculatesWorkingHoursAndOvertimeCorrectly() {
	WorkingHours hours = new WorkingHours(INPUT_OVERTIME);

	assertThat(hours.getSerialNumber(), is(SERIAL_NUMBER));
	assertThat(hours.getRegularHours(), is(OVERTIME_LIMIT));
	assertThat(hours.getOvertimeHours(), is(OVERTIME));
    }
}
