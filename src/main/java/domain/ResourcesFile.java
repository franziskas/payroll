package domain;

import input.InputFile;
import input.InputLines;
import input.LineItems;

import java.util.ArrayList;
import java.util.List;

public class ResourcesFile {

    private static final String RESOURCE_FILENAME = "resources.txt";
    private InputLines inputLines;

    public ResourcesFile(String directory) {
	inputLines = new InputLines(
		new InputFile(directory + RESOURCE_FILENAME));
    }

    public List<Resource> createResources() {
	List<Resource> resources = new ArrayList<>();
	for (LineItems items : inputLines.getLines()) {
	    resources.add(new Resource(items));
	}
	return resources;
    }

}
