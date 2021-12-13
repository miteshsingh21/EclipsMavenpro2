package SeleniumEx;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestPassFailedstatus implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test case start withname:"+result.getName());
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case pass withname:"+result.getName());
	}
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test case Failed withname:"+result.getName());
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test case Skipped withname:"+result.getName());
	}
	
	
}
