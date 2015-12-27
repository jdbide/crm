package miage.pds.prospect;

import miage.pds.prospect.controller.ProspectController;

/**
 * Created by Truong on 12/24/2015.
 */
public class Main {

    public static void main(String[] args) {
        ProspectController prospectController = new ProspectController();
        prospectController.getSalesByProspect();

    }
}
