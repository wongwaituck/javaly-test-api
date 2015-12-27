package javaly.model;

import java.util.*;

public class Runs {
    private ArrayList<Run> runs;
    private boolean success = true;

    public Runs() {
        runs = new ArrayList<>();
    }

    public void add(Run item) {
        runs.add(item);
        if (!item.getResult().isSuccess()) {
            success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<Run> getListFromRuns() {
        return runs;
    }

    public String toString(){
      return runs.toString();
    }

    public Run popRun(){
      return runs.remove(runs.size() - 1);
    }

    public void convertLastRunToHidden(){
      Run run = popRun();
      Resultable result = run.getResult();
      run.setResult(HiddenResult.convertResultToHidden(result));
      runs.add(run);
    }

}
