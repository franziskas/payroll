package application;

import static input.builder.LineItemsBuilder.FIRST_NAME;
import static input.builder.LineItemsBuilder.LAST_NAME;
import static input.builder.LineItemsBuilder.OTHER_FIRST_NAME;
import static input.builder.LineItemsBuilder.OTHER_LAST_NAME;
import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static output.PayrollOutputFile.FILENAME_TEMPLATE;
import input.InputFile;
import input.InputLines;
import input.LineItems;
import input.builder.LineItemsForOutputBuilder;
import input.builder.LineItemsForResourceBuilder;
import input.builder.LineItemsForWorkingHoursBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import domain.hours.HoursFile;
import domain.hours.WorkingHours;
import domain.resources.PayrollResource;
import domain.resources.ResourcesFile;

@RunWith(MockitoJUnitRunner.class)
public class PayrollFilesWorkerIntegrationTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private static final String EXPECTED_FILENAME = format(FILENAME_TEMPLATE,
	    LAST_NAME, FIRST_NAME);
    private static final String EXPECTED_FILENAME2 = format(FILENAME_TEMPLATE,
	    OTHER_LAST_NAME, OTHER_FIRST_NAME);

    private static final LineItems EXPECTED_FILE_CONTENT = new LineItemsForOutputBuilder()
	    .create();
    private static final LineItems EXPECTED_FILE_CONTENT2 = new LineItemsForOutputBuilder()
	    .withOtherEmployee().create();

    private static final PayrollResource FIRST_RESOURCE = new PayrollResource(
	    new LineItemsForResourceBuilder().create());
    private static final PayrollResource SECOND_RESOURCE = new PayrollResource(
	    new LineItemsForResourceBuilder().withOtherEmployee().create());
    private static final PayrollResource MANAGER_RESOURCE = new PayrollResource(
	    new LineItemsForResourceBuilder().withOtherValues().create());

    private static final WorkingHours WORKING_HOURS = new WorkingHours(
	    new LineItemsForWorkingHoursBuilder().create());
    private static final WorkingHours OTHER_WORKING_HOURS = new WorkingHours(
	    new LineItemsForWorkingHoursBuilder().withOtherValues().create());

    @Mock
    private InputFile workerMock;
    @Mock
    private ResourcesFile resourcesMock;
    @Mock
    private HoursFile hoursMock;

    private File tempFolder;

    @Before
    public void setUp() throws IOException {
	tempFolder = folder.newFolder();
    }

    @Test
    public void givenFolderWithEmptyResourceFileItGeneratesNoPayrollFiles() {
	PayrollFilesWorker worker = setUpWorker(tempFolder, emptyList());

	worker.createPayroll();

	assertThat(tempFolder.listFiles(), is(emptyArray()));
    }

    @Test
    public void givenFolderWithFileContainingTwoResourcesItGeneratesPayrollFiles() {
	PayrollFilesWorker worker = setUpWorker(tempFolder,
		asList(FIRST_RESOURCE, SECOND_RESOURCE, MANAGER_RESOURCE));

	worker.createPayroll();

	File[] files = tempFolder.listFiles();
	assertThat(files, arrayWithSize(2));

	List<String> fileNames = asList(files[0].getName(), files[1].getName());
	assertThat(fileNames, hasItems(EXPECTED_FILENAME, EXPECTED_FILENAME2));

	List<LineItems> fileContents = asList(getFirstLineOf(files[0]),
		getFirstLineOf(files[1]));
	assertThat(fileContents,
		hasItems(EXPECTED_FILE_CONTENT, EXPECTED_FILE_CONTENT2));
    }

    private LineItems getFirstLineOf(File file) {
	return new InputLines(new InputFile(file.getAbsolutePath())).getLines()
		.get(0);
    }

    private PayrollFilesWorker setUpWorker(File tempFolder,
	    List<PayrollResource> resources) {
	PayrollFilesWorker worker = new PayrollFilesWorker();
	worker.setDirectory(tempFolder.getAbsolutePath() + "\\");

	worker.setResourceFile(resourcesMock);
	when(resourcesMock.createResources()).thenReturn(resources);

	worker.setHoursFile(hoursMock);

	when(hoursMock.createWorkingHours()).thenReturn(
		asList(WORKING_HOURS, OTHER_WORKING_HOURS));

	return worker;
    }
}
