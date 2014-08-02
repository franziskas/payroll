package input;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LineItemsTest {

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

}
