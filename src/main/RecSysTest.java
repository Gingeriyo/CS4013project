package main;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecSysTest {

    @Test
    public void RecSysModuleNameTest() throws FileNotFoundException {
        RecSys test = new RecSys(22348069);
        assertEquals("Eric", test.getAllModuleInfo()[17]);
        assertEquals("CS4012", test.getAllModuleInfo()[0]);
    }
}
