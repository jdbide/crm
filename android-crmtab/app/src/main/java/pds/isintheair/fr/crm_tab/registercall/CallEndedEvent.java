package pds.isintheair.fr.crm_tab.registercall;

/**
 * Created by j-d on 20/12/2015.
 */
public class CallEndedEvent {

    private int idcontact;
    private int idclient;
    private int number;

    public int getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(int idcontact) {
        this.idcontact = idcontact;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CallEndedEvent(int idcontact, int idclient, int number) {

        this.idcontact = idcontact;
        this.idclient = idclient;
        this.number = number;
    }

    public CallEndedEvent() {


    }
}
