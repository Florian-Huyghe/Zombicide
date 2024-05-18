package test.zombicide;
import static org.junit.Assert.*;

import org.junit.Test;

import zombicide.tool.MasterKey;
import zombicide.tool.Tool;


public class ToolsTest {

        // can be instantiated without errors
    @Test
    public void test_instantiation() {
        Tool tool = new MasterKey();
        assertNotNull(tool);
        assertEquals("Master Key", tool.toString());

    }
    
     
}
