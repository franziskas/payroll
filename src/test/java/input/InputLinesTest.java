package input;

import static input.InputLines.LINES_WITH_ERROR;
import static input.PathBuilder.EMPTY_FILE;
import static input.PathBuilder.FILE_NOT_FOUND;
import static input.PathBuilder.FILE_WITH_IO_EXCEPTION;
import static input.PathBuilder.FILE_WITH_TWO_LINES;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InputLinesTest {

    private static final String FIRST_LINE = "first line";
    private static final String SECOND_LINE = "second line";

    @Test
    public void givenAPathToAnEmptyFileItReturnsEmptyLines() {
	InputLines lines = new InputLines(EMPTY_FILE);

	assertThat(lines.getLines(), is(empty()));
    }

    @Test
    public void givenAnIoExceptionItReturnsLinesWithError() {
	InputLines lines = new InputLines(FILE_WITH_IO_EXCEPTION);

	assertThat(lines.getLines(), is(LINES_WITH_ERROR));
	assertThat(lines.getLines().get(0), is(new LineItems(FILE_NOT_FOUND)));
    }

    @Test
    public void givenAPathToAFileWithTwoLinesItReturnsAListOfThoseLines() {
	InputLines lines = new InputLines(FILE_WITH_TWO_LINES);

	assertThat(lines.getLines(),
		hasItems(new LineItems(FIRST_LINE), new LineItems(SECOND_LINE)));
    }
}
