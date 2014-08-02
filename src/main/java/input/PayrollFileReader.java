package input;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class PayrollFileReader {

    private final Path path;

    public PayrollFileReader(String pathToFile) {
	path = FileSystems.getDefault().getPath(pathToFile);
    }

    public boolean fileExists() {
	return Files.exists(path);
    }

}
