package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
      /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    @Test
    public void put01(){
        spec.pathParams("first","update", "second",21);
        DummyApiDataPojo dummyApiDataPojo = new DummyApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        DummyApiResponseBodyPojo expectedData = new DummyApiResponseBodyPojo("success", dummyApiDataPojo,"Successfully! Record has been updated.");
        Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyApiDataPojo).when().put("/{first}/{second}");
        response.prettyPrint();
        DummyApiResponseBodyPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiResponseBodyPojo.class);


        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());

        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());
    }

}