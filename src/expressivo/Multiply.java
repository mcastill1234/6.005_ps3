package expressivo;


public class Multiply implements Expression {
    private final Expression e1;
    private final Expression e2;

    // Abstraction function:
    //      represents the product of two expressions e1 and e2
    // Rep invariant:
    //      true
    // Safe from Rep:
    //      all fields are immutable and final

    public Multiply(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public String toString() {
        return e1.toString() + "*" + e2.toString();
    }
}
