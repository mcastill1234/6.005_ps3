package expressivo;

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
}
