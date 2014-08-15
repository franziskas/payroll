package domain.hours;

import static input.builder.TestFiles.TEST_FILES_FOLDER;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import input.builder.LineItemsForWorkingHoursBuilder;

import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class HoursFileIntegrationTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private static final WorkingHours FIRST_HOURS = new WorkingHours(
	    new LineItemsForWorkingHoursBuilder().create());
    private static final WorkingHours SECOND_HOURS = new WorkingHours(
	    new LineItemsForWorkingHoursBuilder().withOtherValues().create());

    @Test
    public void givenADirectoryNameWithEmptyFileItReturnsEmptyList()
	    throws IOException {
	folder.newFile("badges.txt");

	List<WorkingHours> resources = new HoursFile(getTempFolderPath())
		.createWorkingHours();

	assertThat(resources, is(empty()));
    }

    private String getTempFolderPath() {
	return folder.getRoot().getAbsolutePath() + "\\";
    }

    @Test
    public void givenADirectoryNameWithFileHavingTwoLinesItReturnsHoursReadFromFile() {
	List<WorkingHours> resources = new HoursFile(TEST_FILES_FOLDER)
		.createWorkingHours();

	assertThat(resources, hasItems(FIRST_HOURS, SECOND_HOURS));
    }
}
