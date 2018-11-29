package expressivo;

import java.util.Map;

public class Variable implements Expression {
    private final String var;

    // Abstraction function
    //    represents the variable var
    // Rep invariant
    //    var contains only a-zA-Z
    // Safety from rep exposure
    //    all fields are private final and immutable

    private void checkRep() {
        assert var.matches("[a-zA-Z]+");
    }

    /** Make a Variable */
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

    @Override
    public Expression differentiate(Expression exp) {
        if (this.equals(exp)) {
            return Expression.make(1);
        } else {
            return Expression.make(0);
        }
    }

    @Override
    public Expression evaluate(Map<String, Double> environment) {
        if (environment.containsKey(var)) {
            return Expression.make(environment.get(var));
        } else {
            return this;
        }
    }

    @Override
    public boolean isNumeric() {
        return false;
    }

}
