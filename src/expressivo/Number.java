package expressivo;

import java.util.Map;

public class Number implements Expression {
    private final double n;

    // Abstraction function:
    //      represents the nonnegative number n
    // Rep invariant:
    //      0 <= num <= Double.MAX_VALUE
    // Safe from Rep:
    //      all fields are private final and immutable

    private void checkRep() {
        assert n >= 0;
    }

    /** Make a number */
    public Number(double n) {
        this.n = n;
        checkRep();
    }

    public double getNumber() {
        return n;
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Number )) return false;
        Number that = (Number) obj;
        return Double.compare(this.n, that.n) == 0;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long c = Double.doubleToLongBits(n);
        return 31*result + (int)(c^(c>>>32));
    }

    @Override
    public Expression differentiate(Expression exp) {
        return Expression.make(0);
    }

    @Override
    public Expression evaluate(Map<String, Double> environment) {
        return this;
    }

    @Override
    public boolean isNumeric() {
        return true;
    }
}
