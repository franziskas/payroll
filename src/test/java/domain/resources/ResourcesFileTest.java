package domain.resources;

import static input.builder.TestFiles.TEST_FILES_FOLDER;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import input.builder.LineItemsForResourceBuilder;

import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ResourcesFileTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private static final PayrollResource SECOND_RESOURCE = new PayrollResource(
	    new LineItemsForResourceBuilder().withOtherValues().create());
    private static final PayrollResource FIRST_RESOURCE = new PayrollResource(
	    new LineItemsForResourceBuilder().create());

    @Test
    public void givenADirectoryNameWithEmptyResourceFileItReturnsEmptyResourceList()
	    throws IOException {
	folder.newFile("resources.txt");

	List<PayrollResource> resources = new ResourcesFile(getTempFolderPath())
		.createResources();

	assertThat(resources, is(empty()));
    }

    private String getTempFolderPath() {
	return folder.getRoot().getAbsolutePath() + "\\";
    }

    @Test
    public void givenADirectoryNameWithResourceFileHavingTwoLinesItReturnsResourcesReadFromFile() {
	List<PayrollResource> resources = new ResourcesFile(TEST_FILES_FOLDER)
		.createResources();

	assertThat(resources, hasItems(FIRST_RESOURCE, SECOND_RESOURCE));
    }
}
