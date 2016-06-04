package INTERM_LANG;

/**
 *
 * @author cbanegas
 */
public class Label {
    private static int labelCount = 0;
    private String labelName;

    public Label() {
        this("L" + labelCount++);
    }

    public Label(String labelName) {
        this.labelName = labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return labelName;
    }
    
}
