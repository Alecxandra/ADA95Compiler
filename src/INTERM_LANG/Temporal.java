package INTERM_LANG;

/**
 *
 * @author cbanegas
 */
public class Temporal {
    
    private static int temporalCount;
    private String temporalLiteral;

    public Temporal() {
        int index = this.temporalCount++;
        this.temporalLiteral = "t" + index;
    }

    public Temporal(String temporalLiteral) {
        this.temporalLiteral = temporalLiteral;
    }

    @Override
    public String toString() {
        return this.temporalLiteral;
    }

}
