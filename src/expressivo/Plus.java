package expressivo;

import java.util.Map;

public class Plus implements Expression {
    private final Expression e1;
    private final Expression e2;

    // Abstraction function:
    //      represents the sum of two expressions e1 and e2
    // Rep invariant:
    //      expressions e1 and e2 are non-null and immutable
    // Safe from Rep:
    //      all fields are private final and immutable

    private void checkRep() {
        assert e1 != null;
        assert e2 != null;
    }

    /** Make a Plus which is the sum of e1 and e2 expressions */
    public Plus(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        checkRep();
    }

    @Override
    public String toString() {
        return "(" + e1.toString() + " + " + e2.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Plus)) {
            return false;
        }
        Plus that = (Plus)obj;
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
        return Expression.sum(e1.differentiate(exp), e2.differentiate(exp));
    }

    @Override
    public Expression evaluate(Map<String, Double> environment) {
        Plus addition = new Plus(e1.evaluate(environment), e2.evaluate(environment));
        if (addition.isNumeric()) {
            return new Number (((Number) addition.e1).getNumber() + ((Number) addition.e2).getNumber()) ;
        }
        return addition;
    }

    @Override
    public boolean isNumeric() {
        return e1.isNumeric() && e2.isNumeric();
    }

}
