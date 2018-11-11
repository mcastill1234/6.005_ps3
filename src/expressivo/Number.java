package expressivo;

public class Number implements Expression {
    private final double n;

    // Abstraction function:
    //      represents the positive number n
    // Rep invariant:
    //      num >= 0
    // Safe from Rep:
    //      all fields are immutable and final

    private void checkRep() {
        assert n >= 0;
    }

    /** Make a number */
    public Number(double n) {
        this.n = n;
        checkRep();
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

}
