/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import vn.ptit.controller.DAO;
import vn.ptit.model.EmptyCellPoint;
import vn.ptit.model.Matrix;
import vn.ptit.model.Player;

/**
 *
 * @author Cuong Pham
 */
public class JPanelImg extends JPanel {

    private int bound = 2;
    private int size = 250;
    private JButton[][] btn;
    private Matrix matrix;
    private Color backGroundColor = Color.lightGray;
    private GamePlay gamePlay;
    private int indexI, indexJ;
    private DAO dao;
    private MainFrame frame;

    public JPanelImg(GamePlay gamePlay,MainFrame frame) {
        dao=new DAO();
        this.gamePlay = gamePlay;
        this.frame=frame;
        btn = new JButton[3][3];

        this.setLayout(new GridLayout(3, 3, bound, bound));
        this.setBackground(backGroundColor);
        this.setPreferredSize(new Dimension((size + bound) * 3, (size + bound)
                * 3));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        newGame();
    }

    public void deleteAllBtn() {
        if (getComponentCount() > 0) {
            removeAll();
        }
    }

    public void newGame() {
        matrix = new Matrix();
        addArrayButton();
    }

    public void addArrayButton() {
        deleteAllBtn();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btn[i][j] = new JButton();
                Icon icon = getIcon(matrix.getMatrix()[i][j]);
                btn[i][j].setIcon(icon);
                btn[i][j].addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        btnKeyReleased(e);
                    }
                });
                add(btn[i][j]);
            }
        }

        EmptyCellPoint ecp = matrix.getPointXY();
        indexI = ecp.getX();
        indexJ = ecp.getY();

        btn[indexI][indexJ].requestFocus();

        frame.setVisible(true);
    }

    private Icon getIcon(int index) {
        int width = 250, height = 250;
        Image image = new ImageIcon(getClass().getResource(
                "/img/"+gamePlay.getNameImg()+"-" + index + ".jpg")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(width, height,
                image.SCALE_SMOOTH));
        return icon;
    }

    public void btnKeyReleased(KeyEvent e) {
        EmptyCellPoint ecp = matrix.getPointXY();
        indexI = ecp.getX();
        indexJ = ecp.getY();

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            if (indexI < 2) {
                int[][] t = matrix.getMatrix();
                int val = t[indexI][indexJ];
                t[indexI][indexJ] = t[indexI + 1][indexJ];
                t[indexI + 1][indexJ] = val;
                matrix.setMatrix(t);

                System.out.println("--->");
                matrix.showMatrix();
                addArrayButton();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            if (indexI > 0) {
                int[][] t = matrix.getMatrix();
                int val = t[indexI][indexJ];
                t[indexI][indexJ] = t[indexI - 1][indexJ];
                t[indexI - 1][indexJ] = val;
                matrix.setMatrix(t);

                System.out.println("--->");
                matrix.showMatrix();
                addArrayButton();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            if (indexJ < 2) {
                int[][] t = matrix.getMatrix();
                int val = t[indexI][indexJ];
                t[indexI][indexJ] = t[indexI][indexJ + 1];
                t[indexI][indexJ + 1] = val;
                matrix.setMatrix(t);

                System.out.println("--->");
                matrix.showMatrix();
                addArrayButton();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            if (indexJ > 0) {
                int[][] t = matrix.getMatrix();
                int val = t[indexI][indexJ];
                t[indexI][indexJ] = t[indexI][indexJ - 1];
                t[indexI][indexJ - 1] = val;
                matrix.setMatrix(t);

                System.out.println("--->");
                matrix.showMatrix();
                addArrayButton();
            }
        }
        if (matrix.checkWin()) {
            Image image = new ImageIcon(getClass().getResource(
                    "/img/icon-win.jpg")).getImage();

            Icon icon = new ImageIcon(image.getScaledInstance(50, 50,
                    image.SCALE_SMOOTH));
            gamePlay.getCount().suspend();
            dao.addPlayer(new Player(gamePlay.getNamePlayer(), gamePlay.getjLabelTimeCount().getText()));
            JOptionPane.showMessageDialog(frame, "Bạn đã chiến thắng", "Thông báo", JOptionPane.INFORMATION_MESSAGE, icon);
            
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(int t[][]) {
        matrix.setMatrix(t);
    }

}
