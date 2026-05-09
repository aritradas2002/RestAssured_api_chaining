package Listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listener implements ITestListener {
    private static final Logger logger =
            LogManager.getLogger(Listener.class);

    @Override
    public void onStart(ITestContext context) {

        logger.info("===== TEST SUITE STARTED =====");
    }

    @Override
    public void onFinish(ITestContext context) {

        logger.info("===== TEST SUITE FINISHED =====");
    }

    @Override
    public void onTestStart(ITestResult result) {

        logger.info("TEST STARTED: "
                + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        logger.info("TEST PASSED: "
                + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.error("TEST FAILED: "
                + result.getName());

        logger.error("Failure Reason: "
                + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        logger.warn("TEST SKIPPED: "
                + result.getName());
    }
}
