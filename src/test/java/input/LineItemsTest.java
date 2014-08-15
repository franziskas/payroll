package input;

import static input.LineItems.INPUT_SEPERATOR;
import static input.builder.LineItemsBuilder.EMPLOYEE_TYPE;
import static input.builder.LineItemsBuilder.FIRST_NAME;
import static input.builder.LineItemsBuilder.LAST_NAME;
import static input.builder.LineItemsBuilder.SERIAL_NUMBER;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import input.builder.LineItemsBuilder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import domain.Resource;

public class LineItemsTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static final String EMPTY_LINE = "";
    private static final String FIRST_VALUE = "first";
    private static final String SECOND_VALUE = "second";
    private static final String LINE_WITH_TWO_VALUES = FIRST_VALUE + ","
	    + SECOND_VALUE;
    private static final String LINE_WITH_TWO_VALUES_WRONG_SEPERATOR = FIRST_VALUE
	    + ";" + SECOND_VALUE;

    @Test
    public void givenAnEmptyLineItemItReturnsNoValues() {
	LineItems items = new LineItems(EMPTY_LINE);

	assertThat(items.getValues(), is(empty()));
    }

    @Test
    public void givenTwoValuesInLineItemItSplitsThem() {
	LineItems items = new LineItems(LINE_WITH_TWO_VALUES);

	assertThat(items.getValues(), hasItems(FIRST_VALUE, SECOND_VALUE));
    }

    @Test
    public void givenNoSeperatorThereIsNoSplit() {
	LineItems items = new LineItems(LINE_WITH_TWO_VALUES_WRONG_SEPERATOR);

	assertThat(items.getValues(),
		hasItem(LINE_WITH_TWO_VALUES_WRONG_SEPERATOR));
    }

    @Test
    public void givenEmptyItemsValidationOfAtLeastOneValueFails() {
	LineItems items = new LineItems("");

	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("Input does not have 1 values: ");

	items.validate(1);
    }

    @Test
    public void givenEmptyItemsValidationOfZeroValuesSucceeds() {
	LineItems items = new LineItems("");

	items.validate(0);
    }

    @Test
    public void givenOneItemValidationOfOneValueSucceeds() {
	LineItems items = new LineItems("item" + INPUT_SEPERATOR);

	items.validate(1);
    }

    @Test
    public void givenAResourceLineItemsCanProvideTheContentsAsStrings() {
	LineItems items = new LineItems(new Resource(
		new LineItemsBuilder().create()));

	assertThat(items.getValue(0), is(Long.toString(SERIAL_NUMBER)));
	assertThat(items.getValue(1), is(FIRST_NAME));
	assertThat(items.getValue(2), is(LAST_NAME));
	assertThat(items.getValue(3), is(EMPLOYEE_TYPE));
    }

    @Test
    public void givenAResourceLineItemsCanProvideTheContentsAsOneOutputString() {
	LineItems items = new LineItems(new Resource(
		new LineItemsBuilder().create()));

	String expectedOutput = SERIAL_NUMBER + " | " + FIRST_NAME + " | "
		+ LAST_NAME + " | " + EMPLOYEE_TYPE;

	assertThat(expectedOutput, is(items.getOutput()));
    }
}
