package input;

import static input.InputLines.LINES_WITH_ERROR;
import static input.builder.TestFiles.EMPTY_FILE;
import static input.builder.TestFiles.FILE_WITH_TWO_LINES;
import static input.builder.TestFiles.UNKNOWN_FILE;
import static input.builder.TestFiles.pathWithIoException;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import input.builder.InputLinesBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InputLinesTest {

    private static final String FIRST_LINE = "first line";
    private static final String SECOND_LINE = "second line";

    @Test
    public void givenAPathToAnEmptyFileItReturnsEmptyLines() {
	InputLines lines = new InputLinesBuilder(EMPTY_FILE).create();

	assertThat(lines.getLines(), is(empty()));
    }

    @Test
    public void givenAnIoExceptionItReturnsLinesWithError() {
	InputLines lines = new InputLinesBuilder(
		pathWithIoException(UNKNOWN_FILE)).create();

	assertThat(lines.getLines(), is(LINES_WITH_ERROR));
	assertThat(lines.getLines().get(0), is(new LineItems(UNKNOWN_FILE)));
    }

    @Test
    public void givenAPathToAFileWithTwoLinesItReturnsAListOfThoseLines() {
	InputLines lines = new InputLinesBuilder(FILE_WITH_TWO_LINES).create();

	assertThat(lines.getLines(),
		hasItems(new LineItems(FIRST_LINE), new LineItems(SECOND_LINE)));
    }
}
