package koshin;

public class Status {
    
    public enum Section {
        CUSTOM,
        DEFAULT,
        DIST,
        MANIFEST
    }
    
    public enum Action {
        SET_INDETERMINATE,
        SET_MAX,
        SET_PROGRESS
    }
    
    private final Section section;
    private final Action action;
    private int max;
    private int progress;
    
    public Status(
            Section section, 
            Action action
    ) throws Exception {
        this.section = section;
        this.action = action;
        if (action != Action.SET_INDETERMINATE) {
            throw new Exception("Can't have this status without a value");
        }
    }
    
    public Status(
            Section section, 
            Action action, 
            int n
    ) throws Exception {
        this.section = section;
        this.action = action;
        switch(action) {
            case SET_MAX:
                this.max = n;
                break;
            case SET_PROGRESS:
                this.progress = n;
                break;            
            default:
            throw new Exception("Can't have this status with a value");
        }
    }
    
    public Section getSection() {
        return this.section;
    }
    
    public Action getAction(){
        return this.action;
    }
    
    public int getMax(){
        return this.max;
    }
    
    public int getProgress(){
        return this.progress;
    }
    
}
