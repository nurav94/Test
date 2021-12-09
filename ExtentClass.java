package testing;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentClass {


	static ExtentSparkReporter spark;
	static ExtentTest testcase;

	public static ExtentReports extentreport;

	

	public static ExtentReports createInstance() {
		extentreport=new ExtentReports();
		
		String report =System.getProperty("user.dir");
		System.out.println(report +"report loc");
		spark= new ExtentSparkReporter(report +"/MCaaS.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("MCaaS");
		extentreport.attachReporter(spark);
		extentreport.setSystemInfo("Project", "MCaaS");
		extentreport.setSystemInfo("Operating System", "Windows 10");
		extentreport.setSystemInfo("Browser and Version", "Chrome 92");
		extentreport.setSystemInfo("Tester", "Varun");
		return extentreport;
	}

}
