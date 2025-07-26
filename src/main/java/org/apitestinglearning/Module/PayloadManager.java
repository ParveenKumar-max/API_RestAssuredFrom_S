package org.apitestinglearning.Module;

import com.google.gson.Gson;
import org.apitestinglearning.POJO.*;
import com.github.javafaker.Faker;

public class PayloadManager {

    // Payload means, what we are sending in request because we have created a POJO file
    // with different(Request & Response) getter and setter.

    // Convert the Java Object into the JSON String to use as Payload.
    // Serialization

    Gson gson;  // Created a local variable
    Faker faker;


    public String CreatePayloadWith_ValidDataString() {

        Booking booking = new Booking();
        booking.setFirstname("Parveen");
        booking.setLastname("Chaudhary");
        booking.setTotalprice(2025);
        booking.setDepositpaid(true);

        //This booking date value comes under main string, so we have to assign it on Main string.
        Booking_Dates bookingdates = new Booking_Dates();
        bookingdates.setCheckin("2025-07-23");
        bookingdates.setCheckout("2025-07-30");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast & Lunch included");


        System.out.println(booking);
        //   return booking;   Return statement won't work because, we have to convert it into JSON

        // So we have two library GSON and Jackson API, we will use GSON library below.

        gson = new Gson();
        String jsonStringbooking = gson.toJson(booking);
        return jsonStringbooking;

        // So in above code, we have converted our java request in json

    }

    public String CreatePayloadWith_ValidMissingDataString(){
        Booking booking = new Booking();
        booking.setFirstname("");
        System.out.println(booking);

        gson = new Gson();
        String jsonStringbooking1  = gson.toJson(booking);
        return jsonStringbooking1;
    }

    // So once you Set the Data and Convert Java function to JSON via GSON,
    // then you have to convert it to vice versa
    // Convert the JSON String to Java Object so that we can verify response Body
    // DeSerialization

    public Booking_Response bookingResponseJava(String responseString){
        gson = new Gson();
        Booking_Response bookingResponse = gson.fromJson(responseString, Booking_Response.class);
        return bookingResponse;
    }

    // For Token Creation
    // For every payload, we have to define Response.

    // Java Object -> JSON
    public String CreatePayload_setValidToken(){

        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        // Convert Java to Json

        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the " + " " + jsonPayloadString);
        return jsonPayloadString;

    }

    // DeSer ( JSON String -> Java Object
    public String getTokenFromJson (String tokenResponse){
        gson = new Gson();
        Token_Response  TokenResponse01 = gson.fromJson(tokenResponse, Token_Response.class);
        return TokenResponse01.getToken();

    }


    public String createPayloadBookingFakerJS(){
        faker  = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1,1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        Booking_Dates bookingdates = new Booking_Dates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;

    }

    public Booking getResponseFromJSON(String getResponse){
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }

    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Booking_Dates bookingdates = new Booking_Dates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);


    }

    public String partialUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Dutta");
        return gson.toJson(booking);

    }

}
