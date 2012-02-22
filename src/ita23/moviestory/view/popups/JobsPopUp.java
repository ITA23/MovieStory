package ita23.moviestory.view.popups;

import ita23.managerframework.contract.CantTakeJobException;
import ita23.managerframework.contract.Job;
import ita23.managerframework.contract.JobManager;
import ita23.managerframework.time.Duration;
import ita23.moviestory.model.contract.SimpleJob;
import ita23.moviestory.model.contract.SimpleRequirement;
import ita23.moviestory.model.staff.SimpleSkill;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The PopUp which enables the user to pick a new Job to
 *  work on.
 * @author Lukas Knuth
 * @version 1.0
 */
public class JobsPopUp implements PopUp, ActionListener, ListSelectionListener {
    
    /** All currently available jobs */
    private List<Job> jobs;
    /** The {@code JList}, containing all jobs */
    private JList job_list;
    
    private JButton take_job;
    private JLabel payment;
    private JLabel time;
    
    public JobsPopUp(){
        jobs = new ArrayList<Job>(4);
        SimpleJob.Builder builder = new SimpleJob.Builder();
        jobs.add(builder
                .setName("Visual Effects")
                .setDuration(new Duration(1, 0, 0))
                .setPayment(30000)
                .addRequirement(new SimpleRequirement(
                        SimpleRequirement.REQ_SOUND, 20, SimpleSkill.SKILL_SOUND))
                .addRequirement(new SimpleRequirement(
                        SimpleRequirement.REQ_GRAPHICS, 30, SimpleSkill.SKILL_EFFECTS))
                .build()
        );
        jobs.add(builder
                .setName("Short scene")
                .setDuration(new Duration(0, 3, 0))
                .setPayment(99998)
                .addRequirement(new SimpleRequirement(
                        SimpleRequirement.REQ_CREATIVITY, 20, SimpleSkill.SKILL_WRITING))
                .addRequirement(new SimpleRequirement(
                        SimpleRequirement.REQ_ENVIRONMENT, 30, SimpleSkill.SKILL_COSTUMES))
                .build()
        );
    }
    
    @Override
    public JComponent getView() {
        final JPanel content = new JPanel(new BorderLayout());
        // Job List:
        job_list = new JList();
        job_list.addListSelectionListener(this);
        DefaultListModel model = new DefaultListModel();
        for (Job job : jobs)
            model.addElement(job.getName());
        job_list.setModel(model);
        content.add(new JScrollPane(job_list), BorderLayout.CENTER);
        // Take Job-button
        take_job = new JButton("Take the Job!");
        take_job.addActionListener(this);
        content.add(take_job, BorderLayout.SOUTH);
        // Job infos:
        final JPanel info_panel = new JPanel();
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.Y_AXIS));
        info_panel.setBorder(BorderFactory.createTitledBorder("Infos"));
        payment = new JLabel();
        time = new JLabel();
        info_panel.add(payment);
        info_panel.add(time);
        content.add(info_panel, BorderLayout.EAST);
        // Do the rest:
        job_list.setSelectedIndex(0);
        return content;
    }

    @Override
    public String getTitle() {
        return "Work on Contract.";
    }

    @Override
    public void popupClosed(CloseReason state) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Job job = jobs.get(job_list.getSelectedIndex());
        if (job != null)
            try {
                JobManager.INSTANCE.doJob(job);
                System.out.println("Job taken...");
            } catch (CantTakeJobException e1) {
                e1.printStackTrace();
            }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Job job = jobs.get(job_list.getSelectedIndex());
        payment.setText(job.getPayment()+" Geld");
        time.setText(job.getDuration().toString());
    }
}
