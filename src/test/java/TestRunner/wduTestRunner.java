package TestRunner;


import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/main/java/features"},
        tags= "@RegressionTest", monochrome=true,
        glue={"stepDefinition"})

public class wduTestRunner {

}