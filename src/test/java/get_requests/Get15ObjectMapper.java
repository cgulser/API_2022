package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {

    /*
        Given
	            https://restful-booker.herokuapp.com/booking/55
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
              {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-08-09",
                    "checkout": "2022-08-27"
                },
                "additionalneeds": "Breakfast"
              }
     */
    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first","booking","second",55);

        //2. Step: Set the expected data
        String expectedData =" {\n" +
                "\"firstname\": \"Jim\",\n" +
                "\"lastname\": \"Brown\",\n" +
                " \"totalprice\": 111,\n" +
                " \"depositpaid\": true,\n" +
                " \"bookingdates\": {\n" +
                "\"checkin\": \"2022-08-09\",\n" +
                "\"checkout\": \"2022-08-27\"\n" +
                " },\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";


        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //3. Step: Send the request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

        //Hard Assertion
//        assertEquals(200,response.getStatusCode());
//        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
//        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
//        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
//        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());
//        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());

        //Soft Assertion
        //1) Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();
        //2) Do Assertion
        softAssert.assertEquals(actualDataPojo.getFirstname(),expectedDataPojo.getFirstname(),"Firstname did not match");
        softAssert.assertEquals(actualDataPojo.getLastname(),expectedDataPojo.getLastname(),"Lastname did not match");
        softAssert.assertEquals(actualDataPojo.getTotalprice(),expectedDataPojo.getTotalprice(),"Total price did not match");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(),expectedDataPojo.getDepositpaid(),"Deposit paid did not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(),expectedDataPojo.getBookingdates().getCheckin(),"Checkin did not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(),expectedDataPojo.getBookingdates().getCheckout(),"Checkout not match");
        softAssert.assertEquals(actualDataPojo.getAdditionalneeds(),expectedDataPojo.getAdditionalneeds(),"Additional needs did not match");



        //3) Use assertAll()
        softAssert.assertAll();



    }


}
