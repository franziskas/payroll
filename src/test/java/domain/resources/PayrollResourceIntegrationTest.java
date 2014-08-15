package domain.resources;

import static domain.resources.PayrollResource.OUTPUT_SEPERATOR;
import static input.builder.LineItemsBuilder.FIRST_NAME;
import static input.builder.LineItemsBuilder.LAST_NAME;
import static input.builder.LineItemsBuilder.SERIAL_NUMBER;
import static input.builder.LineItemsForResourceBuilder.EMPLOYEE_TYPE;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;
import input.LineItems;
import input.builder.LineItemsBuilder;
import input.builder.LineItemsForResourceBuilder;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PayrollResourceIntegrationTest {
    @Rule
    public ExpectedException expectedException = none();

    private static final LineItems LINE_ITEMS_WITH_VALUES = new LineItemsForResourceBuilder()
	    .create();
    private static final LineItems EMPTY_LINE_ITEMS = new LineItems("");

    @Test
    public void givenWrongNumberOfLineItemsItThrowsAnIllegalArgumentException() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Input does not have 4 values: "
		+ EMPTY_LINE_ITEMS.getValues().toString());

	new PayrollResource(EMPTY_LINE_ITEMS);
    }

    @Test
    public void givenCorrectNumberOfLineItemsItAssignsThoseToResourceFields() {
	PayrollResource resource = new PayrollResource(LINE_ITEMS_WITH_VALUES);

	assertThat(resource.getSerialNumber(), is(SERIAL_NUMBER));
	assertThat(resource.getFirstName(), is(LineItemsBuilder.FIRST_NAME));
	assertThat(resource.getLastName(), is(LineItemsBuilder.LAST_NAME));
	assertThat(resource.getEmployeeType(), is(EMPLOYEE_TYPE));
    }

    @Test
    public void givenLineItemsItReturnsThemAsAListOfStrings() {
	PayrollResource resource = new PayrollResource(LINE_ITEMS_WITH_VALUES);

	List<String> expectedValues = asList(Long.toString(SERIAL_NUMBER),
		LineItemsBuilder.LAST_NAME, LineItemsBuilder.FIRST_NAME,
		EMPLOYEE_TYPE);

	assertThat(resource.getItemsAsList(), is(expectedValues));
    }

    @Test
    public void givenALineItemItCanProvideTheContentsAsOneOutputString() {
	PayrollResource items = new PayrollResource(
		new LineItemsForResourceBuilder().create());

	String expectedOutput = SERIAL_NUMBER + OUTPUT_SEPERATOR + LAST_NAME
		+ OUTPUT_SEPERATOR + FIRST_NAME;

	assertThat(expectedOutput, is(items.getOutput()));
    }

}
