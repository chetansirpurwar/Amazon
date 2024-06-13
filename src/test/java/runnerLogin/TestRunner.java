package runnerLogin;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    dryRun = false,
    features = "src/test/resources/features",
    monochrome = true,
    glue = {"glueCode", "hooks"},
    tags = "@SmokeTest",
    plugin = {
        "pretty",
        "html:target/cucumber-reports",
        "rerun:target/rerun.txt"  // Plugin to collect failed test scenarios
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {
	

}
