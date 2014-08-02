package input;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class PathBuilder {
    public static final String TEST_FILES_FOLDER = "src\\test\\resources\\";
    public static final Path EMPTY_FILE = getPath("emptyFile");
    public static final Path FILE_WITH_TWO_LINES = getPath("fileWithTwoLines");
    public static final String FILE_NOT_FOUND = "file not found";
    public static final Path FILE_WITH_IO_EXCEPTION = pathWithIoException(FILE_NOT_FOUND);
    public static final String KNOWN_FILE = "knownFile";
    public static final String KNOWN_FILE_PATH = getPathToTestFile(KNOWN_FILE);

    private static Path pathWithIoException(String filename) {
	return FileSystems.getDefault().getPath(filename);
    }

    private static Path getPath(String filename) {
	return new InputFile(TEST_FILES_FOLDER + filename).getPath();
    }

    private static String getPathToTestFile(String filename) {
	return TEST_FILES_FOLDER + filename;
    }

    static final String PATH_TO_UNKNOWN_FILE = getPathToTestFile("unknown");
}
