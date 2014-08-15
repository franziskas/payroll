package domain.hours;

import static input.builder.LineItemsBuilder.SERIAL_NUMBER;
import static input.builder.LineItemsForWorkingHoursBuilder.DATE;
import static input.builder.LineItemsForWorkingHoursBuilder.OTHER_DATE;
import static input.builder.LineItemsForWorkingHoursBuilder.TIMESTAMP_TOO_SHORT;
import static input.builder.LineItemsForWorkingHoursBuilder.HOURS;
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

    private static final LineItems LINE_ITEMS_WITH_VALUES = new LineItemsForWorkingHoursBuilder()
	    .create();
    private static final LineItems LINE_ITEMS_TIMESTAMP_DIFFERENT_DATES = new LineItemsForWorkingHoursBuilder()
	    .withTimestampsOnDifferentDates().create();
    private static final LineItems LINE_ITEMS_TIMESTAMP_TOO_SHORT = new LineItemsForWorkingHoursBuilder()
	    .withTimestampTooShort().create();

    private static final LineItems EMPTY_LINE_ITEMS = new LineItems("");

    @Test
    public void givenWrongNumberOfLineItemsItThrowsAnIllegalArgumentException() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Input does not have 3 values: "
		+ EMPTY_LINE_ITEMS.getValues().toString());

	new WorkingHours(EMPTY_LINE_ITEMS);
    }

    @Test
    public void givenTimestampsOnDifferentDaysItThrowsAnIllegalArgumentException() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException
		.expectMessage("Start and end date of the timestamp no not match: "
			+ DATE + ", " + OTHER_DATE);

	new WorkingHours(LINE_ITEMS_TIMESTAMP_DIFFERENT_DATES);
    }

    @Test
    public void givenTimestampsThatAreTooShortThrowsAnIllegalArgumentException() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Timestamp too short: "
		+ TIMESTAMP_TOO_SHORT);

	new WorkingHours(LINE_ITEMS_TIMESTAMP_TOO_SHORT);
    }

    @Test
    public void givenCorrectNumberOfLineItemsItCalculatesWorkingHours() {
	WorkingHours hours = new WorkingHours(LINE_ITEMS_WITH_VALUES);

	assertThat(hours.getSerialNumber(), is(SERIAL_NUMBER));
	assertThat(hours.getHours(), is(HOURS));
    }

}
