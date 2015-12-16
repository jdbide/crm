package badge.system.pds.crmbadge;

/**
 * Created by Muthu on 07/11/2015.
 */
public interface Config {
    static final String API_URL = "http://192.168.20.3:8082/api/" + "doBadge/";
    static final String API_URL_CHANGE_STATUS = "http://192.168.20.3:8082/api/" + "doChangeStatus/";
    static final String SOCKET_SERVER_IP = "192.168.20.3:8082/notification/server/notif";
}
