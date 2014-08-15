package domain.resources;

import static input.builder.LineItemsForResourceBuilder.EMPLOYEE_TYPE;
import static input.builder.LineItemsForResourceBuilder.FIRST_NAME;
import static input.builder.LineItemsForResourceBuilder.LAST_NAME;
import static input.builder.LineItemsForResourceBuilder.SERIAL_NUMBER;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;
import input.LineItems;
import input.builder.LineItemsForResourceBuilder;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PayrollResourceTest {
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
	assertThat(resource.getFirstName(), is(FIRST_NAME));
	assertThat(resource.getLastName(), is(LAST_NAME));
	assertThat(resource.getEmployeeType(), is(EMPLOYEE_TYPE));
    }

    @Test
    public void givenLineItemsItReturnsThemAsAListOfStrings() {
	PayrollResource resource = new PayrollResource(LINE_ITEMS_WITH_VALUES);

	List<String> expectedValues = asList(Long.toString(SERIAL_NUMBER),
		LAST_NAME, FIRST_NAME, EMPLOYEE_TYPE);

	assertThat(resource.getItemsAsList(), is(expectedValues));
    }

}