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
            
            
            
            // Get a list of files for each Node
            
            // - Custom, Default, Dist
            // should take around 0.1 seconds in total
            
            
            
            // For each file in each of the three lists
            
            // - get the filesize in bytes, and last mod timestamp
            
            // can have working progress bar, because
            // we know up front how many files are in each list
            
            
            
            // Foreach file in Custom
            
            // - Check equivalent file in Dist
            // - - if not there in Dist, then add to Copy list
            // - - if there but different, then add to Copy list
            
            // all in RAM so instantaneous


            
            // Foreach file in Default
            
            // - Check equivalent file in Custom
            // - - if there and different, then skip this file
            // - - if there and identical, then LOG A WARNING and skip this file
            // - - if not there, then continue
            
            // - Check equivalent in Dist
            // - - if not there, then add to copy list
            // - - if there and different, then add to Copy list
                        
            // all in RAM so instantaneous
            
            
                        
            // Foreach file in Dist
            
            // - Check equivalent file in Default
            // - - if it exists, identical or no, skip this file
            // - - if not, then continue
            
            // - Check equivalent in Custom
            // - - if it exists, identical or no, skip this file
            // - - if not, then add to Delete list

            // all in RAM so instantaneous
            
            
            
            // For each file in the copy / delete list
            
            // - Copy or delete the file

            // can have working progress bar, because
            // we know up front how many files in list
            

            
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

