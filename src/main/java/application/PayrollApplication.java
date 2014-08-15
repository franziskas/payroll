package application;

public class PayrollApplication {

    private static PayrollWorker worker = new PayrollFilesWorker();

    public static void main(String[] args) {
	worker.createPayroll();
    }

    public static void setPayrollWorker(PayrollWorker worker) {
	PayrollApplication.worker = worker;
    }

}
