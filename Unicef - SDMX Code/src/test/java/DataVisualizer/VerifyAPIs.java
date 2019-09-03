package DataVisualizer;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.junit.annotations.UseTestDataFrom;

@UseTestDataFrom("testdata/DataVisualizerAPI.csv")
@RunWith(SerenityParameterizedRunner.class)
public class VerifyAPIs {

	private String URL;
	private String Accept;
	static int dataCnt = 1;

	@Before
	public void ReadingCSVData() {
		System.out.println("");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("START  --  Test Case ->  " + dataCnt);
	}

	@Test
	public void getAPIResponse() {

		RequestSpecification request = RestAssured.given();
		request.header("Accept", Accept);

		String responseStr = request.get(URL).asString();
		ArrayList<String> x= JsonPath.parse(responseStr).read("$.data.dataSets[0].observations[*][0]");
		String[] y=x.toString().replace("[", "").replace("]", "").trim().split("\\,");

		
System.out.println(y[0]);
		
	  
		  

	}

	@After
	public void doneWithReadingCSVData() {
		System.out.println("");
		System.out.println("END  --  Test Case ->  " + dataCnt);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		dataCnt++;
	}

	// GETTERS & SETTERS
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getAccept() {
		return Accept;
	}

	public void setAccept(String Accept) {
		this.Accept = Accept;
	}
	// GETTERS & SETTERS

}
