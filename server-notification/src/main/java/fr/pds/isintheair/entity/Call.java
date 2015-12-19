package fr.pds.isintheair.entity;

public class Call {
    private Long phoneNumber;

    public Call(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
