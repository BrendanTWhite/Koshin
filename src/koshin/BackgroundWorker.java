package koshin;

import java.util.List;
import javax.swing.*;

public class BackgroundWorker extends SwingWorker<Void, Status> {
// The first type is the doInBackground() method's return type (or Void for null)
// The second is the process() method's return type (or Void for null)

    private final Koshin koshin;

    BackgroundWorker(Koshin koshin) {
        // Constructor - runs in main thread
        this.koshin = koshin;

        koshin.getStartButton().setEnabled(false);
        koshin.getManifestFilePathTextNameSelectButton().setEnabled(false);
        
        koshin.getUpdateCustomFilesProgressBar().setValue(0);
        koshin.getUpdateManifestFileProgressBar().setValue(0);

    }

    @Override
    protected Void doInBackground() throws Exception { // background thread
        /*
        Simulate the download of a file.  We wait 1 second, then report
        20% downloaded, etc.
         */

        try {

            for (int i = 0; i <= 100; i += 10) {
                {
                    Thread.sleep(100);
                }
                
                System.out.println("Publishing Rebuild " + i);
                Status status = new Status();
                status.setStatusRebuilding(i);
                publish(status);
            }

            for (int i = 0; i <= 100; i += 10) {
                {
                    Thread.sleep(50);
                }

                System.out.println("Publishing Manifest " + i);
                Status status = new Status();                
                status.setStausManifesting(i);
                publish(status);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getStackTrace(),
                    "Error: " + e.getLocalizedMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }

        Status status = new Status();                
        status.setStatusFinished();
        publish(status);

        return null;
    }


    /**
     *
     * @param chunks
     */

    @Override
    protected void process(List<Status> chunks) { // main thread

        while (!chunks.isEmpty()) {
            Status status = chunks.remove(0); // pop oldest item

            switch (status.getState()) {
                case REBUILDING -> {
                    System.out.println("Processing Rebuild " + status.getProgress());
                    koshin.getUpdateCustomFilesProgressBar().setValue(status.getProgress());
                }
                case MANIFESTING -> {
                    System.out.println("Processing Manifest " + status.getProgress());
                    koshin.getUpdateManifestFileProgressBar().setValue(status.getProgress());
                }
                case IDLE ->
                    System.out.println("Done ");
                default ->
                    throw new Error("Unexpected state " + status.getState());
            }
        }
    }

    //Called when doInBackground() completes.
    @Override
    public void done() { // main thread
        koshin.getStartButton().setEnabled(true);
        koshin.getManifestFilePathTextNameSelectButton().setEnabled(true);
    }

}; // end class BackgroundWorker

