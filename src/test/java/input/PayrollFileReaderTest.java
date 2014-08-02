package input;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PayrollFileReaderTest {
    private static final String TEST_FILES_FOLDER = "src/test/resources/";
    private static final String PATH_TO_UNKNOWN_FILE = TEST_FILES_FOLDER
	    + "unknown";
    private static final String PATH_TO_KNOWN_FILE = TEST_FILES_FOLDER
	    + "knownFile";

    @Test
    public void fileIsNotFound() {
	PayrollFileReader reader = new PayrollFileReader(PATH_TO_UNKNOWN_FILE);

	boolean fileExists = reader.fileExists();

	assertThat(fileExists, is(false));
    }

    @Test
    public void fileIsFound() {
	PayrollFileReader reader = new PayrollFileReader(PATH_TO_KNOWN_FILE);

	boolean fileExists = reader.fileExists();

	assertThat(fileExists, is(true));
    }

}
