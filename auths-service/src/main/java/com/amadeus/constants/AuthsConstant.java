package com.amadeus.constants;

public class AuthsConstant {

    public static final String VERSION = "/v1";
    public static final String DEV = "/dev";
    public static final String TEST = "/test";
    public static final String PRO = "/pro";

    public static final String AUTH = VERSION + DEV + "/auths";

    public static final String DO_LOGIN = "/dologin";
    public static final String REGISTER = "/register";


    /**
     * Response DTO
     */
    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Registered successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
}
