/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;
import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // TODO tests for Commands.differentiate() and Commands.simplify()

    @Test public void testCommandDifferentiateMethod() {
        String testExp = "x * x * x";
        String diffTestExp = Commands.differentiate(testExp, "x");
        String expected = "((x * x) + (x * (x + x)))";
        assertEquals(expected, diffTestExp);
    }

    @Test public void testCommandSimplifyMethod() {
        String testExp = "x*x*x + y*y*y";
        Map<String, Double> environment = new HashMap<>();
        // environment.put("x", 5.00);
        environment.put("y", 10.00);
        // environment.put("z", 20.00);
        String simplified = Commands.simplify(testExp, environment);
        String expected = "(((x * x) * x) + 1000.0)";
        assertEquals(expected, simplified);
    }
    
}
