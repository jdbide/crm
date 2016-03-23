package fr.pds.isintheair.notifier.entity;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/19/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class SessionInfo {
    private Integer          userId;
    private DeviceType       deviceType;
    private NotificationType notificationType;

    public Integer getUserId () {
        return userId;
    }

    public DeviceType getDeviceType () {
        return deviceType;
    }

    public NotificationType getNotificationType () {
        return notificationType;
    }
}
