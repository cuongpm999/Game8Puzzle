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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cuong Pham
 */
public class JPanelMenu extends JPanel implements ActionListener {

    private Image img;
    private ContainerPanel containerPanel;
    private JButton btnNewGame,btnTutorial,btnHightScore,btnExit;
    private JLabel jLabelLogo;
    private HightScore hightScore;

    public JPanelMenu(Image img, ContainerPanel containerPanel) {
        this.containerPanel = containerPanel;
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20,20,20,20);
       
        btnNewGame = new JButton("Chơi mới");
        btnNewGame.addActionListener(this);
        
        btnTutorial = new JButton("Hướng dẫn");
        btnTutorial.addActionListener(this);
        
        btnHightScore = new JButton("Điểm cao");
        btnHightScore.addActionListener(this);
        
        btnExit = new JButton("Thoát");
        btnExit.addActionListener(this);
        
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
        
        btnNewGame.setFont(font);
        btnNewGame.setBackground(Color.decode("#00FFFF"));
        
        btnTutorial.setFont(font);
        btnTutorial.setBackground(Color.decode("#00FFFF"));
        
        btnHightScore.setFont(font);
        btnHightScore.setBackground(Color.decode("#00FFFF"));
        
        btnExit.setFont(font);
        btnExit.setBackground(Color.decode("#00FFFF"));
        
        Image image = new ImageIcon(getClass().getResource(
                "/img/logo.png")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(400, 400,
                image.SCALE_SMOOTH));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        jLabelLogo = new JLabel(icon);
        add(jLabelLogo,gbc);
        
        gbc.ipadx=50;
        gbc.ipady=5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btnNewGame,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnTutorial,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnHightScore,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(btnExit,gbc);
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewGame) {
            containerPanel.setShowScreen();
        }
        if (e.getSource() == btnExit) {
            System.exit(0);
        }
        if (e.getSource() == btnHightScore) {
            hightScore = new HightScore(new ImageIcon(getClass()
                .getResource("/img/selectscreen.jpg")).getImage(), containerPanel);
            containerPanel.add(hightScore, "tag_hightscore");
            containerPanel.setShowHightScore();
        }
        if (e.getSource() == btnTutorial) {
            containerPanel.setShowTutorial();
        }
        
    }

}
