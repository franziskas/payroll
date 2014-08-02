package input;

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

    private static final String TEST_FILES_FOLDER = "src\\test\\resources\\";
    private static final String KNOWN_FILE = "knownFile";
    private static final String PATH_TO_UNKNOWN_FILE = getPathToTestFile("unknown");
    private static final String ERROR_MESSAGE = "File not found at "
	    + PATH_TO_UNKNOWN_FILE;

    @Test
    public void givenKnownFileItProvidesAPath() {
	InputFile reader = new InputFile(getPathToTestFile(KNOWN_FILE));

	Path path = reader.getPath();

	assertThat(path, matchesFilename(KNOWN_FILE));
    }

    @Test
    public void givenUnknownFileItThrowsAnIllegalArgumentException() {
	InputFile reader = new InputFile(PATH_TO_UNKNOWN_FILE);

	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage(ERROR_MESSAGE);
	Path path = reader.getPath();

	assertThat(path, matchesFilename(PATH_TO_UNKNOWN_FILE));
    }

    private static String getPathToTestFile(String filename) {
	return TEST_FILES_FOLDER + filename;
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
