package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Visit;

/**
 * Created by Muthu on 06/02/2016.
 */
public class MockVisit {


    public MockVisit(){}


    public List<Visit> getMockVisit(){
        List<Visit> visits = new ArrayList<Visit>();

        Visit v1 = new Visit();
        Visit v2 = new Visit();
        Visit v3 = new Visit();
        Visit v4 = new Visit();
        Visit v5 = new Visit();
        Visit v6 = new Visit();
        Visit v7 = new Visit();

        v1.setId(1);
        v1.setDate("06/02/2016");
        v1.setIdContact(1);

        v2.setId(2);
        v2.setDate("02/01/2016");
        v2.setIdContact(1);

        v3.setId(3);
        v3.setDate("02/01/2016");
        v3.setIdContact(2);

        v4.setId(4);
        v4.setDate("06/02/2016");
        v4.setIdContact(3);

        v5.setId(5);
        v5.setDate("06/02/2016");
        v5.setIdContact(3);

        v6.setId(6);
        v6.setDate("06/02/2016");
        v6.setIdContact(4);

        v7.setId(7);
        v7.setDate("01/02/2016");
        v7.setIdContact(5);

        visits.add(v1);
        visits.add(v2);
        visits.add(v3);
        visits.add(v4);
        visits.add(v5);
        visits.add(v6);
        visits.add(v7);



        return visits;
    }


}
