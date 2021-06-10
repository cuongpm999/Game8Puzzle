/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Cuong Pham
 */
public class Tutorial extends JPanel{
    private Image img;
    private ContainerPanel containerPanel;

    public Tutorial(Image img, ContainerPanel containerPanel) {
        this.containerPanel = containerPanel;
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(40, 20, 40, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JTextArea jTextArea = new JTextArea();
        jTextArea.setText("Bấm các phím A, S, D, W hoặc các phím mũi tên để di\n"
                +         "chuyển ô vuông xám sang trái, xuống dưới, sang phải,\n"
                +         "lên trên để ghép 9 ô thành hình ảnh bên phải màn hình.\n\n"
                +         "Hoặc bạn có thể dùng 2 thuật toán BFS hoặc A* để tìm\nđường đi đến đích.");
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
        jTextArea.setFont(font);
        
        jTextArea.setEditable(false);
        
        Border compound=BorderFactory.createCompoundBorder(
                          BorderFactory.createEtchedBorder(EtchedBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        Border redline = BorderFactory.createLineBorder(Color.red);
        compound = BorderFactory.createCompoundBorder(
                          redline, compound);
        jTextArea.setBorder(compound);
        jTextArea.setBackground(Color.WHITE);
        
        add(jTextArea,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx=50;
        gbc.ipady=5;
        JButton jButton = new JButton("Quay lại");
        jButton.setFont(font);
        jButton.setBackground(Color.decode("#00FFFF"));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                containerPanel.setShowMenu();
            }
        });
        add(jButton,gbc);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
    
    
}
