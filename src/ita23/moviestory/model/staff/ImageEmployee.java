package ita23.moviestory.model.staff;

import ita23.managerframework.skill.AbstractSkill;
import ita23.managerframework.staff.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an {@code Employee} with an Image attached
 *  to it.
 * @author Lukas Knuth
 * @version 1.0
 */
public class ImageEmployee extends Employee{

    /** The employees salary */
    private int salary;
    /** The employees name */
    private String name;
    /** The employees gender */
    private Employee.Gender gender;
    /** The {@code ImageIcon} associated with this {@code Employee}. */
    private ImageIcon image;
    
    /** The skills for this employee */
    private Map<String, SimpleSkill> skills;

    /**
     * Private constructor, called by the {@code Builder}-class to
     *  create a new instance.
     * @param salary the salary for the new {@code ImageEmployee}.
     * @param name the name for the new {@code ImageEmployee}.
     * @param gender the gender for the new {@code ImageEmployee}.
     * @param image the {@code ImageIcon} associated with this {@code ImageEmployee}.
     */
    private ImageEmployee(int salary, String name, Gender gender, ImageIcon image){
        this.salary = salary;
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.skills = new HashMap<String, SimpleSkill>(4);
        setUpSkills();
    }
    
    @Override
    public AbstractSkill getSkill(String s) {
        return skills.get(s);
    }

    @Override
    public List<AbstractSkill> getSkills() {
        // TODO Maybe cache this for memory purposes?
        return new ArrayList<AbstractSkill>(skills.values());
    }

    /**
     * Sets the skills common to every {@code ImageEmployee}.
     */
    private void setUpSkills(){
        skills.put(SimpleSkill.SKILL_WRITING,
                new SimpleSkill(SimpleSkill.SKILL_WRITING));
        skills.put(SimpleSkill.SKILL_COSTUMES,
                new SimpleSkill(SimpleSkill.SKILL_COSTUMES));
        skills.put(SimpleSkill.SKILL_EFFECTS,
                new SimpleSkill(SimpleSkill.SKILL_EFFECTS));
        skills.put(SimpleSkill.SKILL_SOUND,
                new SimpleSkill(SimpleSkill.SKILL_SOUND));
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Get the image associated with this {@code ImageEmployee} as a
     *  {@code ImageIcon}.
     * @return the image of this employee.
     */
    public ImageIcon getImage(){
        return this.image;
    }

    /**
     * @author Lukas Knuth
     * @version 1.0
     * This class implements the "builder-class"-pattern to build an instance
     *  of the {@code ImageEmployee}-class.</p>
     * All methods of this builder return the current instance, so calls to
     *  the setter-methods can be chained.
     */
    public static final class Builder{
        
        /** The gender of the new Employee */
        private Gender gender;
        /** The new employees salary. */
        private int salary;
        /** The new employees name */
        private String name;
        /** The new employees {@code ImageIcon} */
        private ImageIcon image;

        /**
         * Set the gender of the new {@code Employee}.
         * @param gender the new employees gender.
         * @return this builder-instance.
         */
        public Builder setGender(Gender gender){
            this.gender = gender;
            return this;
        }

        /**
         * Set the salary for the new {@code Employee}.
         * @param salary the new employees salary.
         * @return this builder-instance.
         */
        public Builder setSalary(int salary){
            this.salary = salary;
            return this;
        }

        /**
         * Set the name for the new {@code Employee}.
         * @param name the new employees name.
         * @return this builder-instance.
         */
        public Builder setName(String name){
            this.name = name;
            return this;
        }

        /**
         * Set the {@code ImageIcon} for this new {@code Employee}.</p>
         * This method will also cause the passed image to be resized.
         * @param image the {@code ImageIcon} for this employee.
         * @return this builder-instance.
         * @throws NullPointerException if {@code image} is {@code null}.
         */
        public Builder setImage(ImageIcon image){
            if (image == null) throw new NullPointerException("Image can't be null!");
            this.image = new ImageIcon(
                    image.getImage().getScaledInstance(90, 191, Image.SCALE_SMOOTH)
            );
            return this;
        }

        /**
         * Constructs a new {@code ImageEmployee}-instance with the
         *  given information.
         * @return the new {@code ImageEmployee}-instance.
         * @throws IllegalArgumentException if not all information
         *  was set.
         */
        public ImageEmployee build() {
            if (gender == null || salary == 0 || name == null || image == null)
                throw new IllegalArgumentException("Not all information is set.");
            return new ImageEmployee(this.salary, this.name, this.gender, this.image);
        }
        
    }
}
