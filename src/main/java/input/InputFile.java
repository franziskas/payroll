package input;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputFile {

    private final Path path;

    public InputFile(String pathToFile) {
	path = FileSystems.getDefault().getPath(pathToFile);
    }

    public Path getPath() {
	if (Files.exists(path))
	    return path;

	throw new IllegalArgumentException("File not found at "
		+ path.toString());
    }
}
