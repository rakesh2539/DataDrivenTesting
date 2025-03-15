package utils;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentReports report;
	public ExtentSparkReporter sparkreport;
	public ExtentTest extentTest;

	String reportName;

	@Override
	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		reportName = "Logifrieght" + timeStamp + ".html";

		sparkreport = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + reportName);

		sparkreport.config().setDocumentTitle("Logifriegt Automation Report");
		sparkreport.config().setReportName("Logifrieght Functional Testing");
		sparkreport.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(sparkreport);
		report.setSystemInfo("Application", "Logifireght");
		report.setSystemInfo("Module", "consumer");
		report.setSystemInfo("SubModule", "Outbond");
		report.setSystemInfo("userNamae:", System.getProperty("user.name"));
		report.setSystemInfo("Envirornment:", "QA");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest = report.createTest(result.getMethod().getMethodName());
		extentTest.log(Status.PASS, result.getMethod().getMethodName() + " Test got successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Use the method name instead of class name
		extentTest = report.createTest(result.getMethod().getMethodName());
		extentTest.log(Status.FAIL, result.getMethod().getMethodName() + " Test got Failed..");
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgpath = new BaseClass().captureScreenShot(result.getMethod().getMethodName());
			extentTest.addScreenCaptureFromPath(imgpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Use the method name instead of class name
		extentTest = report.createTest(result.getMethod().getMethodName());
		extentTest.log(Status.SKIP, result.getMethod().getMethodName() + " Test got Skipped..");
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
	}

	@Override
	public void onFinish(ITestContext context) {

		report.flush();

		String pathofExtentReport = System.getProperty("user.dir") + "\\reports\\" + reportName;

		File extentreport = new File(pathofExtentReport);

		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
