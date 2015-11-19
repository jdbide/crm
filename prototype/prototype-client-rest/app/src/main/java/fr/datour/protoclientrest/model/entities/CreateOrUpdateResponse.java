package fr.datour.protoclientrest.model.entities;

public class CreateOrUpdateResponse {
    private Boolean status;

    public CreateOrUpdateResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
