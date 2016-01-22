package fr.pds.isintheair.crmtab.uc.phone.call.receive.controller.bus.event;

public class PhoneCallReceivedEvent {
    String phoneNumber;

    public PhoneCallReceivedEvent(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
