package fr.pds.isintheair.jdatour.uc.phone.call.receive.entity;

public class Register {
    private Integer userId;

    public Register(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
