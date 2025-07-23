package org.apitestinglearning.Module;

import com.google.gson.Gson;
import org.apitestinglearning.POJO.Booking;
import org.apitestinglearning.POJO.Booking_Dates;
import org.apitestinglearning.POJO.Booking_Response;

public class PayloadManager {

    // Payload means, what we are sending in request because we have created a POJO file
    // with different(Request & Response) getter and setter.

    // Convert the Java Object into the JSON String to use as Payload.
    // Serialization

    Gson gson;  // Created a local variable


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

    // So once you Set the Data and Convert Java function to JSON via GSON,
    // then you have to convert it to vice versa
    // Convert the JSON String to Java Object so that we can verify response Body
    // DeSerialization

    public Booking_Response bookingResponseJava(String responseString){
        gson = new Gson();
        Booking_Response bookingResponse = gson.fromJson(responseString, Booking_Response.class);
        return bookingResponse;
    }



}
