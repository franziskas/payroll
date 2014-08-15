package output;

import static java.text.MessageFormat.format;
import input.LineItems;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import domain.Resource;

public class PayrollFile {

    private Resource resource;

    public PayrollFile(Resource resource) {
	this.resource = resource;
    }

    public void writeToFolder(String destinationFolder) {
	Path file = getFilePath(destinationFolder);
	try {
	    Files.createFile(file);
	    FileWriter fileWriter = new FileWriter(
		    getFileName(destinationFolder));
	    fileWriter.write(getContents());
	    fileWriter.close();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}

    }

    private String getContents() {
	return new LineItems(resource).getOutput();
    }

    private Path getFilePath(String destinationFolder) {
	return FileSystems.getDefault().getPath(getFileName(destinationFolder));
    }

    private String getFileName(String destinationFolder) {
	return destinationFolder.concat(format("{0}-{1}.txt",
		resource.getLastName(), resource.getFirstName()));
    }
}
