package fr.pds.isintheair.crmtab;

/**
 * Create by jdatour
 * Modified by tlacouque on 06/03/2016
 * <p>
 * Class used to store url with static constant
 */
public class Constant {
    // url for rest server
    public static final String REST_URL = "http://192.168.20.3:8082";
    //url for mock client
    public static final String BASE_URL = "http://192.168.20.3:8082/api/";

    public static String WEBSOCKET_CALL_ENDPOINT     = "ws://192.168.20.3:8084/call";
    //public static String WEBSOCKET_CALL_ENDPOINT     = "ws://192.168.1.1:8084/call";
    public static String WEBSOCKET_CALENDAR_ENDPOINT = "ws://192.168.20.3:8084/calendar";
    //public static String WEBSOCKET_CALENDAR_ENDPOINT = "ws://192.168.1.1:8084/calendar";

}