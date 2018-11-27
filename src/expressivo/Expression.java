/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and * nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Data type definition (abbreviated Expr)
    //
    // Expr = Number(num: int) + Variable(var: String)
    //      + Plus(e1: Expr, e2: Expr)
    //      + Multiply(e1: Expr, e2: Expr)

    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        try {
            CharStream stream = new ANTLRInputStream(input);
            ExpressionLexer lexer = new ExpressionLexer(stream);
            lexer.reportErrorsAsExceptions();

            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();

            ParseTree tree = parser.root();
            MakePolyExpression expr = new MakePolyExpression();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(expr, tree);
            return expr.getExpression();
        }
        catch (ParseCancellationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // TODO more instance methods

    /**
     * Creates a Number expression with Double format
     * @param num number to be represented as expression, requires num >= 0
     * @return Number expression
     */
    public static Expression make(double num) {
        return new Number(num);
    }

    /**
     * Creates a Variable expression with String format
     * @param var variable to be represented as expression, requires var only contains chars a-z, A-Z
     * @return Variable expression
     */
    public static Expression make(String var) { return new Variable(var); }

    /**
     * Creates a Plus expression of two sub-expressions. Strips unnecessary zeroes.
     * @param e1 left side expression
     * @param e2 right side expression
     * @return Plus Expression e1 + e2
     */
    public static Expression sum(Expression e1, Expression e2) {
        Number zero = new Number(0);
        if (e1.equals(zero)) {
            return e2;
        }
        if (e2.equals(zero)) {
            return e1;
        }
        return new Plus(e1, e2);
    }

    /**
     * Creates a Multiply expression of two sub-expressions. Strips unnecessary ones and zeroes.
     * @param e1 left side expression
     * @param e2 right sie expression
     * @return Multiply expression e1 * e2
     */
    public static Expression times(Expression e1, Expression e2) {
        Number zero = new Number(0);
        Number one = new Number(1);
        if (e1.equals(zero) || e2.equals(zero)) {
            return new Number(0);
        }
        if (e1.equals(one)) {
            return e2;
        }
        if (e2.equals(one)) {
            return e1;
        }
        return new Multiply(e1, e2);
    }

    /**
     * Differentiates this expression with respect to variable exp.
     * Requires variable to be expressed as an Expression object, not a string.
     * @param exp the expression for the variable to differentiate by
     * @return Differentiated expression with respect to variable exp
     */
    public Expression differentiate(Expression exp);



}
