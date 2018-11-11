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
    //      - Test toString, equals and hashCode for all types of expressions
    //      - Test other methods
    
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
     */

    // Covers Number toString method
    @Test public void testToStringNumber() {
        Expression zero = Expression.make(0);
        Expression num1 = Expression.make(8.26);
        Expression asInt = Expression.make(16);
        assertEquals("0.0", zero.toString());
        assertEquals("8.26", num1.toString());
        assertEquals("16.0", asInt.toString());
    }

    // Covers Number equals method
    @Test public void testEqualsNumber() {
        Expression num1 = Expression.make(8.26);
        Expression sameNum1 = Expression.make(8.26);
        Expression sameNum2 = Expression.make(8.26);
        Expression diffNum1 = Expression.make(0.26);
        Expression asInt = Expression.make(16);
        Expression asDouble = Expression.make(16.0);
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
        Expression num1 = Expression.make(8.26);
        Expression sameNum1 = Expression.make(8.26);
        Expression sameNum2 = Expression.make(8.26);
        Expression asInt = Expression.make(16);
        Expression asDouble = Expression.make(16.0);
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
            assertEquals(s, Expression.make(s).toString());
        }

    }

    // Covers Variable equals method
    @Test public void testEqualsVariable() {
        Expression var = Expression.make("alpha");
        Expression varCaps = Expression.make("AlphA");
        Expression varSame = Expression.make("alpha");
        Expression varSame2 = Expression.make("alpha");
        Expression varDiff = Expression.make("phi");
        assertTrue(var.equals(var));    // Reflexivity
        assertTrue(var.equals(varSame));    // Compare two equal variables
        assertTrue(varSame.equals(var));    // Symmetry
        assertTrue(varSame.equals(varSame2));   // Transitivity
        assertTrue(var.equals(varSame2));   // Transitivity
        assertFalse(var.equals(varCaps));   // Compare different variables
        assertFalse(var.equals(varDiff));   // Compare different variables
        assertFalse(var.equals(null));      // Compare with null object
    }

    // Covers Variable hashCode method
    @Test public void testHashCodeVariable() {
        Expression var = Expression.make("alpha");
        Expression varSame = Expression.make("alpha");
        Expression varSame2 = Expression.make("alpha");
        assertEquals(var.hashCode(),varSame.hashCode());
        assertEquals(var.hashCode(),varSame2.hashCode());

    }

    /**
     * Tests for Plus expression
     *      - Test toString, equals and hashCode
     */

    // Covers Plus toString method
    @Test public void testToStringPlus() {
        Expression num1 = Expression.make(19.2);
        Expression var1 = Expression.make("a");
        Expression sum1 = Expression.sum(num1, var1);
        Expression sum2 = Expression.sum(sum1, num1);
        Expression mult1 = Expression.times(num1, var1);
        Expression sum3 = Expression.sum(var1, mult1);
        Expression sum4 = Expression.sum(sum1, sum3);
        assertEquals("(19.2 + a)", sum1.toString());
        assertEquals("((19.2 + a) + 19.2)", sum2.toString());
        assertEquals("(a + (19.2 * a))", sum3.toString());
        assertEquals("((19.2 + a) + (a + (19.2 * a)))", sum4.toString());
    }

    // Cover Plus equals method
    @Test public void testEqualsPlus() {
        Expression num1 = Expression.make(83.2);
        Expression var1 = Expression.make("spin");
        Expression sum1 = Expression.sum(num1, var1);
        Expression revSum1 = Expression.sum(var1, num1);
        Expression sameSum = Expression.sum(num1, var1);
        Expression sameSum1 = Expression.sum(num1, var1);
        assertTrue(sum1.equals(sum1));  // Reflexivity
        assertTrue(sum1.equals(sameSum));  // Compare two equal expressions
        assertTrue(sameSum.equals(sum1));   // Symmetry
        assertTrue(sameSum.equals(sameSum1));   // Transitivity
        assertTrue(sameSum.equals(sameSum1));   // Transitivity
        assertFalse(sum1.equals(null));         // Compare with null
        assertFalse(sum1.equals(revSum1));      // (Num + Var) != (Var + Num)
    }

    // Cover Plus hashCode method
    @Test public void testHashCodePlus() {
        Expression num1 = Expression.make(83.2);
        Expression var1 = Expression.make("spin");
        Expression sum1 = Expression.sum(num1, var1);
        Expression sameSum = Expression.sum(num1, var1);
        assertEquals(sum1.hashCode(), sameSum.hashCode());
    }

    /**
     * Tests for Multiply expression
     *      - Test toString, equals and hashCode
     */

    // Covers Plus toString method
    @Test public void testToStringMultiply() {
        Expression num1 = Expression.make(19.2);
        Expression var1 = Expression.make("a");
        Expression mult1 = Expression.times(num1, var1);
        Expression mult2 = Expression.times(mult1, num1);
        Expression sum1 = Expression.sum(num1, var1);
        Expression mult3 = Expression.times(var1, sum1);
        Expression mult4 = Expression.times(mult1, mult3);
        assertEquals("(19.2 * a)", mult1.toString());
        assertEquals("((19.2 * a) * 19.2)", mult2.toString());
        assertEquals("(a * (19.2 + a))", mult3.toString());
        assertEquals("((19.2 * a) * (a * (19.2 + a)))", mult4.toString());
    }

    // Cover Plus equals method
    @Test public void testEqualsMultiply() {
        Expression num1 = Expression.make(83.2);
        Expression var1 = Expression.make("spin");
        Expression mult1 = Expression.times(num1, var1);
        Expression revMult1 = Expression.times(var1, num1);
        Expression sameMult = Expression.times(num1, var1);
        Expression sameMult1 = Expression.times(num1, var1);
        assertTrue(mult1.equals(mult1));  // Reflexivity
        assertTrue(mult1.equals(sameMult));  // Compare two equal expressions
        assertTrue(sameMult.equals(mult1));   // Symmetry
        assertTrue(sameMult.equals(sameMult1));   // Transitivity
        assertTrue(sameMult.equals(sameMult1));   // Transitivity
        assertFalse(mult1.equals(null));         // Compare with null
        assertFalse(mult1.equals(revMult1));     // (1 * a) =! (a * 1)
    }

    // Cover Plus hashCode method
    @Test public void testHashCodeMultiply() {
        Expression num1 = Expression.make(83.2);
        Expression var1 = Expression.make("spin");
        Expression mult1 = Expression.times(num1, var1);
        Expression sameMult = Expression.times(num1, var1);
        assertEquals(mult1.hashCode(), sameMult.hashCode());
    }
}
