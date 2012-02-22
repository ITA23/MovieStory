package ita23.moviestory.view.popups;

import ita23.managerframework.skill.AbstractSkill;
import ita23.managerframework.staff.Employee;
import ita23.managerframework.staff.StaffManager;
import ita23.moviestory.model.staff.ImageEmployee;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * The pop-up showing the staff and all options available for it.
 * @author Lukas Knuth
 * @version 1.0
 */
public class StaffPopUp implements PopUp, ListSelectionListener{

    /** Shows the selected employees image. */
    private JLabel employee_image;
    /** Shows the level of the selected employee */
    private JLabel level;
    /** Shows the skills and their level */
    private JLabel skills;

    /** The list that holds all the staff */
    private final JList staff;

    public StaffPopUp(){
        staff = new JList();
    }

    @Override
    public JComponent getView() {
        final JPanel content = new JPanel(new BorderLayout());
        staff.addListSelectionListener(this);
        staff.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        staff.setLayoutOrientation(JList.VERTICAL);
        // Fill the List:
        DefaultListModel staff_model = new DefaultListModel();
        for (Employee employee : StaffManager.INSTANCE.getStaff())
            staff_model.addElement(employee.getName());
        staff.setModel(staff_model);
        // Add Staff-list:
        content.add(new JScrollPane(staff), BorderLayout.CENTER);
        // Employee-picture:
        employee_image = new JLabel();
        employee_image.setBorder(BorderFactory.createTitledBorder("Image"));
        content.add(employee_image, BorderLayout.WEST);
        // Employee stat-info's:
        final JPanel info_panel = new JPanel();
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.Y_AXIS));
        level = new JLabel();
        info_panel.add(level);
        skills = new JLabel();
        info_panel.add(skills);
        info_panel.setBorder(BorderFactory.createTitledBorder("Skills"));
        content.add(info_panel, BorderLayout.EAST);
        // All done, now select the first one:
        staff.setSelectedIndex(0);
        return content;
    }

    @Override
    public String getTitle() {
        return "Manage your Staff";
    }

    @Override
    public void popupClosed(CloseReason state) {
        System.out.println("Closed because: "+state);
    }

    /**
     * Called when an {@code Employee} was choosen from the staff-list.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Get the Employee:
        ImageEmployee employee = (ImageEmployee) StaffManager.INSTANCE.getEmployee(staff.getSelectedValue().toString());
        level.setText("Lvl: "+employee.getLevel());
        // Build the Level-String:
        StringBuilder builder = new StringBuilder(40);
        builder.append("<html><br>");
        for (AbstractSkill skill : employee.getSkills())
            builder.append("<p>"+skill.getName()+": "+skill.getCurrentLevel()+"</p>");
        builder.append("</html>");
        skills.setText(builder.toString());
        // Set the Image:
        employee_image.setIcon(employee.getImage());
    }
}
