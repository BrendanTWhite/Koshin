package koshin;

import java.io.File;
import java.util.List;
import javax.swing.*;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.stream.*;

public class BackgroundWorker extends SwingWorker<Void, Status> {
// The first type is the doInBackground() method's return type (or Void for null)
// The second is the process() method's return type (or Void for null)

    long startTime;
    long endTime;

    private static long l = 0;
    private static File f;
    private static FileTime ft;

    private final Koshin koshin;

    private final Path customDirPath;
    private final Path defaultDirPath;
    private final Path distDirPath;

    BackgroundWorker(Koshin koshin) throws Exception {
        // Constructor - runs in main thread
        this.koshin = koshin;

        koshin.getStartButton().setEnabled(false);
        koshin.getCustomDirPathSelectButton().setEnabled(false);

        koshin.getUpdateCustomFilesProgressBar().setValue(0);
        koshin.getUpdateManifestFileProgressBar().setValue(0);

        this.customDirPath = Paths.get(koshin.getCustomDirectoryPathTextField().getText());
        this.defaultDirPath = customDirPath.resolveSibling("default");
        this.distDirPath = customDirPath.resolveSibling("distribution");
        Files.createDirectories(distDirPath); // create it if needed

        if (!customDirPath.toFile().exists()) {
            throw new Exception("Custom directory not found");
        }
        if (!customDirPath.toFile().isDirectory()) {
            throw new Exception("Custom is not a directory");
        }

        if (!defaultDirPath.toFile().exists()) {
            throw new Exception("Default directory not found");
        }
        if (!defaultDirPath.toFile().isDirectory()) {
            throw new Exception("Default is not a directory");
        }

        if (!distDirPath.toFile().exists()) {
            throw new Exception("Distribution directory not found");
        }
        if (!distDirPath.toFile().isDirectory()) {
            throw new Exception("Distribution is not a directory");
        }

    }

    @Override
    protected Void doInBackground() throws Exception { // background thread

        try {

            // Get a list of files for each Node
            // - Custom, Default, Dist
            // should take around 0.1 seconds in total
            this.startStopwatch();
            List<Path> customFiles = getAllDecendants(customDirPath);
            List<Path> defaultFiles = getAllDecendants(defaultDirPath);
            List<Path> distFiles = getAllDecendants(distDirPath);
            this.stopStopwatch("get lists of files");

            // For each file in each of the three lists
            // - get the filesize in bytes, and last mod timestamp
            // can have working progress bar, because
            // we know up front how many files are in each list
            this.startStopwatch();
            for (int i = 0; i < customFiles.size(); i++) {
                l = Files.size(customFiles.get(i));
                ft = Files.getLastModifiedTime(customFiles.get(i),LinkOption.NOFOLLOW_LINKS);
            }
            for (int i = 0; i < defaultFiles.size(); i++) {
                l = Files.size(defaultFiles.get(i));
                ft = Files.getLastModifiedTime(defaultFiles.get(i),LinkOption.NOFOLLOW_LINKS);
            }
            for (int i = 0; i < distFiles.size(); i++) {
                l = Files.size(distFiles.get(i));
                ft = Files.getLastModifiedTime(distFiles.get(i),LinkOption.NOFOLLOW_LINKS);
            }
            this.stopStopwatch("get file sizes and last mod dates");

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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getStackTrace(),
                    "Error: " + ex.getLocalizedMessage(),
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
        koshin.getCustomDirPathSelectButton().setEnabled(true);
    }

    void startStopwatch() {
        startTime = java.lang.System.currentTimeMillis();
    }

    void stopStopwatch(String s) {
        endTime = java.lang.System.currentTimeMillis();
        System.out.println(s + ": " + ((endTime - startTime) / 1000.0) + "secs");

    }

    List<Path> getAllDecendants(Path thePath) throws Exception {
        List<Path> result;
        try (
                Stream<Path> pathStream = Files.find(thePath,
                        Integer.MAX_VALUE,
                        (p, basicFileAttributes) -> !Files.isDirectory(p))) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;
    }

}; // end class BackgroundWorker

