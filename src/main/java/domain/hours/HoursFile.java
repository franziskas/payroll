package domain.hours;

import input.InputLines;
import input.LineItems;

import java.util.ArrayList;
import java.util.List;

import domain.resources.PayrollInputFile;

public class HoursFile extends PayrollInputFile {

    private static final String HOURS_FILENAME = "badges.txt";

    public HoursFile(String directory) {
	super(directory);
    }

    public List<WorkingHours> createWorkingHours() {
	InputLines inputLines = readFrom(HOURS_FILENAME);

	return readContents(inputLines);
    }

    private List<WorkingHours> readContents(InputLines inputLines) {
	ArrayList<WorkingHours> workingHours = new ArrayList<WorkingHours>();
	for (LineItems items : inputLines.getLines()) {
	    workingHours.add(new WorkingHours(items));
	}
	return workingHours;
    }

}
