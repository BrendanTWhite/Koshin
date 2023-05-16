/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package koshin;

import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.*;

/**
 *
 * @author brendantwhite
 */
public class Koshin extends javax.swing.JFrame {

    /**
     * Creates new form
     */
    public Koshin() {
        initComponents();

        Preferences prefs = Preferences.userNodeForPackage(Koshin.class);
        customDirectoryPathTextField.setText(prefs.get("custom_directory_path", "< click the Select button >"));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customDirectoryPathLabel = new javax.swing.JLabel();
        customDirectoryPathTextField = new javax.swing.JTextField();
        customDirectoryPathSelectButton = new javax.swing.JButton();
        customDirectorySeparator = new javax.swing.JSeparator();
        custCheckBox = new javax.swing.JCheckBox();
        custProgressBar = new javax.swing.JProgressBar();
        custSeparator = new javax.swing.JSeparator();
        defCheckBox = new javax.swing.JCheckBox();
        defProgressBar = new javax.swing.JProgressBar();
        defSeparator = new javax.swing.JSeparator();
        distCheckBox = new javax.swing.JCheckBox();
        distProgressBar = new javax.swing.JProgressBar();
        distSeparator = new javax.swing.JSeparator();
        manifestCheckBox = new javax.swing.JCheckBox();
        manifestProgressBar = new javax.swing.JProgressBar();
        manifestSeparator = new javax.swing.JSeparator();
        startButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Koshin");
        setMaximumSize(new java.awt.Dimension(1024, 800));
        setMinimumSize(new java.awt.Dimension(320, 455));

        customDirectoryPathLabel.setLabelFor(customDirectoryPathTextField);
        customDirectoryPathLabel.setText("Custom Directory");

        customDirectoryPathTextField.setEditable(false);

        customDirectoryPathSelectButton.setText("Select...");
        customDirectoryPathSelectButton.setToolTipText("");
        customDirectoryPathSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customDirectoryPathSelectButtonActionPerformed(evt);
            }
        });

        custCheckBox.setSelected(true);
        custCheckBox.setText("Refresh Custom Files");
        custCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custCheckBoxActionPerformed(evt);
            }
        });

        defCheckBox.setText("Copy Default Files");
        defCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defCheckBoxActionPerformed(evt);
            }
        });

        distCheckBox.setText("Trim Distribution Files");

        manifestCheckBox.setSelected(true);
        manifestCheckBox.setText("Refresh Manifest File");
        manifestCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manifestCheckBoxActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customDirectoryPathLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(customDirectoryPathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(customDirectoryPathSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(customDirectorySeparator)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(custSeparator))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(defSeparator))
                    .addComponent(distCheckBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(distSeparator)
                    .addComponent(manifestCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(manifestSeparator))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(manifestProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(distProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(defProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(defCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(custProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(custCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customDirectoryPathLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customDirectoryPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customDirectoryPathSelectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customDirectorySeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(custCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(custProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(custSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(defProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manifestCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manifestProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manifestSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customDirectoryPathSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customDirectoryPathSelectButtonActionPerformed
        Preferences prefs = Preferences.userNodeForPackage(Koshin.class);
        File currentCustomDirectory = new File(customDirectoryPathTextField.getText());

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the Custom directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (currentCustomDirectory.exists() && currentCustomDirectory.isDirectory()) {
            fileChooser.setSelectedFile(currentCustomDirectory);
        } else {
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        }

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentCustomDirectory = fileChooser.getSelectedFile();
            prefs.put("custom_directory_path", currentCustomDirectory.getAbsolutePath());
            customDirectoryPathTextField.setText(currentCustomDirectory.getAbsolutePath());
        }

    }//GEN-LAST:event_customDirectoryPathSelectButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed

        try {
            new BackgroundWorker(this).execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getStackTrace(),
                    "Error: " + ex.getLocalizedMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }//GEN-LAST:event_startButtonActionPerformed

    private void custCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custCheckBoxActionPerformed

    private void manifestCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manifestCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manifestCheckBoxActionPerformed

    private void defCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defCheckBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        // Set System L&F
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Koshin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Koshin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox custCheckBox;
    private javax.swing.JProgressBar custProgressBar;
    private javax.swing.JSeparator custSeparator;
    private javax.swing.JLabel customDirectoryPathLabel;
    private javax.swing.JButton customDirectoryPathSelectButton;
    private javax.swing.JTextField customDirectoryPathTextField;
    private javax.swing.JSeparator customDirectorySeparator;
    private javax.swing.JCheckBox defCheckBox;
    private javax.swing.JProgressBar defProgressBar;
    private javax.swing.JSeparator defSeparator;
    private javax.swing.JCheckBox distCheckBox;
    private javax.swing.JProgressBar distProgressBar;
    private javax.swing.JSeparator distSeparator;
    private javax.swing.JCheckBox manifestCheckBox;
    private javax.swing.JProgressBar manifestProgressBar;
    private javax.swing.JSeparator manifestSeparator;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getCustomDirPathSelectButton() {
        return customDirectoryPathSelectButton;
    }

    public JProgressBar getUpdateCustomFilesProgressBar() {
        return custProgressBar;
    }

    public JProgressBar getUpdateManifestFileProgressBar() {
        return manifestProgressBar;
    }
    
    public JTextField getCustomDirectoryPathTextField() {
        return customDirectoryPathTextField;
    }
}
