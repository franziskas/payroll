package output;

import static input.builder.LineItemsBuilder.FIRST_NAME;
import static input.builder.LineItemsBuilder.LAST_NAME;
import static input.builder.LineItemsBuilder.SERIAL_NUMBER;
import static input.builder.LineItemsForOutputBuilder.STANDARD_OUTPUT;
import static java.text.MessageFormat.format;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import input.InputFile;
import input.InputLines;
import input.LineItems;
import input.builder.LineItemsForOutputBuilder;
import input.builder.LineItemsForResourceBuilder;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import domain.hours.Timesheet;
import domain.resources.PayrollResource;

@RunWith(MockitoJUnitRunner.class)
public class PayrollOutputFileIntegrationTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private static final LineItems LINE_ITEMS_WITH_VALUES = new LineItemsForResourceBuilder()
	    .create();
    private static final String EXPECTED_FILENAME = format("{0}-{1}.txt",
	    LAST_NAME, FIRST_NAME);
    private static final LineItems EXPECTED_FILE_CONTENT = new LineItemsForOutputBuilder()
	    .create();

    @Mock
    private Timesheet timesheetMock;

    @Test
    public void givenResourceAndHoursOutputIsAreWrittenToFile()
	    throws IOException {
	PayrollOutputFile payrollFile = setUpPayrollOutputFile();
	File destinationFolder = folder.newFolder();

	payrollFile.writeToFolder(getDestination(destinationFolder));

	assertThat(getOutput(destinationFolder).getLines(), hasItem(EXPECTED_FILE_CONTENT));

    }

    private InputLines getOutput(File destinationFolder) {
	String desiredFilepath = getDestination(destinationFolder)
		+ EXPECTED_FILENAME;
	InputLines lines = new InputLines(new InputFile(desiredFilepath));
	return lines;
    }

    private PayrollOutputFile setUpPayrollOutputFile() {
	PayrollResource resource = new PayrollResource(LINE_ITEMS_WITH_VALUES);
	PayrollOutputFile payrollFile = new PayrollOutputFile(resource,
		timesheetMock);
	when(timesheetMock.getOutput(SERIAL_NUMBER))
		.thenReturn(STANDARD_OUTPUT);
	return payrollFile;
    }

    private String getDestination(File destinationFolder) {
	return destinationFolder.getPath() + "\\";
    }

}
