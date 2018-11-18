package expressivo;

import java.util.Stack;

import expressivo.parser.*;

/** Make an Expression value from a parse tree. */
public class MakePolyExpression extends ExpressionBaseListener {
    private Stack<Expression> stack = new Stack<>();

    /**
     * Returns the expression constructed by this listener object.
     * Requires that this listener has completely walked over an Expression
     * parse tree using ParseTreeWalker.
     * @return Expression for the parse tree that was walked
     */
    public Expression getExpression() {
        return stack.get(0);
    }

    @Override
    public void exitMult(ExpressionParser.MultContext ctx) {
        // matched the Mult alternative
        Expression e2 = stack.pop();
        Expression e1 = stack.pop();
        Expression product = new Multiply(e1, e2);
        stack.push(product);
    }

    @Override
    public void exitPlus(ExpressionParser.PlusContext ctx) {
        // matched the Plus alternative
        Expression e2 = stack.pop();
        Expression e1 = stack.pop();
        Expression sum = new Plus(e1, e2);
        stack.push(sum);
    }

    @Override
    public void exitNum(ExpressionParser.NumContext ctx) {
        if (ctx.NUM() != null){
            // matched the Num alternative
            double n = Double.valueOf(ctx.NUM().getText());
            Expression number = new Number(n);
            stack.push(number);
        } else {
            // matched a different alternative. Do nothing.
        }
    }

    @Override public void exitVar(ExpressionParser.VarContext ctx) {
        if (ctx.VAR() != null) {
            Expression variable = new Variable(ctx.getText());
            stack.push(variable);
        } else {
            // matched a different alternative. Do nothing.
        }
    }
}
