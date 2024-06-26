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
    public void changeGrade() {
        Faculty test = new Faculty();
        test.updateGrade("92546743","AQ822",0, "CH4701",99);
    }

    @Test
    public void changeGrade2() {
        Faculty test = new Faculty();
        test.updateGrade("22348070","LM123",0, "CS0016",55.6);
    }

    @Test
    public void changeComment() {
        Faculty test = new Faculty();
        test.updateComment("22348069","LM115",0, "CS4141","itworks!!");
    }
}
