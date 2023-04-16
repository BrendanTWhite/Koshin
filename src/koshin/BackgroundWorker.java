package koshin;

import java.util.List;
import javax.swing.*;

public class BackgroundWorker extends SwingWorker<Void, Integer> {
// The first type is the doInBackground() method's return type 
// The second is the process() method's return type  

    private final Koshin koshin;

    BackgroundWorker(Koshin koshin) {
        this.koshin = koshin;
    }

    @Override
    protected Void doInBackground() throws Exception { // background thread
        /*
        Simulate the download of a file.  We wait 1 second, then report
        20% downloaded, etc.
         */

        koshin.getStartButton().setEnabled(false);
        koshin.getManifestFilePathTextNameSelectButton().setEnabled(false);

        try {
            
            for (int i = 1; i <= 100; i++) {
                {
                    Thread.sleep(10);
                }

                //calls process with data added to List, 10% more each iteration.
                publish(i);
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

        return null;
    }


    /*
    This is were the worker reports data to update the list.
     */
    @Override
    protected void process(List<Integer> chunks) { // main thread
        //Grab data from the int last added to the list.
        koshin.getUpdateManifestFileProgressBar().setValue(chunks.get(chunks.size() - 1));
    }

    //Called when doInBackground() completes.
    @Override
    public void done() { // main thread
        //We are done, so set value to 0 in preparation for another download.
        koshin.getStartButton().setEnabled(true);
        koshin.getManifestFilePathTextNameSelectButton().setEnabled(true);
    }

}; // end class BackgroundWorker

