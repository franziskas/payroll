package input;

import static java.nio.file.Files.readAllLines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputLines {

    public static final List<LineItems> LINES_WITH_ERROR = new ArrayList<>();
    private List<LineItems> lines = new ArrayList<>();

    public InputLines(InputFile file) {
	try {
	    addAllLinesReadFromFile(file);
	} catch (IOException e) {
	    recordError(e);
	}
    }

    private void recordError(IOException e) {
	lines = LINES_WITH_ERROR;
	lines.add(new LineItems(e.getMessage()));
    }

    private void addAllLinesReadFromFile(InputFile file) throws IOException {
	for (String line : readAllLines(file.getPath())) {
	    lines.add(new LineItems(line));
	}
    }

    public List<LineItems> getLines() {
	return lines;
    }
}
