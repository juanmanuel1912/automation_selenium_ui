package org.nopCommerce.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features/",
        glue = "org.nopCommerce.stepsDefinitions",
        plugin = {"pretty", "summary",
                "html:target/test-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        },
        monochrome = false,
        publish = true,
        dryRun = false
)


public class TestRunner {
}
