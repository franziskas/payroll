package domain.resources;

import input.InputLines;
import input.LineItems;

import java.util.ArrayList;
import java.util.List;

public class ResourcesFile extends PayrollInputFile {
    private static final String RESOURCE_FILENAME = "resources.txt";

    public ResourcesFile(String directory) {
	super(directory);
    }

    public List<PayrollResource> createResources() {
	InputLines inputLines = readFrom(RESOURCE_FILENAME);

	return readContents(inputLines);
    }

    private List<PayrollResource> readContents(InputLines inputLines) {
	List<PayrollResource> resources = new ArrayList<>();
	for (LineItems items : inputLines.getLines()) {
	    resources.add(new PayrollResource(items));
	}
	return resources;
    }

}
