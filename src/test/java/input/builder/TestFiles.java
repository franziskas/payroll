package input.builder;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TestFiles {
    public static final String TEST_FILES_FOLDER = "src\\test\\resources\\";
    public static final String EMPTY_FILE = getPathToTestFile("emptyFile");
    public static final String FILE_WITH_TWO_LINES = getPathToTestFile("fileWithTwoLines");
    public static final String KNOWN_FILE = "knownFile";
    public static final String KNOWN_FILE_PATH = getPathToTestFile(KNOWN_FILE);
    public static final String UNKNOWN_FILE = getPathToTestFile("unknown");

    private static String getPathToTestFile(String filename) {
	return TEST_FILES_FOLDER + filename;
    }

    public static Path pathWithIoException(String filename) {
	return FileSystems.getDefault().getPath(filename);
    }
}
