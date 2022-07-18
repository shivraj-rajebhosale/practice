import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.netty.handler.codec.json.JsonObjectDecoder;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Response {
  @Test
  public  void validate_post() {
	  
	  RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
	  RequestSpecification requestOBJ = RestAssured.given();
	  
	 org.json.simple.JSONObject requestPara=new org.json.simple.JSONObject();
	 requestPara.put("userId","TQ123");
	 requestPara.put("isbn","9781449325862");
	
	 
	 requestOBJ.header("content-Type","application/json");
	 requestOBJ.body(requestPara.toJSONString());
	 
	 
	 Response response = requestOBJ.request(Method.POST,"/BookStoreV1BooksPost");
	
	 String body = response.getBody().asString();
	 System.out.println(body);
	 
	 int responsecode = response.statusCode();
	 System.out.println("The statuscode received: "+responsecode);
	 Assert.assertEquals(responsecode,404,"TC failed statuscode didn't matched");
	 Reporter.log("TC Passed statuscode matched",true);
	 String responsecodeline = response.statusLine();
	 System.out.println("The status received: " + responsecodeline);
	Assert.assertEquals(responsecodeline,"HTTP/1.1 404 Not Found");
	Reporter.log("TC Passed statusline matched",true);
  }
}
