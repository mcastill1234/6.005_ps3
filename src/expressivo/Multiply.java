package expressivo;

import java.util.Map;

public class Multiply implements Expression {
    private final Expression e1;
    private final Expression e2;

    // Abstraction function:
    //      represents the product of two expressions e1 and e2
    // Rep invariant:
    //      expressions e1 and e2 are non-null and immutable
    // Safe from Rep:
    //      all fields are immutable and final

    private void checkRep() {
        assert e1 != null;
        assert e2 != null;
    }

    /** Make a Multiply which is the sum of e1 and e2 expressions */
    public Multiply(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        checkRep();
    }

    @Override
    public String toString() {
      return "(" +  e1.toString() + " * " + e2.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Multiply)) {
            return false;
        }
        Multiply that = (Multiply)obj;
        return this.e1.equals(that.e1) && this.e2.equals(that.e2);

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + e1.hashCode();
        result = 31*result + e2.hashCode();
        return result;
    }

    @Override
    public Expression differentiate(Expression exp) {
        Expression leftDiff = Expression.times(e1, e2.differentiate(exp));
        Expression rightDiff = Expression.times(e2, e1.differentiate(exp));
        return Expression.sum(leftDiff, rightDiff);
    }

    @Override
    public Expression evaluate(Map<String, Double> environment) {
        Multiply product = new Multiply(e1.evaluate(environment), e2.evaluate(environment));
        if (product.isNumeric()) {
            return new Number(((Number) product.e1).getNumber() * ((Number) product.e2).getNumber());
        }
        return product;
    }

    @Override
    public boolean isNumeric() {
        return e1.isNumeric() && e2.isNumeric();
    }
}
