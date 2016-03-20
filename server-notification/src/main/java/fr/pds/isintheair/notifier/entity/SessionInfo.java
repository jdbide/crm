package fr.pds.isintheair.notifier.entity;

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
