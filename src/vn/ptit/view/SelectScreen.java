/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Cuong Pham
 */
public class SelectScreen extends JPanel{
    private Image img;
    private ContainerPanel containerPanel;
    private JLabel jLabelImg1,jLabelImg2,jLabelImg3,jLabelTitle,jLabelNamePlay;
    private JTextField jTextField;
    private GamePlay gamePlay;
    private MainFrame frame;

    public SelectScreen(Image img, ContainerPanel containerPanel,MainFrame frame) {
        this.containerPanel = containerPanel;
        this.img = img;
        this.frame=frame;
        
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,20,70,20);
        gbc.fill = GridBagConstraints.VERTICAL;
        
        Image image1 = new ImageIcon(getClass().getResource(
                "/img/sasuke-anhgoc.jpg")).getImage();
        Icon icon1 = new ImageIcon(image1.getScaledInstance(350, 350,
                image1.SCALE_SMOOTH));
        
        Image image2 = new ImageIcon(getClass().getResource(
                "/img/girl-anhgoc.jpg")).getImage();
        Icon icon2 = new ImageIcon(image2.getScaledInstance(350, 350,
                image2.SCALE_SMOOTH));
        
        Image image3 = new ImageIcon(getClass().getResource(
                "/img/thewitcher3-anhgoc.jpg")).getImage();
        Icon icon3 = new ImageIcon(image3.getScaledInstance(350, 350,
                image3.SCALE_SMOOTH));
        
        Border compound=BorderFactory.createCompoundBorder(
                          BorderFactory.createEtchedBorder(EtchedBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        Border redline = BorderFactory.createLineBorder(Color.red);
        compound = BorderFactory.createCompoundBorder(
                          redline, compound);
        
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 50);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        jLabelTitle = new JLabel("Chọn màn");
        jLabelTitle.setFont(font);
        add(jLabelTitle,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        jLabelTitle = new JLabel("Tên của bạn: ");
        jLabelTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        add(jLabelTitle,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        jTextField = new JTextField(20);
        jTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        add(jTextField,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        jLabelImg1 = new JLabel(icon1);
        jLabelImg1.setBorder(compound);
        jLabelImg1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jLabelImg1MouseClicked();
            }
        });
        add(jLabelImg1,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        jLabelImg2 = new JLabel(icon2);
        jLabelImg2.setBorder(compound);
        jLabelImg2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jLabelImg2MouseClicked();
            }
        });
        add(jLabelImg2,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        jLabelImg3 = new JLabel(icon3);
        jLabelImg3.setBorder(compound);
        jLabelImg3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jLabelImg3MouseClicked();
            }
        });
        add(jLabelImg3,gbc);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
    
    private void jLabelImg1MouseClicked(){
        if(jTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên của bạn");
        }
        else{
            gamePlay = new GamePlay(jTextField.getText(),"sasuke",containerPanel,frame);
            containerPanel.add(gamePlay,"tag_playgame");
            containerPanel.setShowPlay();
        }
    }
    
     private void jLabelImg2MouseClicked(){
        if(jTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên của bạn");
        }
        else{
            gamePlay = new GamePlay(jTextField.getText(),"girl",containerPanel,frame);
            containerPanel.add(gamePlay,"tag_playgame");
            containerPanel.setShowPlay();
        }
    }
     
     private void jLabelImg3MouseClicked(){
        if(jTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên của bạn");
        }
        else{
            gamePlay = new GamePlay(jTextField.getText(),"thewitcher3",containerPanel,frame);
            containerPanel.add(gamePlay,"tag_playgame");
            containerPanel.setShowPlay();
        }
    }
    
}
