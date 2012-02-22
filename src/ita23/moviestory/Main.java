package ita23.moviestory;

import ita23.managerframework.staff.Employee;
import ita23.managerframework.staff.StaffManager;
import ita23.moviestory.model.staff.ImageEmployee;
import ita23.moviestory.utilities.PathUtility;
import ita23.moviestory.view.GuiManager;

import javax.swing.*;
import java.io.File;

/**
 * Main entry-point for the program.
 * @author Lukas Knuth
 * @version 1.0
 */
class Main {

    // TODO Fix JavaDoc and put "author" + "version" below the class-discretion

    /**
     * Private constructor to make the class non-instantiable.</p>
     * Do the basic set-up and present the GUI.
     */
    private Main(){
        JFrame f = new JFrame("Movie Story");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(GuiManager.getInstance());
        f.pack();
        f.setVisible(true);
    }

    /**
     * This method only creates a couple of things to make the
     *  game playable.
     */
    private static void createTestEnvironment(){
        // Add some employees:
        StaffManager manager = StaffManager.INSTANCE;
        ImageEmployee.Builder employee_builder = new ImageEmployee.Builder();
        manager.hire(employee_builder
                .setGender(Employee.Gender.MALE)
                .setName("D. Furbach")
                .setSalary(200)
                .setImage(new ImageIcon(
                        new File(PathUtility.getBasePath(), "res/drawable/char2.png").toString()
                ))
        .build());
        manager.getEmployee("D. Furbach").increaseLevel();
        manager.hire(employee_builder
                .setGender(Employee.Gender.FEMALE)
                .setName("Anna Bolika")
                .setSalary(2000)
                .setImage(new ImageIcon(
                        new File(PathUtility.getBasePath(), "res/drawable/char3.png").toString()
                ))
        .build());
        manager.getEmployee("Anna Bolika").increaseLevel();
        manager.hire(employee_builder
                .setGender(Employee.Gender.MALE)
                .setName("Everyday Normal Guy")
                .setSalary(2000)
                .setImage(new ImageIcon(
                        new File(PathUtility.getBasePath(), "res/drawable/char1.png").toString()
                ))
        .build());
        manager.getEmployee("Everyday Normal Guy").increaseLevel();
        manager.hire(employee_builder
                .setGender(Employee.Gender.MALE)
                .setName("Big Earl")
                .setSalary(2000)
                .setImage(new ImageIcon(
                        new File(PathUtility.getBasePath(), "res/drawable/char4.png").toString()
                ))
        .build());
        manager.getEmployee("Big Earl").increaseLevel();
        // Start the SoundManager:
        //SoundManager soundManager = SoundManager.INSTANCE;
    }

    /**
     * Main-entry point of the program.
     * @param args arguments from the command-line.
     */
    public static void main(String[] args){
        // Call bootstrap here.
        createTestEnvironment();
        // Now, show game
        new Main();
    }
}
