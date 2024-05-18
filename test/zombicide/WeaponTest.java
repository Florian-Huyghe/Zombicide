package test.zombicide;
import static org.junit.Assert.*;

import org.junit.Test;

import zombicide.weapon.Rifle;

public class WeaponTest {

    @Test
    public void test_rifle_created_with_default_values() {
        Rifle rifle = new Rifle();
        assertEquals("Rifle", rifle.getName());
        assertEquals(2, rifle.getNumberOfDiceThrows());
        assertEquals(4, rifle.getThreshold());
        assertEquals(1, rifle.getDamageValue());
        assertEquals(1, rifle.getMinRange());
        assertEquals(3, rifle.getMaxRange());
    }
    

}
