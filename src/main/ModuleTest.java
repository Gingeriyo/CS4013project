package main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    // This test makes sure the marking scheme for CS4141 is correct.
    @Test
    public void ModuleConstructor() throws FileNotFoundException {
        Module mod = new Module("CS4141", "src/csv/modules.csv");
        int[] test_arr = {0,1,30,35,40,48,52,56,60,64,72,80};
        assertEquals(test_arr[2], mod.getMarkingScheme()[2]);
    }

    // This test checks to see if the name for the module CS4141 is correct.
    @Test
    public void ModuleCode() throws FileNotFoundException {
        Module mod = new Module("CS4141", "src/csv/modules.csv");
        assertEquals("Introduction to Programming", mod.getName());
    }

    // This test checks if the course code is correct.
    @Test
    public void ModuleCode2() throws FileNotFoundException {
        Module mod = new Module("CS4012", "src/csv/modules.csv");
        assertEquals("CS4012", mod.getClassCode());
    }

    @Test
    public void Transciptinfotest() {
    try {
    String [] s = Module.leo(22348070,0);
    String expected = "Fundamentals of Computer Organisation";
    assertEquals("ET4011", s[0]);
    } catch(FileNotFoundException e) {
        e.printStackTrace();
    }
    }
    @Test
    public void changegrade() {
        Faculty.updateGrade("22348069", "LM115", 0, "CH4701", 70.0);
    }
}
