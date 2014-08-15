package application;

import java.util.List;

import output.PayrollFile;
import domain.resources.PayrollResource;
import domain.resources.ResourcesFile;

public class PayrollFilesWorker implements PayrollWorker {

    private String directory = "src\\main\\resources\\";
    private ResourcesFile resourcesFile = new ResourcesFile(directory);

    @Override
    public void createPayroll() {
	List<PayrollResource> resources = resourcesFile.createResources();
	for (PayrollResource resource : resources) {
	    if (resource.getEmployeeType().equals("Employee"))
		new PayrollFile(resource).writeToFolder(directory);
	}
    }

    public void setDirectory(String directory) {
	this.directory = directory;
    }

    public void setResourceFile(ResourcesFile resourcesFile) {
	this.resourcesFile = resourcesFile;
    }

}
