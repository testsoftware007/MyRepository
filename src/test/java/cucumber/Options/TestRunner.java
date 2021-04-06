package cucumber.Options;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import io.cucumber.*;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/Features",
		plugin="json:target/jsonReports/cucumber-reports.json",
		glue = "StepDefenation"
	   // tags = "@AddPlace"
		
		//strict=true
		)    

public class TestRunner {
	
}