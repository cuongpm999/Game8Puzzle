/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.view;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Cuong Pham
 */
public class ContainerPanel extends JPanel {

    private CardLayout jCardLayout;
    private static final String TAG_MENU = "tag_menu";
    private static final String TAG_PLAYGAME = "tag_playgame";
    private static final String TAG_HIGHTSCORE = "tag_hightscore";
    private static final String TAG_LOADING = "tag_loading";
    private static final String TAG_TUTORIAL = "tag_tutorial";
    private static final String TAG_SCREEN = "tag_screen";

    private MainFrame frame;
    private BackgroundLoading backgroundLoading;
    private JPanelMenu jPanelMenu;
    private SelectScreen selectScreen;
    private HightScore hightScore;
    private Tutorial tutorial;

    public ContainerPanel(MainFrame frame) {
        this.frame = frame;

        jCardLayout = new CardLayout();
        setLayout(jCardLayout);

        backgroundLoading = new BackgroundLoading(new ImageIcon(getClass()
                .getResource("/img/loading.jpg")).getImage(), this);
        jPanelMenu = new JPanelMenu(new ImageIcon(getClass()
                .getResource("/img/menu.jpg")).getImage(), this);
        selectScreen = new SelectScreen(new ImageIcon(getClass()
                .getResource("/img/selectscreen.jpg")).getImage(), this, frame);
        hightScore = new HightScore(new ImageIcon(getClass()
                .getResource("/img/selectscreen.jpg")).getImage(), this);
        tutorial = new Tutorial(new ImageIcon(getClass()
                .getResource("/img/selectscreen.jpg")).getImage(), this);

        add(backgroundLoading, TAG_LOADING);
        add(jPanelMenu, TAG_MENU);
        add(selectScreen, TAG_SCREEN);
        add(hightScore, TAG_HIGHTSCORE);
        add(tutorial, TAG_TUTORIAL);
        setShowLoading();
    }

    public void setShowMenu() {
        jCardLayout.show(this, TAG_MENU);

    }

    public void setShowPlay() {
        jCardLayout.show(this, TAG_PLAYGAME);

    }

    public void setShowLoading() {
        jCardLayout.show(this, TAG_LOADING);
    }

    public void setShowScreen() {
        jCardLayout.show(this, TAG_SCREEN);
    }

    public void setShowHightScore() {
        jCardLayout.show(this, TAG_HIGHTSCORE);
    }

    public void setShowTutorial() {
        jCardLayout.show(this, TAG_TUTORIAL);
    }

}
