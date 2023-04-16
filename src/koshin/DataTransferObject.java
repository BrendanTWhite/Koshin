package koshin;

import javax.swing.*;

/**
 *
 * @author brendantwhite
 */
public class DataTransferObject {
        
    final public JButton startButton;
    final public JProgressBar updateCustomFilesProgressBar;
    final public JProgressBar updateManifestFileProgressBar;
    final public JTextField manifestFilePathTextName;

    public DataTransferObject (
        JButton startButton,
        JProgressBar updateCustomFilesProgressBar,
        JProgressBar updateManifestFileProgressBar,
        JTextField manifestFilePathTextName
    ) {
        this.startButton = startButton;
        this.updateCustomFilesProgressBar = updateCustomFilesProgressBar;
        this.updateManifestFileProgressBar = updateManifestFileProgressBar;
        this.manifestFilePathTextName = manifestFilePathTextName;
    }
                
}
