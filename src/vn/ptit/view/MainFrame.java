/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.view;

import javax.swing.JFrame;

/**
 *
 * @author Cuong Pham
 */
public class MainFrame extends JFrame{
    private int height = 900;
    private int width = 1300;
    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setTitle("Puzzle");
        setLocationRelativeTo(null);
        ContainerPanel cp = new ContainerPanel(this);
        add(cp);
    }
    
}
