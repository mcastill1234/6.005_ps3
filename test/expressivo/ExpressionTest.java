/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   - Test
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // TODO tests for Expression
    @Test
    public void testDummyMethod() {
        double num1 = 5.43;
        double num2 = 5.43001;
        Expression exp1 = Expression.make(num1);
        Expression exp2 = Expression.make(num2);
        System.out.println(exp1.equals(exp2));
    }

    /**
     * Tests for Number expression
     *      - Test toString, equals and hashCode
     *      - Test other methods
     */

    // Covers Number toString method
    @Test public void testToStringNumber() {
        Number zero = new Number(0);
        Number num1 = new Number(8.26);
        Number asInt = new Number(16);
        assertEquals("8.26", num1.toString());
        assertEquals("0.0", zero.toString());
        assertEquals("16.0", asInt.toString());
    }

    // Covers Number equals method
    @Test public void testEqualsNumber() {
        Number num1 = new Number(8.26);
        Number sameNum1 = new Number(8.26);
        Number sameNum2 = new Number(8.26);
        Number diffNum1 = new Number(0.26);
        Number asInt = new Number(16);
        Number asDouble = new Number(16.0);
        assertTrue(num1.equals(num1));    // Reflexivity
        assertTrue(num1.equals(sameNum1));  // Compare two equal numbers
        assertTrue(sameNum1.equals(num1));  // Symmetry
        assertTrue(sameNum1.equals(sameNum2));  // Transitivity
        assertTrue(num1.equals(sameNum2));  // Transitivity
        assertFalse(num1.equals(diffNum1)); // Compare different numbers
        assertTrue(asInt.equals(asDouble)); // Same number with tail zeroes
        assertFalse(num1.equals(null));     // Compare with null object

    }

    // Covers Number hashCode method
    @Test public void testHashCodeNumber() {
        Number num1 = new Number(8.26);
        Number sameNum1 = new Number(8.26);
        Number sameNum2 = new Number(8.26);
        Number asInt = new Number(16);
        Number asDouble = new Number(16.0);
        assertEquals(num1.hashCode(), sameNum1.hashCode());
        assertEquals(num1.hashCode(), sameNum2.hashCode());
        assertEquals(asInt.hashCode(), asDouble.hashCode());
        assertNotEquals(num1.hashCode(), asInt.hashCode());
    }

    /**
     * Tests for Variable expression
     *      - Test toString, equals and hashCode
     */

    // Helper function with different types of strings to use in unit tests.
    public String[] makeStringList() {
        String oneLower = "n";
        String oneUpper = "N";
        String upperStart = "Var";
        String capsOnly = "VAR";
        String smallOnly = "var";
        String[] tstrs= {oneLower,oneUpper,upperStart,capsOnly,smallOnly};
        return tstrs;
    }

    // Covers Variable toString method
    @Test public void testToStringVariable() {
        String[] testStrings = makeStringList();
        for (String s: testStrings) {
            assertEquals(s, new Variable(s).toString());
        }

    }

    // Covers Variable equals method
    @Test public void testEqualsVariable() {
        Variable var = new Variable("alpha");
        Variable varCaps = new Variable("AlphA");
        Variable varSame = new Variable("alpha");
        Variable varSame2 = new Variable("alpha");
        Variable varDiff = new Variable("phi");
        assertTrue(var.equals(var));    // Reflexivity
        assertTrue(var.equals(varSame));    // Compare two equal variables
        assertTrue(varSame.equals(var));    // Symmetry
        assertTrue(varSame.equals(varSame2));   // Transitivity
        assertTrue(var.equals(varSame2));   // Transitivity
        assertFalse(var.equals(varCaps));   // Compare different variables
        assertFalse(var.equals(null));      // Compare with null object
    }

    // Covers Variable hashCode method
    @Test public void testHashCodeVariable() {
        Variable var = new Variable("alpha");
        Variable varSame = new Variable("alpha");
        Variable varSame2 = new Variable("alpha");
        assertEquals(var.hashCode(),varSame.hashCode());
        assertEquals(var.hashCode(),varSame2.hashCode());

    }
}
