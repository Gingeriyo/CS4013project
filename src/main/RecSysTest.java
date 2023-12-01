package main;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecSysTest {

    // Tests to see if the 3rd part of a String array of all Student Module info is correct.
    @Test
    public void RecSysModuleNameTest() throws FileNotFoundException {
        RecSys test = new RecSys(22348069);
        assertEquals("Representation and Modelling", test.getAllModuleInfo()[2]);
    }
}
