package pds.isintheair.fr.crm_tab.isin.truong.model.entity;

/**
 * Created by Truong on 12/14/2015.
 */
public class CreateResponse {
    private boolean isResponse;

    /**
     * Constructor
     * @param isResponse
     */
    public CreateResponse(boolean isResponse) {
        this.isResponse = isResponse;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setIsResponse(boolean isResponse) {
        this.isResponse = isResponse;
    }
}
