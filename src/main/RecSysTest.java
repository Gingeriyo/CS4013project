package main;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecSysTest {

    @Test
    public void RecSysModuleNameTest() throws FileNotFoundException {
        RecSys test = new RecSys(22348069);
        assertEquals("CS4012", test.getAllModuleInfo()[0]);
    }

    @Test
    public void RecSysTranscriptSingleSem() throws FileNotFoundException {
        RecSys test = new RecSys(22348069);
        assertEquals("CS4012", test.transcriptSingleSem(0));
    }
}
