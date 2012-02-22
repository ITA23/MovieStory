package ita23.moviestory.view;

import ita23.managerframework.contract.JobManager;
import ita23.managerframework.contract.JobStatusChangedListener;
import ita23.moviestory.sound.SoundManager;
import ita23.moviestory.view.elements.GameMenu;
import ita23.moviestory.view.popups.PopUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Holds all the Swing-elements and layouts.</p>
 * This class implement's the Singleton-pattern to expose mothods
 *  for GUI-control to all parts of the code.
 * @author Lukas Knuth
 * @version 1.0
 */
public class GuiManager extends JPanel implements ActionListener, JobStatusChangedListener {
    
    /** The instance to use */
    private static GuiManager INSTANCE;

   /** The default width of the game-window */
    public static final int WINDOW_WIDTH = 500;
    /** The default height of the game-window */
    public static final int WINDOW_HEIGHT = 350;
    /** The preferred size as a {@code Dimension}. */
    private static final Dimension PREF_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);

    /** The button to open the menu */
    private JButton open_menu;
    /** The close-button for the popup */
    private JButton close_popup;
    /** The menu for the game */
    private GameMenu menu;
    /** The panel for all pop-ups */
    private JPanel popup;
    /** The panel holding the content of the popup. */
    private JPanel popup_content;
    
    /** The current {@code PopUp}, which is displayed on the GUI */
    private PopUp current_popup_content;

    /**
     * Creates a new instance of the {@code GuiManager}.
     */
    private GuiManager(){
        super(new BorderLayout());
        this.setPreferredSize(PREF_SIZE);
        setUp();
        // Add Job Listener
        JobManager.INSTANCE.addJobStatusChangedListener(this);
    }

    /**
     * Get the instance of the singleton to do work with it.
     * @return the instance to work with.
     */
    public static GuiManager getInstance(){
        if (INSTANCE == null) INSTANCE = new GuiManager();
        return INSTANCE;
    }

    /**
     * This method is used to set the contents of the PopUp
     *  on the GUI.</p>
     * Setting the content of the popup will also cause the
     *  old {@code PopUp} to be notified that it was closed.</p>
     * As a shortcut, setting new contents for the popup will
     *  make the popup visible.
     * @param content the {@code PopUp} that should be displayed.
     */
    public void setPopupContent(PopUp content){
        // Notify old PopUp:
        if (current_popup_content != null)
            current_popup_content.popupClosed(PopUp.CloseReason.OVERLAPPED);
        // Clean up:
        popup_content.removeAll();
        // Add
        popup_content.add(content.getView());
        popup.setBorder(BorderFactory.createTitledBorder(content.getTitle()));
        popup_content.validate();
        setPopupVisibility(true);
        // Set the new popup:
        this.current_popup_content = content;
    }

    /**
     * Sets the visibility of the popup.</p>
     * Setting the visibility to {@code true} will also cause the
     *  game-menu to hide and the menu-button to be disabled.</p>
     * When setting the visibility to {@code false}, this will also
     *  cause the current {@code PopUp} to be notified that it was
     *  closed by the user.
     * @param status the visibility-state of the popup.
     */
    public void setPopupVisibility(boolean status){
        if (status){
            popup.setVisible(true);
            menu.setVisible(false);
            open_menu.setEnabled(false);
        } else {
            open_menu.setEnabled(true);
            popup.setVisible(status);
            // Notify:
            if (current_popup_content != null)
                current_popup_content.popupClosed(PopUp.CloseReason.USER_CLOSED);
        }
    }

    /**
     * Do the basic set-up for the game-screen and get the
     *  menu.
     */
    private void setUp(){
        // The game-menu:
        menu = new GameMenu();
        menu.setVisible(false);
        add(menu, BorderLayout.WEST);
        // The button to open the menu:
        open_menu = new JButton("Menu");
        open_menu.addActionListener(this);
        add(open_menu, BorderLayout.SOUTH);
        // The popup
        popup = new JPanel(new BorderLayout());
        popup.setBorder(BorderFactory.createTitledBorder("LOL!"));
        popup.setVisible(false);
        add(popup, BorderLayout.CENTER);
        popup_content = new JPanel();
        popup.add(popup_content, BorderLayout.CENTER);
        // The "close"-button on the popup
        close_popup = new JButton("Close");
        close_popup.addActionListener(this);
        popup.add(close_popup, BorderLayout.SOUTH);
    }

    /**
     * Toggles the visibility of the game-menu.
     */
    private void toggleMenu(){
        if (menu.isVisible())
            menu.setVisible(false);
        else menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open_menu) toggleMenu();
        else if (e.getSource() == close_popup) setPopupVisibility(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        // TODO Animation, Buffer, etz
        g.setColor(Color.BLACK);
        g.drawString("Hier dann Zeug...", 130, 80);
        SoundManager.INSTANCE.doBackGroundSound();

    }

    @Override
    public void jobStatusChanged(JobManager.JobStatus jobStatus) {
        System.out.println("The job ended because: "+jobStatus);
    }
}
