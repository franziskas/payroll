package input;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PayrollFileReaderTest {

    @Test
    public void fileIsNotFound() {
	PayrollFileReader reader = new PayrollFileReader();

	boolean fileExists = reader.fileExists("");

	assertThat(fileExists, is(false));
    }
}
