package ita23.moviestory.view.popups;

import ita23.moviestory.model.skills.SimpleActivity;
import ita23.moviestory.model.staff.SimpleSkill;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This popup offers a list of {@code AbstractActivity}s
 *  that the user can use to train his staff.
 * @author Lukas Knuth
 * @version 1.0
 */
public class ActivityPopUp implements PopUp{

    private List<SimpleActivity> activitys;
    
    public ActivityPopUp(){
        activitys = new ArrayList<SimpleActivity>();
        SimpleActivity.Builder builder = new SimpleActivity.Builder();
        activitys.add(builder.setName("Chillen")
                .setPrice(200)
                .addSkillIncrease(SimpleSkill.SKILL_COSTUMES, 5)
                .addSkillIncrease(SimpleSkill.SKILL_EFFECTS, 8)
                .addSkillIncrease(SimpleSkill.SKILL_SOUND, 10)
                .addSkillIncrease(SimpleSkill.SKILL_WRITING, 20)
        .build());
        activitys.add(builder.setName("Laufen")
                .setPrice(100)
                .addSkillIncrease(SimpleSkill.SKILL_COSTUMES, 0)
                .addSkillIncrease(SimpleSkill.SKILL_EFFECTS, 0)
                .addSkillIncrease(SimpleSkill.SKILL_SOUND, 30)
                .addSkillIncrease(SimpleSkill.SKILL_WRITING, 30)
        .build());
    }

    @Override
    public boolean open() {
        // TODO get an Employee here.
        return true;
    }

    @Override
    public Object openForResult() {
        return null;
    }

    @Override
    public JComponent getView() {
        final JList activity_list = new JList();
        DefaultListModel model = new DefaultListModel();
        for (SimpleActivity activity : activitys)
            model.addElement(activity.getName());
        activity_list.setModel(model);
        return new JScrollPane(activity_list);
    }

    @Override
    public String getTitle() {
        return "Train your Staff";
    }

    @Override
    public void popupClosed(CloseReason state) {
    }
}
