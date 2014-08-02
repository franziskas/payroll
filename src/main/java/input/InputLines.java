package input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputLines {

    public static final List<String> LINES_WITH_ERROR = new ArrayList<>();
    private List<String> lines;

    public InputLines(Path path) {
	try {
	    lines = Files.readAllLines(path);
	} catch (IOException e) {
	    lines = LINES_WITH_ERROR;
	    lines.add(e.getMessage());
	}
    }

    public List<String> getLines() {
	return lines;
    }
}
