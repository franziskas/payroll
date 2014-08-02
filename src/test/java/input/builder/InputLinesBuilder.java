package input.builder;

import input.InputFile;
import input.InputLines;

import java.nio.file.Path;

public class InputLinesBuilder {

    private InputLines inputLines;

    public InputLinesBuilder(String file) {
	inputLines = new InputLines(new InputFile(file));
    }

    public InputLinesBuilder(Path path) {
	inputLines = new InputLines(new InputFile("") {

	    @Override
	    public Path getPath() {
		return path;
	    }

	});
    }

    public InputLines create() {
	return inputLines;
    }

}
