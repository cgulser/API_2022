package post_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiActualBody;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05 extends DummyRestApiBaseUrl {
     /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body: {
                        "employee_name": "Ali Can",
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
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    @Test
    public void post01(){
        //1. Step: Set the Url
        spec.pathParam("first","create");

        //2. Step: Set the expected data
        DummyApiDataPojo dummyApiDataPojo = new DummyApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        DummyApiResponseBodyPojo expectedData = new DummyApiResponseBodyPojo("success", dummyApiDataPojo,"Successfully! Record has been added.");
        //3. Step: Send the Post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        DummyApiActualBody actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), DummyApiActualBody.class);
        System.out.println("actualDataPojo = " + actualDataPojo);
        assertEquals(expectedData.getStatus(),actualDataPojo.getStatus());
        assertEquals(expectedData.getMessage(),actualDataPojo.getData().getMessage());

        assertEquals(expectedData.getData().getEmployee_name(),actualDataPojo.getData().getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(),actualDataPojo.getData().getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(),actualDataPojo.getData().getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(),actualDataPojo.getData().getData().getProfile_image());

    }




}