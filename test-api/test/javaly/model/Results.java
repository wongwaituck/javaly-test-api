package javaly.model;

import java.util.*;

public class Results {
    private ArrayList<Result> results;
    private boolean success = true;

    public Results() {
        results = new ArrayList<>();
    }

    public void add(Result item) {
        results.add(item);
        if (!item.isSuccess()) {
            success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<Result> getListFromResults() {
        return results;
    }

    public String toString(){
      return results.toString();
    }

}
