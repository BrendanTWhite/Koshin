package koshin;

import java.io.File;
import java.util.*;
import javax.swing.*;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.*;

public class BackgroundWorker extends SwingWorker<Void, koshin.Status> {
// The first type is the doInBackground() method's return type (or Void for null)
// The second is the process() method's return type (or Void for null)

// <editor-fold desc="variables">    
    // 4095 - default size of Dist and Def hash sets
    // 1023 - default size of Custom hash sets
    static final int CUST_HASHSET_INITIAL_SIZE = 1023;
    static final int DEF_HASHSET_INITIAL_SIZE = 4095;
    static final int DIST_HASHSET_INITIAL_SIZE = 4095;

    HashSet<Path> custFileHashSet = new HashSet<>(CUST_HASHSET_INITIAL_SIZE);
    HashSet<Path> defFileHashSet = new HashSet<>(DEF_HASHSET_INITIAL_SIZE);
    HashSet<Path> distFileHashSet = new HashSet<>(DIST_HASHSET_INITIAL_SIZE);
// To check whether an item exists in a HashSet, use the contains() method    
    HashSet<Path> filesToCopy = new HashSet<>();
    HashSet<Path> filesToDelete = new HashSet<>();

    long startTime;
    long endTime;

    private static long l = 0;
    private static File f;
    private static FileTime ft;

    private final Koshin koshin;

    private final Path custDirPath;
    private final Path defDirPath;
    private final Path distDirPath;
// </editor-fold>    

    
    BackgroundWorker(Koshin koshin) throws Exception {
        // Constructor - runs in main thread
        this.koshin = koshin;

// <editor-fold desc="set-and-check-three-nodes">    
        
        this.custDirPath = Paths.get(koshin.getCustomDirectoryPathTextField().getText());
        this.defDirPath = custDirPath.resolveSibling("default");
        this.distDirPath = custDirPath.resolveSibling("distribution");
        Files.createDirectories(distDirPath); // create it if needed

        if (!custDirPath.toFile().getName().equalsIgnoreCase("custom")) {
            throw new Exception("Directory is not 'custom'");
        }
        if (!custDirPath.toFile().exists()) {
            throw new Exception("Custom directory not found");
        }
        if (!custDirPath.toFile().isDirectory()) {
            throw new Exception("Custom is not a directory");
        }

        if (!defDirPath.toFile().exists()) {
            throw new Exception("Default directory not found");
        }
        if (!defDirPath.toFile().isDirectory()) {
            throw new Exception("Default is not a directory");
        }

        if (!distDirPath.toFile().exists()) {
            throw new Exception("Distribution directory not found");
        }
        if (!distDirPath.toFile().isDirectory()) {
            throw new Exception("Distribution is not a directory");
        }
// </editor-fold>    

// <editor-fold desc="set-ui-elements">    

        // Set UI elements ready for starting
        koshin.getStartButton().setEnabled(false);
        koshin.getCustomDirectoryPathSelectButton().setEnabled(false);

        koshin.getCustomProgressBar().setMinimum(0);
        koshin.getDefProgressBar().setMinimum(0);
        koshin.getDistProgressBar().setMinimum(0);
        koshin.getManifestProgressBar().setMinimum(0);

        koshin.getCustomProgressBar().setMaximum(100);
        koshin.getDefProgressBar().setMaximum(100);
        koshin.getDistProgressBar().setMaximum(100);
        koshin.getManifestProgressBar().setMaximum(100);                
              
        koshin.getCustomProgressBar().setValue(0);
        koshin.getDefProgressBar().setValue(0);
        koshin.getDistProgressBar().setValue(0);
        koshin.getManifestProgressBar().setValue(0);
// </editor-fold>    

    }

    @Override
    protected Void doInBackground() throws Exception { // background thread
        int filesDone;
        
        try {

            if (koshin.getCustCheckBox().isSelected()) {
// <editor-fold desc="run-custom-files-check">                

                // Get the set of files for Custom
                // could take a while if the files are on a server
                this.startStopwatch();
                
                
                koshin.getCustomProgressBar().setIndeterminate(true);
                koshin.getCustomProgressBar().setValue(100);
                publish(new Status(
                        Status.Section.CUSTOM, 
                        Status.Action.SET_INDETERMINATE
                ));
                
                System.out.println("Starting to get path contents...");
                custFileHashSet = getAllDecendants(custDirPath);                                
                goSlow(1500);
                System.out.println("...got path contents.");

                publish(new Status(
                        Status.Section.CUSTOM, 
                        Status.Action.SET_MAX,
                        custFileHashSet.size()
                ));

                this.stopStopwatch("get sets of files");
                System.out.println();

                // Foreach file in Custom
                // - Check equivalent file in Dist
                // - - if not there AND IDENTICAL in Dist, then add to Copy list
                this.startStopwatch();
                filesDone = 0;

                for (Path fullCustFilePath : custFileHashSet) {
                    Path fileRelativePath = custDirPath.relativize(fullCustFilePath);
                    Path fullDistFilePath = distDirPath.resolve(fileRelativePath);
                    if (!sameFingerprint(fullCustFilePath, fullDistFilePath)) {
                        filesToCopy.add(fullCustFilePath);
                    }
                    filesDone++;
                    goSlow(5);
                    publish(new Status(
                            Status.Section.CUSTOM, 
                            Status.Action.SET_PROGRESS,
                            filesDone
                    ));
                }
                this.stopStopwatch("add Custom files to Copy set");
                

            // write out the results
            System.out.println("Custom files checked: " + custFileHashSet.size());
            System.out.println("Files to Copy from Custom: " + filesToCopy.size());

// </editor-fold>                
            }

            if (koshin.getDefCheckBox().isSelected()) {            
// <editor-fold desc="run-default-files-check">    

            // Get the set of files for Default
            // should take around 0.1 seconds in total
            // but can take quite a while if the files are on a server
            this.startStopwatch();
            defFileHashSet = getAllDecendants(defDirPath);
            goSlow(500);
            this.stopStopwatch("get sets of files");
            System.out.println();

            // Foreach file in Default
            // - Check equivalent file in Custom
            // - - if there, then skip this file
            // - - if not there, then continue
            // - Check equivalent in Dist
            // - - if not there AND IDENTICAL in Dist, then add to Copy list
            // all in RAM so instantaneous
            this.startStopwatch();
            for (Path fullDefFilePath : defFileHashSet) {
                Path fileRelativePath = defDirPath.relativize(fullDefFilePath);
                Path fullCustFilePath = custDirPath.resolve(fileRelativePath);
                Path fullDistFilePath = distDirPath.resolve(fileRelativePath);

                if (Files.exists(fullCustFilePath)) {
                    // already handled as a Custom file, so just continue
                    continue;
                }

                if (!sameFingerprint(fullDefFilePath, fullDistFilePath)) {
                    filesToCopy.add(fullDefFilePath);
                }
            }
            this.stopStopwatch("add Default files to Copy set");

            // write out the results
            System.out.println("Default files checked: " + defFileHashSet.size());
            System.out.println("Files to Copy (all): " + filesToCopy.size());
            for (Path p : filesToCopy) {
//                System.out.println(p.toString());
            }
            System.out.println();

// </editor-fold>    
            }

            if (koshin.getDistCheckBox().isSelected()) {                        
// <editor-fold desc="run-dist-files-check">    
            
            // Get the set of files for Dist
            // should take around 0.1 seconds in total
            // but can take quite a while if the files are on a server
            this.startStopwatch();
            distFileHashSet = getAllDecendants(distDirPath);
            goSlow(500);
            this.stopStopwatch("get sets of files");
            System.out.println();

            // Foreach file in Dist
            // - Check equivalent file in Default
            // - - if it exists, identical or no, skip this file
            // - - if not, then continue
            // - Check equivalent in Custom
            // - - if it exists, identical or no, skip this file
            // - - if not, then add to Delete list
            // all in RAM so instantaneous
            this.startStopwatch();
            for (Path fullDistFilePath : distFileHashSet) {
                Path fileRelativePath = distDirPath.relativize(fullDistFilePath);
                Path fullCustFilePath = custDirPath.resolve(fileRelativePath);
                Path fullDefFilePath = defDirPath.resolve(fileRelativePath);

                if (Files.exists(fullCustFilePath)) {
                    // It exists as a Custom file, so just continue
                    continue;
                }

                if (Files.exists(fullDefFilePath)) {
                    // It exists as a Default file, so just continue
                    continue;
                }

                // Doesn't exist in Def or Cust, so add it to the delete list
                filesToDelete.add(fullDistFilePath);

            }
            this.stopStopwatch("add Dist files to Delete set");

            // write out the results
            System.out.println("Dist files checked: " + distFileHashSet.size());
            System.out.println("Files to Delete: " + filesToDelete.size());
            for (Path p : filesToDelete) {
//                System.out.println(p.toString());
            }
            System.out.println();

            // For each file in the copy list
            // - Copy the file
            // can have working progress bar, because
            // we know up front how many files in list            
            this.startStopwatch();
            for (Path fileToCopy : filesToCopy) {
                Path relativeFileToCopy = null;
                if (fileToCopy.startsWith(custDirPath)) {
//                    System.out.println("From Cust: "+fileToCopy.toString());
                    relativeFileToCopy = custDirPath.relativize(fileToCopy);
                } else if (fileToCopy.startsWith(defDirPath)) {
//                    System.out.println("From Def: "+fileToCopy.toString());
                    relativeFileToCopy = defDirPath.relativize(fileToCopy);
                } else {
                    // it's not Default OR Custom ... Houston, we have a problem.
                    throw new Exception("asdf");
                }
                Path distFullPath = distDirPath.resolve(relativeFileToCopy);
//                System.out.println("...To Dist: "+distFullPath.toString());                
                Files.createDirectories(distFullPath.getParent());
                Files.copy(
                        fileToCopy, distFullPath,
                        StandardCopyOption.COPY_ATTRIBUTES,
                        StandardCopyOption.REPLACE_EXISTING,
                        LinkOption.NOFOLLOW_LINKS
                );
            }
            this.stopStopwatch("Copying files");

            
            // For each file in the delete list
            // - Delete the file
            // can have working progress bar, because
            // we know up front how many files in list
            this.startStopwatch();
            for (Path fileToDelete : filesToDelete) {
                Files.delete(fileToDelete);
            }
            this.stopStopwatch("Deleting files");
// </editor-fold>    
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getLocalizedMessage(),
                    //                    ex.getStackTrace(),
                    "Error: " + ex.getLocalizedMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }

        return null;
    }

    @Override protected void process(List<Status> chunks) { // main thread
        JProgressBar progressBar;    
        Status status;
        
        while (!chunks.isEmpty()) {
            status = chunks.remove(0); // pop oldest item
            progressBar = getProgressBar(status.getSection());
            
            switch (status.getAction()) {
                case SET_INDETERMINATE -> {
                    progressBar.setIndeterminate(true);
                }
                case SET_MAX -> {
                    progressBar.setIndeterminate(false);
                    progressBar.setMaximum(status.getMax());
                }
                case SET_PROGRESS -> {
                    progressBar.setValue(status.getProgress());
                }
                default ->
                    throw new Error("Unexpected Action " + status.getAction());
            }
        }
    }

    JProgressBar getProgressBar(koshin.Status.Section section){
        
        switch(section) {
            case CUSTOM:   return koshin.getCustomProgressBar();
            case DEFAULT:  return koshin.getDefProgressBar();
            case DIST:     return koshin.getDistProgressBar();
            case MANIFEST: return koshin.getManifestProgressBar();
        }
        return null;
    }
        
    //Called when doInBackground() completes.
    @Override public void done() { // main thread
        koshin.getStartButton().setEnabled(true);
        koshin.getCustomDirectoryPathSelectButton().setEnabled(true);
    }

    void startStopwatch() {
        startTime = java.lang.System.currentTimeMillis();
    }

    void stopStopwatch(String s) {
        endTime = java.lang.System.currentTimeMillis();
        System.out.println(s + ": " + ((endTime - startTime) / 1000.0) + "secs");

    }

    HashSet<Path> getAllDecendants(Path thePath) throws Exception {
        List<Path> result;
        try (
                Stream<Path> pathStream = Files.find(thePath,
                        Integer.MAX_VALUE,
                        (p, basicFileAttributes) -> !Files.isDirectory(p))) {
            result = pathStream.collect(Collectors.toList());
        }
        return new HashSet(result);
    }

    boolean sameFingerprint(Path p1, Path p2) throws Exception {

        if (!Files.exists(p1)) {
            return false;
        }

        if (!Files.exists(p2)) {
            return false;
        }

        if (Files.size(p1) != Files.size(p2)) {
            return false;
        }

        if (Files.getLastModifiedTime(p1, LinkOption.NOFOLLOW_LINKS).to(TimeUnit.SECONDS)
                != Files.getLastModifiedTime(p2, LinkOption.NOFOLLOW_LINKS).to(TimeUnit.SECONDS)) {
            return false;
        }

        return true;
    }

    void goSlow(int milliseconds) {
        if (System.getProperty("go_slow") != null
                && System.getProperty("go_slow").equals("Y")) {
            try {
                System.out.print("Waiting " + milliseconds + " ms ... ");
                Thread.sleep(milliseconds);
                System.out.println("Done.");
            } catch (Exception e) {
                //  don't really care
            }
        }
    }

}; // end class BackgroundWorker

