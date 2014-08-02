package input;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class InputFile {

    private final Path path;

    public InputFile(String pathToFile) {
	path = FileSystems.getDefault().getPath(pathToFile);
    }

    public Path getPath() {
	return path;
    }

}
