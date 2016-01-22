package fr.pds.isintheair.crmtab.crv.mock;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Muthu on 02/01/2016.
 */
public class RandomInformationTest extends TestCase {
RandomInformation randomInformation;
    @Before
    public void setUp() throws Exception {
        randomInformation = new RandomInformation();
    }

    //ClassForTest if the random info generator returns a valid json mocked object
    @Test
    public void testGetRandomInfo() throws Exception {
        String mock = randomInformation.getRandomInfo();
        JsonParser parser = new JsonParser();
        JsonElement obj = parser.parse(mock);

        assertEquals(true, obj.isJsonObject());
    }

    //ClassForTest if the random number generator works properly
    @Test
    public void testRandInt() throws Exception {
        int random = RandomInformation.randInt(0,4) ;
        assertEquals(true, random<5);

    }
}