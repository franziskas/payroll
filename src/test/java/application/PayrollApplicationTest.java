package application;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PayrollApplicationTest {

    private static final String[] NO_ARGUMENTS = new String[0];

    @Mock
    private PayrollWorker workerMock;

    @Test
    public void givenAFileWithResourcesItCreatesPayrollFiles() {
	PayrollApplication.setPayrollWorker(workerMock);
	PayrollApplication.main(NO_ARGUMENTS);

	verify(workerMock).createPayroll();
    }

}
