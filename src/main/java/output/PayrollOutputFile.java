package output;

import static java.text.MessageFormat.format;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import domain.hours.Timesheet;
import domain.resources.PayrollResource;

public class PayrollOutputFile {
    public static final String FILENAME_TEMPLATE = "{0}-{1}.txt";

    private PayrollResource resource;

    private Timesheet timesheet;

    public PayrollOutputFile(PayrollResource resource, Timesheet timesheet) {
	this.resource = resource;
	this.timesheet = timesheet;
    }

    public void writeToFolder(String destinationFolder) {
	Path file = getFilePath(destinationFolder);
	try {
	    Files.createFile(file);
	    FileWriter fileWriter = new FileWriter(
		    getFileName(destinationFolder));
	    fileWriter.write(getOutput());
	    fileWriter.close();
	} catch (IOException exception) {
	    throw new RuntimeException(exception);
	}

    }

    private String getOutput() {
	return resource.getOutput()
		+ timesheet.getOutput(resource.getSerialNumber());
    }

    private Path getFilePath(String destinationFolder) {
	return FileSystems.getDefault().getPath(getFileName(destinationFolder));
    }

    private String getFileName(String destinationFolder) {
	return destinationFolder.concat(format(FILENAME_TEMPLATE,
		resource.getLastName(), resource.getFirstName()));
    }
}
