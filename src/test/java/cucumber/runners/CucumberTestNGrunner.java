package cucumber.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
		plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"}
		,features = {"resources/features"}
		,glue = {"cucumber.stepDefinitions"}
		,tags = {"@currentProjectTest"}
)

public class CucumberTestNGrunner {
	
	private TestNGCucumberRunner cucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setRunner() {
		cucumberRunner = new TestNGCucumberRunner(this.getClass());
	}
	
	@AfterClass(alwaysRun = true)
	public void finishTestRunner() {
		cucumberRunner.finish();
	}

	@DataProvider(name = "provideScenarios")
	public Object[][] scenarios(){
		return cucumberRunner.provideScenarios();
	}
	
	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "provideScenarios")
	public void runCucumberTest(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucFeature) throws Throwable {
		cucumberRunner.runScenario(pickleEvent.getPickleEvent());
	}
}