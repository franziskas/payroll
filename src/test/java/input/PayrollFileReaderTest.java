package input;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PayrollFileReaderTest {

    private static final String PATH_TO_UNKNOWN_FILE = "unknown";
    private static final String PATH_TO_KNOWN_FILE = "knownFile";

    @Test
    public void fileIsNotFound() {
	PayrollFileReader reader = new PayrollFileReader();

	boolean fileExists = reader.fileExists(PATH_TO_UNKNOWN_FILE);

	assertThat(fileExists, is(false));
    }

    @Test
    public void fileIsFound() {
	PayrollFileReader reader = new PayrollFileReader();

	boolean fileExists = reader.fileExists(PATH_TO_KNOWN_FILE);

	assertThat(fileExists, is(true));
    }
}
