package input;

import static input.builder.TestFiles.KNOWN_FILE;
import static input.builder.TestFiles.KNOWN_FILE_PATH;
import static input.builder.TestFiles.UNKNOWN_FILE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;

import java.nio.file.Path;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InputFileTest {

    @Rule
    public ExpectedException expectedException = none();

    private static final String ERROR_MESSAGE = "File not found at "
	    + UNKNOWN_FILE;

    @Test
    public void givenKnownFileItProvidesAPath() {
	InputFile reader = new InputFile(KNOWN_FILE_PATH);

	Path path = reader.getPath();

	assertThat(path, matchesFilename(KNOWN_FILE));
    }

    @Test
    public void givenUnknownFileItThrowsAnIllegalArgumentException() {
	InputFile reader = new InputFile(UNKNOWN_FILE);

	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage(ERROR_MESSAGE);
	reader.getPath();
    }

    private Matcher<Path> matchesFilename(String fileName) {
	return new FeatureMatcher<Path, String>(is(fileName), "file name",
		"file name") {

	    @Override
	    protected String featureValueOf(Path actual) {
		return actual.getFileName().toString();
	    }
	};
    }
}
