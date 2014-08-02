package input;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;

import org.junit.Test;

public class PayrollFileReaderTest {
    private static final String TEST_FILES_FOLDER = "src/test/resources/";
    private static final String UNKNOWN_FILE = "unknown";
    private static final String KNOWN_FILE = "knownFile";

    @Test
    public void pathIsReturnedIfFound() {
	PayrollFileReader reader = new PayrollFileReader(
		getPathToTestFile(KNOWN_FILE));

	Path path = reader.getPath();

	assertThat(path.getFileName().toString(), is(KNOWN_FILE));
    }

    @Test
    public void pathIsReturnedEvenIfNotFound() {
	PayrollFileReader reader = new PayrollFileReader(
		getPathToTestFile(UNKNOWN_FILE));

	Path path = reader.getPath();

	assertThat(path.getFileName().toString(), is(UNKNOWN_FILE));
    }

    private String getPathToTestFile(String filename) {
	return TEST_FILES_FOLDER + filename;
    }
}
