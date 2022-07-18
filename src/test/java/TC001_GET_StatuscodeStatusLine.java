import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_StatuscodeStatusLine {
  @Test
  public void validate() {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		RequestSpecification RequestOBJ = RestAssured.given();
	    Response response = RequestOBJ.request(Method.GET,"/employees");
	    String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		int statuscode = response.statusCode();
		System.out.println("Response code is"+statuscode);
		Assert.assertEquals(statuscode,200,"TC failed Statuscode didn't mathced ");
		Reporter.log("TC passed Matched ",true);
		
		String statusline = response.statusLine();
		Assert.assertEquals(statusline,"HTTP/1.1 200 OK","TC failed Statuscode didn't mathced ");
		Reporter.log("TC passed Matched ",true);
		
		String header = response.header("Cache-Control");
		System.out.println("header is "+header);
		Assert.assertEquals(header,"no-cache, private, max-age=21600","TC failed header value didn't matched");
		Reporter.log("TC passed header value matched",true);
		
		Headers allheaders = response.getHeaders();
		System.out.println("All headers are "+allheaders);
		
		
  }
}
