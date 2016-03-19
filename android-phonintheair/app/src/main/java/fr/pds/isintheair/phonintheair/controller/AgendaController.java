package fr.pds.isintheair.phonintheair.controller;

import fr.pds.isintheair.phonintheair.model.entity.Message;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/19/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class AgendaController {
    public static void handleMessage(Message message) {
        switch (message.getMessageMeta().getMessageType()) {
            default:
        }
    }
}
