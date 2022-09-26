package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
      /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */


    @Test
    public void delete01(){
        //1. Step: Set the Url
        spec.pathParams("first","todos","second",198);

        //2. Step: Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        System.out.println("expectedData: "+expectedData);

        //3. Step: Send Delete Request and get the Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        //1. Way:
        Map<String, Object> actualData =  response.as(HashMap.class);
        System.out.println("actualData: "+actualData);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedData, actualData);

        //2. Way:
        response.then().assertThat().statusCode(200);
        assertTrue(actualData.size()==0);
        assertTrue(actualData.isEmpty());


        /*
        Interview Question:
        How to automate Delete Request in API Testing?
        i) Create new data by using "Post Request"
        ii) Use "Delete Request" to delete new data.

        Note: Do not use "Delete Request" for the existing data, create your own data, then delete it.

         */

    }
}
