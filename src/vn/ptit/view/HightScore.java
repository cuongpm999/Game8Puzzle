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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import vn.ptit.controller.DAO;
import vn.ptit.model.Player;

/**
 *
 * @author Cuong Pham
 */
public class HightScore extends JPanel {

    private Image img;
    private ContainerPanel containerPanel;
    private DAO dao;
    private JLabel[][] jLabelPlayer;

    public HightScore(Image img, ContainerPanel containerPanel) {
        jLabelPlayer = new JLabel[9][3];
        dao = new DAO();
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
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

        List<Player> players = dao.getPlayers();
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getTimePlay() - o2.getTimePlay();
            }
        });

        JPanel listPlayer = new JPanel(new GridLayout(9, 3, 20, 20));

        Border compound = BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        Border redline = BorderFactory.createLineBorder(Color.red);
        compound = BorderFactory.createCompoundBorder(
                redline, compound);
        listPlayer.setBorder(compound);
        listPlayer.setBackground(Color.WHITE);

        Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
        if (players.size() < 9) {
            for (int i = 0; i < players.size(); i++) {
                jLabelPlayer[i][0] = new JLabel((i + 1) + ".");
                jLabelPlayer[i][0].setFont(font);
                listPlayer.add(jLabelPlayer[i][0]);

                jLabelPlayer[i][1] = new JLabel(players.get(i).getTen());
                jLabelPlayer[i][1].setFont(font);
                listPlayer.add(jLabelPlayer[i][1]);

                jLabelPlayer[i][2] = new JLabel(players.get(i).getThoi_gian());
                jLabelPlayer[i][2].setFont(font);
                listPlayer.add(jLabelPlayer[i][2]);
            }
        } else {
            for (int i = 0; i < 9; i++) {
                jLabelPlayer[i][0] = new JLabel((i + 1) + ".");
                jLabelPlayer[i][0].setFont(font);
                listPlayer.add(jLabelPlayer[i][0]);

                jLabelPlayer[i][1] = new JLabel(players.get(i).getTen());
                jLabelPlayer[i][1].setFont(font);
                listPlayer.add(jLabelPlayer[i][1]);

                jLabelPlayer[i][2] = new JLabel(players.get(i).getThoi_gian());
                jLabelPlayer[i][2].setFont(font);
                listPlayer.add(jLabelPlayer[i][2]);
            }
        }

        add(listPlayer, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 50;
        gbc.ipady = 5;
        JButton jButton = new JButton("Quay lại");
        jButton.setFont(font);
        jButton.setBackground(Color.decode("#00FFFF"));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                containerPanel.setShowMenu();
            }
        });
        add(jButton, gbc);

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
