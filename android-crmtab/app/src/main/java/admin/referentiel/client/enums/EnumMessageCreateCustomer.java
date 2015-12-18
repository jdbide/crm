package admin.referentiel.client.enums;

/**
 * Created by tlacouque on 13/12/2015.
 */
public enum EnumMessageCreateCustomer {
    ERROR_NAME("Champ Nom vide"),
    ERROR_SIRET("Champ Siret malformé ou vide"),
    ERROR_FINESS("Champ Finess malformé ou vide"),
    ERROR_STREET_NUMBER("Champ numéro de rue vide"),
    ERROR_STREET_NAME("Champ nom de rue vide"),
    ERROR_TOWN("Champ ville vide"),
    ERROR_ZIP_CODE("Champ code postal vide ou non complet"),
    ERROR_OFFLINE_MAP("La carte représentant la position du client n’a pas été pré-téléchargé" +
            " Il le sera à votre prochaine connexion au service en ligne"),
    MESSAGE_CREATE_CUSTOMER("Client créé");




    private String stringValue;

    private EnumMessageCreateCustomer(String toString) {
        stringValue = toString;
    }

    @Override
    public String toString() {
        return stringValue;
    }

}
