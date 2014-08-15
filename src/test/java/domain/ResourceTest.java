package domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;
import input.LineItems;
import input.builder.LineItemsBuilder;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ResourceTest {
    private static final long SERIAL_NUMBER = 123L;
    private static final String FIRST_NAME = "first";
    private static final String LAST_NAME = "last";
    private static final String EMPLOYEE_TYPE = "employeeType";
    private static final LineItems LINE_ITEMS_WITH_VALUES = new LineItemsBuilder()
	    .create();
    private static final LineItems EMPTY_LINE_ITEMS = new LineItems("");
    @Rule
    public ExpectedException expectedException = none();

    @Test
    public void givenWrongNumberOfLineItemsItThrowsAnIllegalArgumentException() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Input does not have 4 values: "
		+ EMPTY_LINE_ITEMS.getValues().toString());

	new Resource(EMPTY_LINE_ITEMS);
    }

    @Test
    public void givenCorrectNumberOfLineItemsItAssignsThoseToResourceFields() {
	Resource resource = new Resource(LINE_ITEMS_WITH_VALUES);

	assertThat(resource.getSerialNumber(), is(SERIAL_NUMBER));
	assertThat(resource.getFirstName(), is(FIRST_NAME));
	assertThat(resource.getLastName(), is(LAST_NAME));
	assertThat(resource.getEmployeeType(), is(EMPLOYEE_TYPE));
    }

    @Test
    public void givenLineItemsItReturnsThemAsAListOfStrings() {
	Resource resource = new Resource(LINE_ITEMS_WITH_VALUES);
	List<String> expectedValues = Arrays.asList(
		Long.toString(SERIAL_NUMBER), FIRST_NAME, LAST_NAME,
		EMPLOYEE_TYPE);

	assertThat(resource.getItemsAsList(), is(expectedValues));
    }

}
