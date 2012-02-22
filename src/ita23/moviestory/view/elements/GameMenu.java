package ita23.moviestory.view.elements;

import ita23.moviestory.view.GuiManager;
import ita23.moviestory.view.popups.ActivityPopUp;
import ita23.moviestory.view.popups.JobsPopUp;
import ita23.moviestory.view.popups.StaffPopUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class holds all buttons/swing elements for the game-menu.
 * @author Lukas Knuth
 * @version 1.0
 */
public class GameMenu extends JPanel implements ActionListener{

    /** The button to open the jobs-menu */
    private JButton jobs;
    /** The button to open the staff-menu */
    private JButton staff;
    /** The button to open the action-menu */
    private JButton action;
    /** Opens the info-menu. */
    private JButton info;
    /** Opens the system-menu */
    private JButton system;

    /**
     * Create the menu for the game.
     */
    public GameMenu(){
        super();
        setLayout(new GridLayout(0, 1));
        setUp();
    }

    /**
     * Do the basic set-up for the menu and add all buttons.
     */
    private void setUp(){
        jobs = createMenuButton("Jobs");
        jobs.addActionListener(this);
        add(jobs);
        staff = createMenuButton("Staff");
        staff.addActionListener(this);
        add(staff);
        action = createMenuButton("Action");
        action.addActionListener(this);
        add(action);
        info = createMenuButton("Info");
        info.addActionListener(this);
        add(info);
        system = createMenuButton("System");
        system.addActionListener(this);
        add(system);
        // Spacers (dirty...)
        add(new JLabel("\n\n\n\n\n\n"));
        add(new JLabel("\n\n\n\n\n\n"));
    }

    /**
     * Create a {@code JButton} with the style-guide for the Game-
     *  menu.
     * @param label the label for the new Button.
     * @return the button for the menu applied with the standard
     *  styles.
     */
    private JButton createMenuButton(String label){
        JButton button = new JButton(label);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jobs){
            GuiManager.getInstance().setPopupContent(new JobsPopUp());
        } else if (e.getSource() == staff){
            GuiManager.getInstance().setPopupContent(new StaffPopUp());
        } else if (e.getSource() == action){
            GuiManager.getInstance().setPopupContent(new ActivityPopUp());
        }
        else if (e.getSource() == info) System.out.println("Info...");
        else if (e.getSource() == system) System.out.println("System.");
    }
}
