package input;

import static input.InputLines.LINES_WITH_ERROR;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.Test;

public class InputLinesTest {
    private static final String EMPTY_FILE = "src\\test\\resources\\emptyFile";
    private static final Path PATH_TO_EMPTY_FILE = getPath(EMPTY_FILE);
    private static final String FILE_NOT_FOUND = "file not found";

    @Test
    public void givenAPathToAnEmptyFileItReturnsEmptyLines() throws Exception {
	InputLines lines = new InputLines(PATH_TO_EMPTY_FILE);

	assertThat(lines.getLines(), is(empty()));
    }

    @Test
    public void givenAnIoExceptionItReturnsLinesWithError() throws Exception {
	InputLines lines = new InputLines(pathWithIoException());

	assertThat(lines.getLines(), is(LINES_WITH_ERROR));
	assertThat(lines.getLines().get(0), is(FILE_NOT_FOUND));
    }

    private static Path getPath(String filename) {
	return new InputFile(filename).getPath();
    }

    private Path pathWithIoException() {
	return FileSystems.getDefault().getPath(FILE_NOT_FOUND);
    }

}
