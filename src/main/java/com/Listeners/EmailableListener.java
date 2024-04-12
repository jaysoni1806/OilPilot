package com.Listeners;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class EmailableListener implements ITestListener {

	int passCount = 0;
	int failCount = 0;
	int skipCount = 0;
	String reportHtml = "";

	public int getTotalCount() {
		return passCount + failCount + skipCount;
	}

	@Override
	public void onStart(ITestContext context) {

		System.out.println("On start called");

		reportHtml = "<html>\n"
				+ "<h2 style=\"color: #5e9ca0;\"><span style=\"color: #0B977C;\">Execution report:</span></h2>\n"
				+ "<table border=\"1\">\n" + "<thead>\n" + "<tr style=\"background-color:#0B977C\">\n"
				+ "<td style=\"width: 40%;\"><strong>Test case name</strong></td>\n"
				+ "<td style=\"width: 40%;\"><strong>Status</strong></td>\n"
				+ "<td style=\"width: 10%;\"><strong>Msg</strong></td>\n" + "</tr>\n" + "</thead>\n" + "<tbody>";

	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " : passed");

		passCount++;
		System.setProperty("passed", String.valueOf(passCount));
		reportHtml = reportHtml + "<tr>\n" + "<td style=\"width: 40%;\">" + result.getMethod().getMethodName()
				+ "</td>\n" + "<td style=\"width: 40%;\">Pass</td>\n" + "<td style=\"width: 10%;\">&nbsp;</td>\n"
				+ "</tr>";

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " : failed");
		failCount++;
		System.setProperty("failed", String.valueOf(failCount));
		reportHtml = reportHtml + "<tr><td style=\"width: 40%;\"><span style=\"color: #ff0000;\">"
				+ result.getMethod().getMethodName() + "</span></td>\n"
				+ "<td style=\"width: 40%;\"><span style=\"color: #ff0000;\">Failed</span></td>\n"
				+ "<td style=\"width: 10%;\"><span style=\"color: #ff0000;\">" + result.getThrowable().getMessage()
				+ "</span></td></tr>";

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		skipCount++;
		System.setProperty("skipped", String.valueOf(skipCount));
		reportHtml = reportHtml + "<tr><td style=\"width: 40%;\"><span style=\"color: #ff0000;\">"
				+ result.getMethod().getMethodName() + "</span></td>\n"
				+ "<td style=\"width: 40%;\"><span style=\"color: #ff0000;\">Skipped</span></td>\n"
				+ "<td style=\"width: 10%;\"><span style=\"color: #ff0000;\">" + "" + "</span></td></tr>";
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onFinish(ITestContext context) {
		System.setProperty("total", String.valueOf(getTotalCount()));
		reportHtml = reportHtml + "<tr><td style=\"width: 40%;\" colspan=\"3\"><span style=\"color: #ff0000;\">"
				+ "<p style=\"color: #0B977C;\"><strong>Summary:</strong></p>\n" + "<ul>\n" + "<li>Total Test Cases: "
				+ getTotalCount() + "</li>\n" + "<li>Total Passed: " + passCount + "</li>\n" + "<li>Total Failed: "
				+ failCount + "</li>\n" + "<li>Total Skipped: " + skipCount + "</li>\n" + "</ul></td>\n" + "</html>";

		/*
		 * reportHtml = "<html>\n" +
		 * "<p style=\"color: #0B977C;\"><strong>Summary:</strong></p>\n" + "<ul>\n" +
		 * "<li>Total Test Cases: " + getTotalCount() + "</li>\n" + "<li>Total Passed: "
		 * + passCount + "</li>\n" + "<li>Total Failed: " + failCount + "</li>\n" +
		 * "<li>Total Skipped: " + skipCount + "</li>\n" + "</ul>\n" + "</html>";
		 */

		/*
		 * reportHtml = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" +
		 * "<meta charset=\"UTF-8\">\n" +
		 * "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
		 * + "<title>Test Report</title>\n" + "<style>\n" + "  body {\n" +
		 * "    font-family: Arial, sans-serif;\n" + "    line-height: 1.6;\n" +
		 * "    margin: 0;\n" + "    padding: 0;\n" + "  }\n" + "  .container {\n" +
		 * "    max-width: 600px;\n" + "    margin: 20px auto;\n" +
		 * "    padding: 20px;\n" + "    border: 1px solid #ccc;\n" +
		 * "    border-radius: 5px;\n" + "  }\n" + "  h1 {\n" + "    color: #333;\n" +
		 * "  }\n" + "  table {\n" + "    width: 100%;\n" +
		 * "    border-collapse: collapse;\n" + "  }\n" + "  th, td {\n" +
		 * "    padding: 10px;\n" + "    border-bottom: 1px solid #ddd;\n" +
		 * "    text-align: left;\n" + "  }\n" + "  th {\n" +
		 * "    background-color: #f2f2f2;\n" + "  }\n" + "</style>\n" + "</head>\n" +
		 * "<body>\n" + "\n" + "<div class=\"container\">\n" +
		 * "  <h1>Test Report</h1>\n" + "  \n" + "  <table>\n" + "    <tr>\n" +
		 * "      <th>Total Test Cases: </th>\n" + "      <th>" + getTotalCount() +
		 * "</th>\n" + "    </tr>\n" + "    <tr>\n" + "    <tr>\n" +
		 * "      <th>Test Case</th>\n" + "      <th>Status</th>\n" + "    </tr>\n" +
		 * "    <tr>\n" + "      <td>Total Passed:</td>\n" + "      <td>" + passCount +
		 * "</td>\n" + "    </tr>\n" + "    <tr>\n" + "      <td>Total Failed:</td>\n" +
		 * "      <td>" + failCount + "</td>\n" + "    </tr>\n" + "    <tr>\n" +
		 * "      <td>Total Skipped:</td>\n" + "      <td>" + skipCount + "</td>\n" +
		 * "    </tr>\n" + "  </table>\n" + "</div>\n" + "\n" + "</body>\n" + "</html>";
		 */

		try (PrintWriter out = new PrintWriter("Report/Automated-Test-Report.html")) {
			out.println(reportHtml);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * ProcessBuilder pb = new ProcessBuilder("/bin/sh"); Map<String, String> envMap
		 * = pb.environment(); Set<String> keys = envMap.keySet(); for (String key :
		 * keys) { System.out.println(key + " ==> " + envMap.get(key)); }
		 */

	}
}
