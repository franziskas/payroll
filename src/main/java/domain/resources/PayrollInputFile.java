package domain.resources;

import input.InputFile;
import input.InputLines;

public abstract class PayrollInputFile {

    protected String directory;

    protected PayrollInputFile(String directory) {
	this.directory = directory;
    }

    protected InputLines readFrom(String filename) {
	return new InputLines(new InputFile(directory + filename));
    }

}