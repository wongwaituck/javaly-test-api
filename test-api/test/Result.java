public class Result {
    private String description;
    private String expected;
    private String actual;
    private boolean success;

    public Result(String description, String expected, String actual, boolean success) {
        this.description = description;
        this.expected = expected;
        this.actual = actual;
        this.success = success;
    }


    public Result(String expected, String actual, boolean success) {
        this("", expected, actual, success);
    }

    public String getDescription() {
        return description;
    }

    public String getExpected() {
        return expected;
    }

    public String getActual() {
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
