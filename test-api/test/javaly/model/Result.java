package javaly.model;

public class Result implements Resultable{
    private String description;
    private Object expected;
    private Object actual;
    private boolean success;

    public Result(String description, Object expected, Object actual, boolean success) {
        this.description = description;
        this.expected = expected;
        this.actual = actual;
        this.success = success;
    }


    public Result(Object expected, Object actual, boolean success) {
        this("", expected, actual, success);
    }

    public String getDescription() {
        return description;
    }

    public Object getExpected() {
        return expected;
    }

    public Object getActual() {
        return actual;

    }

    public boolean isSuccess() {
        return success;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Description: ");
        sb.append(description);
        sb.append("\nExpected: ");
        sb.append(expected);
        sb.append("\nActual: ");
        sb.append(actual);
        sb.append("\nSuccess: ");
        sb.append(success);
        sb.append("\n");
        return sb.toString();
    }
}
