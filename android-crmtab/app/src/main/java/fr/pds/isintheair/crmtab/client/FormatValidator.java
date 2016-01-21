package fr.pds.isintheair.crmtab.client;

/**
 * Created by tlacouque on 29/12/2015.
 * Class used to validate format from views
 */
public class FormatValidator {

    /**
     * Check if siret pass by parameter is valid
     * @param siret
     * @return
     */
    public static boolean isSiretSyntaxValide(String siret){
        if(siret.length() != 14) {
            return false;
        }
        int total = 0;
        int digit = 0;

        for (int i = 0; i<siret.length(); i++) {
            /** Recherche les positions impaires : 1er, 3è, 5è, etc... que l'on multiplie par 2*/

            if ((i % 2) == 0) {
                digit = Integer.parseInt(String.valueOf(siret.charAt(i))) * 2;
                /** if result > 9 => compose of 2 digits, every digits should be added and cannot be
                 * >19 , so we need to do : digit-9 */
                if (digit > 9) digit -= 9;
            }
            else digit = Integer.parseInt(String.valueOf(siret.charAt(i)));
            total += digit;
        }
        /** If sum is a multiple of 10, siret number is valid */
        if ((total % 10) == 0) return true;
        else return false;
    }

    /**
     * Format the url to be used by openWebSite method
     * @param url
     * @return
     */
    public static String formatUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        return url;
    }
}
