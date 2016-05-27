package INTERM_LANG;

/**
 *
 * @author cbanegas
 */
public class TempVar {
    
    private static int tempVarCount;
    private String tempVarLiteral;

    public TempVar() {
        int index = this.tempVarCount++;
        this.tempVarLiteral = "t" + index;
    }

    public TempVar(String tempVarLiteral) {
        this.tempVarLiteral = tempVarLiteral;
    }

    @Override
    public String toString() {
        return this.tempVarLiteral;
    }

}
