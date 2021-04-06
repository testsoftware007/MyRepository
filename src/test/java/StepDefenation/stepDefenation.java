package StepDefenation;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Resource.TestDataBuilder;
import Resource.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefenation extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	static String place_id;
	//JsonPath js;
	TestDataBuilder bu = new TestDataBuilder();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Kumar");
	   
	   res = given().spec(requestSpecification())
			 .body(bu.payload(name, language, address));
	}

	@When("User calls {string} with {string} http method")
	public void user_calls_with_http_method(String resource, String method) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		resspec =	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("post"))
		  response = res.when().post(getGlobalValue(resource)).then().spec(resspec).extract().response();
		else if (method.equalsIgnoreCase("get")) 
		  response = res.when().get(getGlobalValue(resource)).then().spec(resspec).extract().response();	  
		
	}

	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(int status_code) {
	    // Write code here that turns the phrase above into concrete actions
	    //response.getStatusCode();
	    assertEquals(response.getStatusCode(), status_code);
	   // String k = response.getStatusCode();
	   // assertArrayEquals(Integer.parseInt(), status_code);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expected) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(getJasonPath(response, keyvalue), expected);
	}
	
	    
	    @Then("verify place_id created maps to {string} using {string}")
	    public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException 
	    {
	       place_id =getJasonPath(response, "place_id");
	       //System.out.println(resource);
	       res = given().spec(requestSpecification()).queryParam("place_id", place_id);
	        user_calls_with_http_method(resource, "get");
	       String name = getJasonPath(response, "name");
	       assertEquals(name, expectedname);
	       
	    }
	    
	    @Given("DeletePlace Payload")
	    public void deleteplace_Payload() throws IOException {
	        // Write code here that turns the phrase above into concrete actions
	     res= given().spec(requestSpecification()).body(bu.deletePlacePayload(place_id));
	     System.out.println("DeletePlace");
	     
	     
	    }

}
