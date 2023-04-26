package koshin;

public class Status {

    public enum State {
        IDLE,
        REBUILDING,
        MANIFESTING
    }
    
    private State state = State.IDLE;
    private int progress = 0;

    public State getState() {
        return this.state;
    }
    
    public int getProgress() {
        return this.progress;
    }

    public void setStatusRebuilding(int progress) {
        this.state = State.REBUILDING;
        this.progress = progress;
    }

    public void setStausManifesting(int progress) {
        this.state = State.MANIFESTING;
        this.progress = progress;
    }

    public void setStatusFinished() {
        this.state = State.IDLE;
    }
   
}
