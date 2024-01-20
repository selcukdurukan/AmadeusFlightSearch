package com.amadeus.constants;

public class FlightsConstant {

    private FlightsConstant() {
    }

    public static final String VERSION = "/v1";
    public static final String DEV = "/dev";
    public static final String TEST = "/test";
    public static final String PRO = "/pro";

    public static final String FLIGHTS = VERSION + DEV + "/flights";

    public static final String CREATE = "/create";
    public static final String FETCH = "/fetch";
    public static final String FETCH_ALL = "/fetchall";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete/{id}";
    public static final String SEARCH_FLIGHT = "/searchflight";

    /**
     * Response DTO
     */
    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Flight created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";

    /**
     * OpenFeign
     */
    public static final String HAS_FOUND_BY_NAME = "/hasfoundbyname/{name}";
}
