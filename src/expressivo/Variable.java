package expressivo;

public class Variable implements Expression {
    private final String var;

    // Abstraction function
    //    represents the variable var
    // Rep invariant
    //    var contains only a-zA-Z
    // Safety from rep exposure
    //    all fields are immutable and final

    private void checkRep() {
        assert var.matches("[a-zA-Z]+");
    }
    public Variable(String var) {
        this.var = var;
        checkRep();
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) {
            return false;
        }
        Variable that = (Variable)obj;
        return this.var.equals(that.var);
    }

    @Override
    public int hashCode() {
        int result = 17;
        int c = var.hashCode();
        return 31*result + c;
    }
}
