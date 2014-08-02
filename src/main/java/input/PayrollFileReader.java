package input;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class PayrollFileReader {

    public boolean fileExists(String filename) {
	Path path = FileSystems.getDefault().getPath(
		"src/test/resources/" + filename);
	return Files.exists(path);
    }

}
