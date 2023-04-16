package koshin;

import java.util.List;
import javax.swing.*;
import koshin.DataTransferObject;


public class DemoWorker extends SwingWorker<Void, Integer>{
/*
The first type is what is returned by doInBackground(), and the second is returned
by process().  If you do not want doInBackground() to return anything, 
set Void as the type argument.
*/            

private DataTransferObject theDTO;

DemoWorker(DataTransferObject theDTO) {
    this.theDTO = theDTO;
}

    @Override
    protected Void doInBackground() throws Exception { // background thread
        /*
        Simulate the download of a file.  We wait 1 second, then report
        20% downloaded, etc.
        */
        
        theDTO.startButton.setEnabled(false);
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //calls process with data added to List, 10% more each iteration.
            publish( i );
        }
        return null;
    }


    /*
    This is were the worker reports data to update the list.
    */
    @Override
    protected void process(List<Integer> chunks) { // main thread
        //Grab data from the int last added to the list.
        theDTO.updateManifestFileProgressBar.setValue(chunks.get(chunks.size()-1));
    }


    //Called when doInBackground() completes.
    @Override
    public void done() { // main thread
        //We are done, so set value to 0 in preparation for another download.
        theDTO.startButton.setEnabled(true);
    }


}; // end class DemoWorker
        