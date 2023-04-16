package koshin;

import javax.swing.*;

/**
 *
 * @author brendantwhite
 */
public class DataTransferObject {
    
    private boolean running = false;
    
    private JProgressBar updateCustomFilesProgressBar;
    private JProgressBar updateManifestFileProgressBar;
    private JTextField manifestFilePathTextName;

    public void set (
        JProgressBar updateCustomFilesProgressBar,
        JProgressBar updateManifestFileProgressBar,
        JTextField manifestFilePathTextName
    ) {
        this.updateCustomFilesProgressBar = updateCustomFilesProgressBar;
        this.updateManifestFileProgressBar = updateManifestFileProgressBar;
        this.manifestFilePathTextName = manifestFilePathTextName;
    }
    
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public JProgressBar getUpdateCustomFilesProgressBar() {
        return this.updateCustomFilesProgressBar;
    }
    
    public JProgressBar getUpdateManifestFileProgressBar() {
        return this.updateManifestFileProgressBar;
    }
    
    public JTextField getManifestFilePathTextName() {
        return this.manifestFilePathTextName;
    }
    
    public void setUpdateCustomFilesProgressBar(JProgressBar updateCustomFilesProgressBar) {
        this.updateCustomFilesProgressBar = updateCustomFilesProgressBar;
    }

    public void setUpdateManifestFileProgressBar (JProgressBar updateManifestFileProgressBar) {
        this.updateManifestFileProgressBar = updateManifestFileProgressBar;
    }
    
    public void setManifestFilePathTextName (JTextField asdf) {
        this.manifestFilePathTextName = manifestFilePathTextName;
    }
    
}
