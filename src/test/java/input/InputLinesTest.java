package input;

import static input.InputLines.LINES_WITH_ERROR;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.Test;

public class InputLinesTest {
    private static final Path EMPTY_FILE = getPath("emptyFile");
    private static final Path FILE_WITH_TWO_LINES = getPath("fileWithTwoLines");

    private static final String FIRST_LINE = "first line";
    private static final String SECOND_LINE = "second line";
    private static final String FILE_NOT_FOUND = "file not found";

    @Test
    public void givenAPathToAnEmptyFileItReturnsEmptyLines() throws Exception {
	InputLines lines = new InputLines(EMPTY_FILE);

	assertThat(lines.getLines(), is(empty()));
    }

    @Test
    public void givenAnIoExceptionItReturnsLinesWithError() throws Exception {
	InputLines lines = new InputLines(pathWithIoException());

	assertThat(lines.getLines(), is(LINES_WITH_ERROR));
	assertThat(lines.getLines().get(0), is(FILE_NOT_FOUND));
    }

    @Test
    public void givenAPathToAFileWithTwoLinesItReturnsAListOfThoseLines()
	    throws Exception {
	InputLines lines = new InputLines(FILE_WITH_TWO_LINES);

	assertThat(lines.getLines(), hasItems(FIRST_LINE, SECOND_LINE));
    }

    private static Path getPath(String filename) {
	return new InputFile("src\\test\\resources\\" + filename).getPath();
    }

    private Path pathWithIoException() {
	return FileSystems.getDefault().getPath(FILE_NOT_FOUND);
    }

}
