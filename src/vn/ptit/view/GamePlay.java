/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import vn.ptit.controller.AStar;
import vn.ptit.controller.BFS;
import vn.ptit.model.EmptyCellPoint;
import vn.ptit.model.Matrix;
import vn.ptit.model.State;

/**
 *
 * @author Cuong Pham
 */
public class GamePlay extends JPanel
        implements ActionListener {

    private JPanelImg jPanelImg;
    private BFS bfs;
    private JButton[][] btn;
    private JPanel mainPanel;
    private JButton btnNewGame, btnBFS, btnDisplay, btnA, btnExitToMenu;
    private JLabel jLabelCostBFS, jLabelTimeCalBFS, jLabelCostA, jLabelTimeCalA, jLabelStepBFS, jLabelStepA, jLabelTimeCount;
    private Stack<State> st;
    private JList<String> jListBFS;
    private JList<String> jListA;
    private DefaultListModel modelBFS;
    private DefaultListModel modelA;
    private AStar aStar;
    private Map<Integer, List<Integer>> mapBFS;
    private Map<Integer, List<Integer>> mapA;
    private int giay = 0;
    private int phut = 0;
    private TimeCount count;
    
    private String namePlayer;
    private String nameImg;
    
    private ContainerPanel containerPanel;
    
    private MainFrame frame;
    
    public GamePlay(String namePlayer,String nameImg,ContainerPanel containerPanel,MainFrame frame) {
        this.namePlayer=namePlayer;
        this.nameImg=nameImg;
        this.frame=frame;
        this.containerPanel=containerPanel;
        
        modelBFS = new DefaultListModel();
        modelA = new DefaultListModel();
        jListBFS = new JList(modelBFS);
        jListA = new JList<>(modelA);

        jListA.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                jListAKeyReleased();
            }
        });
        jListBFS.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                jListBFSKeyReleased();
            }
        });

        jListBFS.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jListBFSMouseClicked();
            }
        });
        jListA.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jListAMouseClicked();
            }
        });

        modelBFS.add(0, "Bắt đầu");
        modelA.add(0, "Bắt đầu");
        bfs = new BFS();
        aStar = new AStar();
        btn = new JButton[3][3];
        st = new Stack<>();
        mapBFS = new HashMap<>();
        mapA = new HashMap<>();

        btnDisplay = new JButton("Hiển thị");
        btnNewGame = new JButton("Chơi mới");
        btnBFS = new JButton("BFS");
        btnA = new JButton("A*");
        btnExitToMenu = new JButton("Quay lại menu");

        jLabelCostBFS = new JLabel("Chi phí: ");
        jLabelTimeCalBFS = new JLabel("Thời gian giải: ");
        jLabelStepBFS = new JLabel("Số lần duyệt: ");

        jLabelCostA = new JLabel("Chi phí: ");
        jLabelTimeCalA = new JLabel("Thời gian giải: ");
        jLabelStepA = new JLabel("Số lần duyệt: ");
        jLabelTimeCount = new JLabel("00 : 00", (int) CENTER_ALIGNMENT);
 
        count = new TimeCount();
        count.start();
        setLayout(new BorderLayout(10,10));
        add(createGraphicsPanel(), BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.EAST);

    }

    private JPanel createGraphicsPanel() {
        jPanelImg = new JPanelImg(this,frame);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.gray);
        panel.add(jPanelImg);
        return panel;
    }

    private Icon getIcon(int index) {
        int width = 250, height = 250;
        Image image = new ImageIcon(getClass().getResource(
                "/img/"+nameImg+"-" + index + ".jpg")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(width, height,
                image.SCALE_SMOOTH));
        return icon;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBFS) {
            List<Integer> list = new ArrayList<>();
            Matrix t = jPanelImg.getMatrix();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    list.add(t.getMatrix()[i][j]);

                }
            }

            State state = new State(list, 0, 0, null, "Bắt đầu");
            System.out.println(list);
            long timeCal = System.nanoTime();
            bfs.BFS(state);
            timeCal = System.nanoTime() - timeCal;
            DecimalFormat formatter = new DecimalFormat("#0.0000");
            jLabelTimeCalBFS.setText("Thời gian giải: " + formatter.format((double) timeCal / 1000000000) + " s");
            jLabelStepBFS.setText("Số lần duyệt: " + bfs.getSoLanDuyet());
            if (bfs.getSoBuoc() != null) {
                jLabelCostBFS.setText("Chi phí: " + bfs.getSoBuoc());
                Stack<State> stackS = new Stack<>();
                stackS.addAll(bfs.getSt());
                List<String> listDi = new ArrayList<>();
                int im = 0;
                while (!stackS.empty()) {
                    listDi.add(stackS.peek().getDirection());
                    mapBFS.put(im, stackS.peek().getList());
                    im++;
                    stackS.pop();
                }
                for (int i = 1; i < listDi.size(); i++) {
                    modelBFS.add(i, listDi.get(i));
                }

                st = bfs.getSt();

            } else {
                jLabelCostBFS.setText("Chi phí: null");
            }

        }

        if (e.getSource() == btnA) {
            List<Integer> list = new ArrayList<>();
            Matrix t = jPanelImg.getMatrix();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    list.add(t.getMatrix()[i][j]);

                }
            }

            State state = new State(list, 0, aStar.calHeuristic(list, 0), null, "Bắt đầu");
            System.out.println(list);
            long timeCal = System.nanoTime();
            aStar.BFS(state);
            timeCal = System.nanoTime() - timeCal;
            DecimalFormat formatter = new DecimalFormat("#0.0000");
            jLabelTimeCalA.setText("Thời gian giải: " + formatter.format((double) timeCal / 1000000000) + " s");
            jLabelStepA.setText("Số lần duyệt: " + aStar.getSoLanDuyet());
            if (aStar.getSoBuoc() != null) {
                jLabelCostA.setText("Chi phí: " + aStar.getSoBuoc());
                Stack<State> stackS = new Stack<>();
                stackS.addAll(aStar.getStState());
                List<String> listDi = new ArrayList<>();
                int im = 0;
                while (!stackS.empty()) {
                    listDi.add(stackS.peek().getDirection());
                    mapA.put(im, stackS.peek().getList());
                    im++;
                    stackS.pop();
                }
                for (int i = 1; i < listDi.size(); i++) {
                    modelA.add(i, listDi.get(i));
                }

                st = aStar.getStState();

            } else {
                jLabelCostA.setText("Chi phí: null");
            }

        }

        if (e.getSource() == btnDisplay) {
            RunSlow runSlow = new RunSlow();
            runSlow.start();
            
            count.suspend();

        }

        if (e.getSource() == btnNewGame) {
            jPanelImg.newGame();
            st.clear();

            // Content o BFS
            jLabelTimeCalBFS.setText("Thời gian giải: ");
            jLabelCostBFS.setText("Chi phí: ");
            jLabelStepBFS.setText("Số lần duyệt: ");
            modelBFS.removeAllElements();
            modelBFS.add(0, "Bắt đầu");
            bfs.setSt();
            bfs.setSoBuoc(null);
            bfs.setSoLanDuyet(0);
            mapBFS.clear();

            // Content o A*
            jLabelTimeCalA.setText("Thời gian giải: ");
            jLabelCostA.setText("Chi phí: ");
            jLabelStepA.setText("Số lần duyệt: ");
            modelA.removeAllElements();
            modelA.add(0, "Bắt đầu");
            aStar.setStState();
            aStar.setSoBuoc(null);
            aStar.setSoLanDuyet(0);
            mapA.clear();
            
            giay = 0;
            phut = 0;
            count.resume();
        }
        
        if (e.getSource() == btnExitToMenu) {
            containerPanel.setShowMenu();
        }

    }

    private JPanel createControlPanel() {
        // Anh goc
        JPanel panelImgOrigin = new JPanel(new BorderLayout(10, 5));
        Image image = new ImageIcon(getClass().getResource(
                "/img/"+nameImg+"-anhgoc.jpg")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(400, 400,
                image.SCALE_SMOOTH));
        JLabel jLabelImg = new JLabel(icon);

        panelImgOrigin.add(jLabelImg);

        // Phan con lai
        JPanel panelContentBtn = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel panelContent = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel panelBtnAI = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel panelBtn = new JPanel(new GridLayout(3, 1, 5, 5));
        JPanel panelDirection = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel panelDirectionBFS = new JPanel(new BorderLayout());
        JPanel panelDirectionA = new JPanel(new BorderLayout());
        JPanel panelTimeCount = new JPanel(new BorderLayout());
        JPanel panelExitAndNewGame = new JPanel(new GridLayout(1, 2, 5, 5));
        
        panelTimeCount.setBorder(new TitledBorder("Thời gian chơi"));

        jLabelTimeCount.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        panelTimeCount.add(jLabelTimeCount);
        
        Font fontContent = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        
        JPanel panelContentBFS = new JPanel(new GridLayout(3, 1, 5, 5));
        panelContentBFS.setBorder(new TitledBorder("Theo BFS"));
        
        jLabelTimeCalBFS.setFont(fontContent);
        jLabelStepBFS.setFont(fontContent);
        jLabelCostBFS.setFont(fontContent);
        
        panelContentBFS.add(jLabelTimeCalBFS);
        panelContentBFS.add(jLabelStepBFS);
        panelContentBFS.add(jLabelCostBFS);

        JPanel panelContentAStar = new JPanel(new GridLayout(3, 1, 5, 5));
        panelContentAStar.setBorder(new TitledBorder("Theo A*"));
        
        jLabelTimeCalA.setFont(fontContent);
        jLabelStepA.setFont(fontContent);
        jLabelCostA.setFont(fontContent);
        
        panelContentAStar.add(jLabelTimeCalA);
        panelContentAStar.add(jLabelStepA);
        panelContentAStar.add(jLabelCostA);

        btnBFS.addActionListener(this);
        btnBFS.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        btnA.addActionListener(this);
        btnA.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        
        btnA.setBackground(Color.decode("#00FFFF"));
        btnBFS.setBackground(Color.decode("#00FFFF"));
        
        panelBtnAI.add(btnBFS);
        panelBtnAI.add(btnA);

        btnDisplay.addActionListener(this);
        btnNewGame.addActionListener(this);
        btnExitToMenu.addActionListener(this);
        
        btnDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        btnNewGame.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        btnExitToMenu.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        
        btnNewGame.setBackground(Color.decode("#00FFFF"));
        btnDisplay.setBackground(Color.decode("#00FFFF"));
        btnExitToMenu.setBackground(Color.decode("#00FFFF"));
        
        panelBtn.add(panelBtnAI);
        panelBtn.add(btnDisplay);
        
        panelExitAndNewGame.add(btnNewGame);
        panelExitAndNewGame.add(btnExitToMenu);
        panelBtn.add(panelExitAndNewGame);

        panelDirectionBFS.setBorder(new TitledBorder("Hướng đi theo BFS"));
        panelDirectionA.setBorder(new TitledBorder("Hướng đi theo A*"));

        panelDirectionBFS.add(new JScrollPane(jListBFS));
        panelDirection.add(panelDirectionBFS);
        panelDirectionA.add(new JScrollPane(jListA));
        panelDirection.add(panelDirectionA);

        panelContent.add(panelContentBFS);
        panelContent.add(panelContentAStar);
        panelContentBtn.add(panelTimeCount);
        panelContentBtn.add(panelContent);
        panelContentBtn.add(panelDirection);
        panelContentBtn.add(panelBtn);

        // Dieu khien
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(new TitledBorder("Điều khiển"));
        panel.add(panelImgOrigin);
        panel.add(panelContentBtn);

        return panel;
    }

    private void jListAKeyReleased() {
        if (!mapA.isEmpty()) {
            List<Integer> listA = new ArrayList<>();
            listA = mapA.get(jListA.getSelectedIndex());
            int intdex = 0;
            int t[][] = new int[9][9];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    t[i][j] = listA.get(intdex);
                    intdex++;
                }
            }

            jPanelImg.setMatrix(t);
            jPanelImg.deleteAllBtn();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j] = new JButton();
                    Icon icon = getIcon(jPanelImg.getMatrix().getMatrix()[i][j]);
                    btn[i][j].setIcon(icon);
                    btn[i][j].addKeyListener(new KeyAdapter() {
                        public void keyReleased(KeyEvent e) {
                            jPanelImg.btnKeyReleased(e);
                        }
                    });
                    jPanelImg.add(btn[i][j]);
                }
            }

            frame.setVisible(true);
        }
    }

    private void jListBFSKeyReleased() {
        if (!mapBFS.isEmpty()) {
            List<Integer> listBFS = new ArrayList<>();
            listBFS = mapBFS.get(jListBFS.getSelectedIndex());
            int intdex = 0;
            int t[][] = new int[9][9];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    t[i][j] = listBFS.get(intdex);
                    intdex++;
                }
            }

            jPanelImg.setMatrix(t);
            jPanelImg.deleteAllBtn();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j] = new JButton();
                    Icon icon = getIcon(jPanelImg.getMatrix().getMatrix()[i][j]);
                    btn[i][j].setIcon(icon);
                    btn[i][j].addKeyListener(new KeyAdapter() {
                        public void keyReleased(KeyEvent e) {
                            jPanelImg.btnKeyReleased(e);
                        }
                    });
                    jPanelImg.add(btn[i][j]);
                }
            }

            frame.setVisible(true);
        }

    }

    private void jListBFSMouseClicked() {
        if (!mapBFS.isEmpty()) {
            List<Integer> listBFS = new ArrayList<>();
            listBFS = mapBFS.get(jListBFS.getSelectedIndex());
            int intdex = 0;
            int t[][] = new int[9][9];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    t[i][j] = listBFS.get(intdex);
                    intdex++;
                }
            }

            jPanelImg.setMatrix(t);
            jPanelImg.deleteAllBtn();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j] = new JButton();
                    Icon icon = getIcon(jPanelImg.getMatrix().getMatrix()[i][j]);
                    btn[i][j].setIcon(icon);
                    btn[i][j].addKeyListener(new KeyAdapter() {
                        public void keyReleased(KeyEvent e) {
                            jPanelImg.btnKeyReleased(e);
                        }
                    });
                    jPanelImg.add(btn[i][j]);
                }
            }

            frame.setVisible(true);

        }
    }

    private void jListAMouseClicked() {
        if (!mapA.isEmpty()) {
            List<Integer> listA = new ArrayList<>();
            listA = mapA.get(jListA.getSelectedIndex());
            int intdex = 0;
            int t[][] = new int[9][9];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    t[i][j] = listA.get(intdex);
                    intdex++;
                }
            }

            jPanelImg.setMatrix(t);
            jPanelImg.deleteAllBtn();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btn[i][j] = new JButton();
                    Icon icon = getIcon(jPanelImg.getMatrix().getMatrix()[i][j]);
                    btn[i][j].setIcon(icon);
                    btn[i][j].addKeyListener(new KeyAdapter() {
                        public void keyReleased(KeyEvent e) {
                            jPanelImg.btnKeyReleased(e);
                        }
                    });
                    jPanelImg.add(btn[i][j]);
                }
            }

            frame.setVisible(true);
        }
    }

    class RunSlow extends Thread {
        @Override
        public void run() {
            List<Integer> list = new ArrayList<>();
            while (!st.empty()) {
                try {
                    sleep(200);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                list = st.peek().getList();
                bfs.in(list);
                jPanelImg.deleteAllBtn();
                int intdex = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        btn[i][j] = new JButton();
                        Icon icon = getIcon(list.get(intdex));
                        btn[i][j].setIcon(icon);
                        jPanelImg.add(btn[i][j]);
                        intdex++;
                    }
                }

                frame.setVisible(true);
                st.pop();
            }
        }

    }

    class TimeCount extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                giay += 1;
                if (giay == 60) {
                    giay = 0;
                    phut += 1;
                }
                if (phut >= 10 && giay >= 10) {
                    jLabelTimeCount.setText(phut + " : " + giay);
                } else if (phut >= 10 && giay < 10) {
                    jLabelTimeCount.setText(phut + " : 0" + giay);
                } else if (phut < 10 && giay >= 10) {
                    jLabelTimeCount.setText("0" + phut + " : " + giay);
                } else if (phut < 10 && giay < 10) {
                    jLabelTimeCount.setText("0" + phut + " : 0" + giay);
                }
            }
        }

    }

    public TimeCount getCount() {
        return count;
    }

    public void setCount(TimeCount count) {
        this.count = count;
    }

    public JLabel getjLabelTimeCount() {
        return jLabelTimeCount;
    }

    public void setjLabelTimeCount(JLabel jLabelTimeCount) {
        this.jLabelTimeCount = jLabelTimeCount;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getNameImg() {
        return nameImg;
    }

    public void setNameImg(String nameImg) {
        this.nameImg = nameImg;
    }  

}
