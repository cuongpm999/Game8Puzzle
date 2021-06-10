/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Cuong Pham
 */
public class BackgroundLoading extends JPanel {
    private ContainerPanel containerPanel;
    private Image img;
    private JProgressBar jProgressBar;

    public BackgroundLoading(Image img,ContainerPanel containerPanel) {
        this.containerPanel=containerPanel;
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        
        jProgressBar = new JProgressBar();
        jProgressBar.setBackground(Color.decode("#7FFFD4"));
        jProgressBar.setForeground(Color.decode("#191970"));
        jProgressBar.setPreferredSize(new Dimension(800,50));
        jProgressBar.setStringPainted(true);
        jProgressBar.setString("Đang tải game ... 0%");
        jProgressBar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        add(jProgressBar,gbc);
        
        RunSlow runSlow = new RunSlow();
        runSlow.start();

    }

    class RunSlow extends Thread {
        @Override
        public void run() {
            int phanTram = 0;
            while (true) {
                if (jProgressBar.getValue() >= jProgressBar.getMaximum()) {
                    containerPanel.setShowMenu();
                    break;
                }
                phanTram += 10;
                jProgressBar.setValue(jProgressBar.getValue() + 10);
                jProgressBar.setString("Đang tải game ... " + phanTram + "%");
                try {
                    sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
