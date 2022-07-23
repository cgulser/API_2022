package post_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponseBodyPojo;

import static io.restassured.RestAssured.*;

public class Post05 extends DummyRestApiBaseUrl {

     /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
       Test Case: Type by using Gherkin Language
       Assert:
                    {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                     }
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    /*
    Given
            https://dummy.restapiexample.com/api/v1/create
            {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        }
   When
        User sen the pot request

   Then
        status code is 200

   And
        {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }


     */

    @Test
    public void post01(){

        spec.pathParams("first","create");

        DummyApiDataPojo dummyApiDataPojo= new DummyApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyApiResponseBodyPojo dummyApiResponseBodyPojo= new DummyApiResponseBodyPojo("success",dummyApiDataPojo,"Successfully! Record has been added.");
        Response response =given().spec(spec).contentType(ContentType.JSON).body(dummyApiDataPojo).when().post("/{first}");
        response.prettyPrint();


    }

}