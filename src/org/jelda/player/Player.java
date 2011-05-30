/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Player.java
 *
 * Created on May 29, 2011, 10:32:52 PM
 */

package org.jelda.player;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Blaise
 */
public class Player extends javax.swing.JFrame {

    QuestRunner questRunner;
    /** Creates new form Player */
    public Player() {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception ex) {}
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadQuestFileChooser = new javax.swing.JFileChooser();
        drawPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        staticStatusText = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadQuestMenuItem = new javax.swing.JMenuItem();
        stopQuestMenuItem = new javax.swing.JMenuItem();
        quitMenuItem = new javax.swing.JMenuItem();

        loadQuestFileChooser.setDialogTitle("Open Quest");
        loadQuestFileChooser.setFileFilter(new jqstFileFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jquest Player");

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);

        staticStatusText.setText("Status:");
        jToolBar1.add(staticStatusText);

        statusLabel.setText("Not Started");
        jToolBar1.add(statusLabel);

        fileMenu.setText("File");

        loadQuestMenuItem.setText("Load Quest");
        loadQuestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadQuestMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadQuestMenuItem);

        stopQuestMenuItem.setText("Stop Quest");
        stopQuestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopQuestMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(stopQuestMenuItem);

        quitMenuItem.setText("Quit");
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitMenuItem);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadQuestMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadQuestMenuItemActionPerformed
	loadQuest();
    }//GEN-LAST:event_loadQuestMenuItemActionPerformed

    private void stopQuestMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopQuestMenuItemActionPerformed
	stopQuest();
    }//GEN-LAST:event_stopQuestMenuItemActionPerformed

    private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuItemActionPerformed
	closeWindow();
    }//GEN-LAST:event_quitMenuItemActionPerformed

    public void loadQuest() throws HeadlessException {
	int returnVal = loadQuestFileChooser.showOpenDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    File file = loadQuestFileChooser.getSelectedFile();
	    statusLabel.setText("Loading Quest...");
	    questRunner = new QuestRunner(file, drawPanel.getGraphics());
	    statusLabel.setText("Starting Quest...");
	    questRunner.start();
	    statusLabel.setText("Running");
	}
    }

    public void stopQuest() {
	if (questRunner != null && questRunner.isRunning()) {
	    statusLabel.setText("Stopping...");
	    questRunner.stop();
	    statusLabel.setText("Stopped");
	}
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Player().setVisible(true);
            }
        });
    }

    public void closeWindow() {
	if (questRunner == null || (questRunner != null && !questRunner.isRunning()) || (questRunner != null && questRunner.isRunning() && JOptionPane.showConfirmDialog(this, "A quest is currently running. Are you sure you want to quit?") == JOptionPane.YES_OPTION)) {
	    stopQuest();
	    WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	    setVisible(false);
	    dispose();
	    System.exit(0);
	} else {
	    return;
	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel drawPanel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JFileChooser loadQuestFileChooser;
    private javax.swing.JMenuItem loadQuestMenuItem;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JLabel staticStatusText;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JMenuItem stopQuestMenuItem;
    // End of variables declaration//GEN-END:variables


    private class jqstFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
	    return file.isDirectory() || file.getAbsolutePath().endsWith(".jqst");
	}

	@Override
	public String getDescription() {
	    return "Jelda Quest Files (*.jqst)";
	}
	
    }
}
