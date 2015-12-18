package pds.isintheair.fr.crm_tab.isin.truong.model.bus;

import pds.isintheair.fr.crm_tab.isin.truong.model.entity.Prospect;

/**
 * Created by Truong on 12/14/2015.
 */
public class CreateClientEvent {
    private Prospect prospect;

    /**
     * Constructor
     * @param prospect
     */
    public CreateClientEvent(Prospect prospect) {
        this.prospect = prospect;
    }

    public Prospect getProspect() {
        return prospect;
    }

    public void setProspect(Prospect prospect) {
        this.prospect = prospect;
    }
}
