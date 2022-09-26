package delete_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDeleteResponse;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {
       /*
        URL: https://dummy.restapiexample.com/api/v1/delete/2
       HTTP Request Method: DELETE Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "status" is "success"
              iii) "data" is "2"
               iv) "message" is "Successfully! Record has been deleted"
     */

    @Test
    public void delete01(){
        //1. Step: Set the Url
        spec.pathParams("first","delete","second",2);

        //2. Step: Set the expected data
        DummyApiDeleteResponse expectedData = new DummyApiDeleteResponse("success","2","Successfully! Record has been deleted");

        //3. Step: Send Delete Request and get the Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        DummyApiDeleteResponse actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiDeleteResponse.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getStatus(),actualDataPojo.getStatus());
        assertEquals(expectedData.getData(),actualDataPojo.getData());
        assertEquals(expectedData.getMessage(),actualDataPojo.getMessage());

    }
}
