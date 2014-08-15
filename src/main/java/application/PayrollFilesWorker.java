package application;

import java.util.List;

import output.PayrollOutputFile;
import domain.hours.HoursFile;
import domain.hours.Timesheet;
import domain.hours.WorkingHours;
import domain.resources.PayrollResource;
import domain.resources.ResourcesFile;

public class PayrollFilesWorker implements PayrollWorker {

    private String directory = "src\\main\\resources\\";
    private ResourcesFile resourcesFile = new ResourcesFile(directory);
    private HoursFile hoursFile = new HoursFile(directory);

    @Override
    public void createPayroll() {
	List<PayrollResource> resources = resourcesFile.createResources();
	Timesheet timesheet = createTimesheet();
	for (PayrollResource resource : resources) {
	    if (resource.getEmployeeType().equals("Employee"))
		new PayrollOutputFile(resource, timesheet)
			.writeToFolder(directory);
	}
    }

    private Timesheet createTimesheet() {
	List<WorkingHours> workingHours = hoursFile.createWorkingHours();
	return new Timesheet(workingHours);
    }

    public void setDirectory(String directory) {
	this.directory = directory;
    }

    public void setResourceFile(ResourcesFile resourcesFile) {
	this.resourcesFile = resourcesFile;
    }

    public void setHoursFile(HoursFile hoursFile) {
	this.hoursFile = hoursFile;
    }

}
