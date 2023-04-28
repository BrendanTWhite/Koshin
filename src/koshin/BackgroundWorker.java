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

        try {
            
            
            
            // Get three lists of files
            
            // - Custom, Default, Dist
            // should take around 0.1 seconds in total
            
            
            
            // For each file in each of the three lists
            
            // - get the filesize in bytes, and last mod timestamp
            // can have working progress bar, because
            // we know up front how many files are in each list
            
            
            
            // Foreach file in Default
            
            // - If equivalent file exists in Custom
            // - - then Source = Custom file
            // - - else Source = Default file
            
            // - If equivalet in Dist does not exist
            // - - then copy Source to Dist (or add to copy list)
            
            // - else if Dist exists but fingerprint <>  source fingerprint
            // - - then copy Source to Dist (or add to copy list)
            
            // - else if Dist exists and fingerprint ==  source fingerprint
            // - - then this file hasn't changed, so do nothing

            
            
            // Foreach file in Custom
            
            // - If equivalent file does not exist in Dist
            // - - then copy Custom file to Dist (or add to copy list)
            
            
            
            // Foreach file in Dist
            // - If it doesn't exist in Default OR Custom
            // - - then add to Delete list
            
            
            
            // For each file in the copy / delete list
            
            // - Copy or delete the file
            // can have working progress bar, because
            // we know up front how many files in list
            
            

            for (int i = 0; i <= 100; i += 10) {
                {
                    Thread.sleep(100);
                }
                
//                System.out.println("Publishing Rebuild " + i);
                Status status = new Status();
                status.setStatusRebuilding(i);
                publish(status);
            }

            for (int i = 0; i <= 100; i += 10) {
                {
                    Thread.sleep(50);
                }

//                System.out.println("Publishing Manifest " + i);
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
//                    System.out.println("Processing Rebuild " + status.getProgress());
                    koshin.getUpdateCustomFilesProgressBar().setValue(status.getProgress());
                }
                case MANIFESTING -> {
//                    System.out.println("Processing Manifest " + status.getProgress());
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

