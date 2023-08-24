public class Cell {
    private String operation;
    private String arg1;
    private String arg2;
    private Integer value;

    public Cell(String operation, String arg1, String arg2, Integer value) {
        this.operation = operation;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
