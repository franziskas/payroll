package input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputLines {

    public static final List<LineItems> LINES_WITH_ERROR = new ArrayList<>();
    private List<LineItems> lines = new ArrayList<>();

    public InputLines(Path path) {
	try {
	    for (String line : Files.readAllLines(path)) {
		lines.add(new LineItems(line));
	    }

	} catch (IOException e) {
	    lines = LINES_WITH_ERROR;
	    lines.add(new LineItems(e.getMessage()));
	}
    }

    public List<LineItems> getLines() {
	return lines;
    }
}
