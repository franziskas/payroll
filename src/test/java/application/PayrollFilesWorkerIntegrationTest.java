package application;

import static input.builder.LineItemsBuilder.FIRST_NAME;
import static input.builder.LineItemsBuilder.FIRST_NAME2;
import static input.builder.LineItemsBuilder.LAST_NAME;
import static input.builder.LineItemsBuilder.LAST_NAME2;
import static java.text.MessageFormat.format;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import input.InputFile;
import input.InputLines;
import input.LineItems;
import input.builder.LineItemsBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import domain.Resource;
import domain.ResourcesFile;

@RunWith(MockitoJUnitRunner.class)
public class PayrollFilesWorkerIntegrationTest {
    private static final byte[] IRRELEVANT_IMPUT = "2014 11 ".getBytes();
    private static final String EXPECTED_FILENAME = format("{0}-{1}.txt",
	    LAST_NAME, FIRST_NAME);
    private static final String EXPECTED_FILENAME2 = format("{0}-{1}.txt",
	    LAST_NAME2, FIRST_NAME2);
    private static final LineItems EXPECTED_FILE_CONTENT = new LineItemsBuilder(
	    " | ").create();
    private static final LineItems EXPECTED_FILE_CONTENT2 = new LineItemsBuilder()
	    .createSecondInstance(" | ");
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private static final Resource FIRST_RESOURCE = new Resource(
	    new LineItemsBuilder().create());
    private static final Resource SECOND_RESOURCE = new Resource(
	    new LineItemsBuilder().createSecondInstance());

    @Mock
    private InputFile workerMock;
    @Mock
    private ResourcesFile resourcesMock;

    @Test
    public void givenUserInputItReadsAResourceFileAndGeneratesPayrollFiles()
	    throws IOException {
	PayrollFilesWorker worker = new PayrollFilesWorker();
	File tempFolder = folder.newFolder();
	worker.setDirectory(tempFolder.getAbsolutePath() + "\\");
	worker.setResourceFile(resourcesMock);
	when(resourcesMock.createResources()).thenReturn(
		Arrays.asList(FIRST_RESOURCE, SECOND_RESOURCE));

	worker.createPayroll();

	System.setIn(new ByteArrayInputStream(IRRELEVANT_IMPUT));

	File[] files = tempFolder.listFiles();
	assertThat(files, arrayWithSize(2));

	assertThat(Arrays.asList(files[0].getName(), files[1].getName()),
		hasItems(EXPECTED_FILENAME, EXPECTED_FILENAME2));

	LineItems lines1 = new InputLines(new InputFile(
		files[0].getAbsolutePath())).getLines().get(0);
	LineItems lines2 = new InputLines(new InputFile(
		files[1].getAbsolutePath())).getLines().get(0);
	List<LineItems> fileList = Arrays.asList(lines1, lines2);

	assertThat(fileList,
		hasItems(EXPECTED_FILE_CONTENT, EXPECTED_FILE_CONTENT2));
    }
}
