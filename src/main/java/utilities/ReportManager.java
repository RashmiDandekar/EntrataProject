package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportManager {
	private static ExtentTest test;
	private static ExtentReports report;
	private static String ScreenshotFolder;
	private static String ReportFolder;
	private static String ResultDest;
	public static SoftAssertions softly;

	/**
	 * setReportName - Sets report name and create folder
	 * 
	 * @param iReportName
	 */
	public static void setReportName(String iReportName) {
		ScreenshotFolder = iReportName + "_" + System.currentTimeMillis();
		ReportFolder = iReportName + "_" + System.currentTimeMillis();
		File Screenshotfolder = new File(System.getProperty("user.dir") + "\\Screenshots\\" + ScreenshotFolder);
		Screenshotfolder.mkdir();
		File Reportfolder = new File(System.getProperty("user.dir") + "\\Reports\\" + ReportFolder);
		Reportfolder.mkdir();
		report = new ExtentReports(System.getProperty("user.dir") + "\\Reports\\" + ReportFolder
				+ "\\ExtentReportResults_" + System.currentTimeMillis() + ".html", true);
	}

	/**
	 * initializeReport - Initialize Extent Report, sets up GLOBAL_REPORT folder for
	 * SQUASH Execution or GLOBAL_REPORT_CurrentDateTime folder for Local Execution.
	 * Creates index.html result file under Global Report folder.
	 * 
	 */
	private static void initializeReport() {
		String TestResultFolderName = "GLOBAL_REPORT";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS");
		LocalDateTime now = LocalDateTime.now();
		String currDateTime = dtf.format(now);
		currDateTime = currDateTime.replace(":", "-");
		currDateTime = currDateTime.replace("/", "-");

		try {
			TestResultFolderName = "GLOBAL_REPORT" + "_" + currDateTime;
		} catch (Exception e) {
			System.out.println("GLOBAL Report NOT initialized");
		}

		File ResultFolder = new File("Results\\" + TestResultFolderName);
		ResultFolder.mkdirs();
		ResultDest = ResultFolder.toString();

		try {
			String workflowID = System.getenv("_CurrentWorkflowId");
			report = new ExtentReports(ResultDest + "\\index.html", false);
		} catch (NullPointerException e) {
			report = new ExtentReports(ResultDest + "\\index.html", true);
		}
	}

	/**
	 * startTest - Start the test using the ExtentTest class object.
	 * 
	 * @param testTitle
	 * @param testDescription
	 * @throws Exception
	 */
	public static void startTest(String testTitle, String testDescription) throws Exception {
		System.out.println("STARTED : " + testTitle);
		softly = new SoftAssertions();
		if (report == null) {
			// setReportName(testTitle);
			initializeReport();
		}
		test = report.startTest(testTitle, testDescription);
	}

	/**
	 * endTest - close report and write everything to document
	 */
	public static void endTest() {
		System.out.println("--------------Test END----------------");
		report.endTest(test);
		report.flush();
		test = null;
		softly.assertAll();
	}

	/**
	 * logPass - Create pass logs for action performed during execution
	 * 
	 * @param logMsg -
	 * @throws IOException
	 */
	public static void logPass(String logMsg) throws IOException {
		System.out.println(" PASS : " + logMsg);
		test.log(LogStatus.PASS, logMsg);
	}

	/**
	 * logFail - Create fail logs for action performed during execution
	 * 
	 * @param logMsg - message from calling method
	 * @throws Exception
	 */
	public static void logFail(String logMsg) throws Exception {
		System.out.println(" FAIL : " + logMsg);
		test.log(LogStatus.FAIL, logMsg);
		if (BasePage.driver != null)
			logScreenshot(logMsg, logMsg);
		softly.assertThat(false).isTrue();
	}

	/**
	 * logInfo - Create info logs for action performed during execution
	 * 
	 * @param msg - message from calling method
	 * @throws Exception
	 */
	public static void logInfo(String msg) throws Exception {
		System.out.println(" INFO : " + msg);
		test.log(LogStatus.INFO, msg);
	}

	/**
	 * logScreenshot - Works based on input provided from calling method
	 * 
	 * @param logMsg - depend on string value, screenshots save with message
	 * @throws Exception
	 */
	public static void logScreenshot(String ScreenshotName, String logMsg) throws Exception {
		String ScreenshotFilepath = null;
		try {
			TakesScreenshot ts = ((TakesScreenshot) BasePage.driver);
			File scrFile = ts.getScreenshotAs(OutputType.FILE);
			File Dest = new File(ResultDest + "\\" + ScreenshotName + "_" + System.currentTimeMillis() + ".png");
			ScreenshotFilepath = Dest.getAbsolutePath();
			ScreenshotFilepath = ".." + ScreenshotFilepath.split("Results")[1];
			FileUtils.copyFile(scrFile, Dest);
			test.log(LogStatus.INFO, test.addScreenCapture(ScreenshotFilepath));
		} catch (Exception e) {
			e.printStackTrace();
			logFail("Issue in capture method ");
		}
	}
}