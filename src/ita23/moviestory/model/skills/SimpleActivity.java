package ita23.moviestory.model.skills;

import ita23.managerframework.skill.AbstractActivity;
import ita23.managerframework.skill.AbstractSkill;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements a simple {@code AbstractActivity}.</p>
 * To create an instance of this class, use the inner
 *  {@code Builder}-class.
 * @author Lukas Knuth
 * @version 1.0
 */
public final class SimpleActivity implements AbstractActivity {
    
    private int price;
    private String name;
    private Map<String, Integer> skills;

    /**
     * Creates a simple activity (only called from the inner
     *  {@code Builder}-class).
     */
    private SimpleActivity(String name, int price,
                           Map<String, Integer> skills){
        this.price = price;
        this.name = name;
        this.skills = skills;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public int increaseSkillMax(AbstractSkill abstractSkill) {
        return skills.get(abstractSkill.getName());
    }

    /**
     * This builder is used to create an instance of the
     *  {@code SimpleActivity}-class.
     */
    public static final class Builder{
       
        private int price;
        private String name;
        private Map<String, Integer> skills;

        /**
         * Create a new Builder-instance.
         */
        public Builder(){
            this.skills = new HashMap<String, Integer>(4);
        }

        /**
         * Set the price for this Activity.
         * @param price the price.
         * @return this builder instance.
         */
        public Builder setPrice(int price){
            this.price = price;
            return this;
        }

        /**
         * Set the name for this Activity.
         * @param name this activity's name.
         * @return this builder instance.
         */
        public Builder setName(String name){
            this.name = name;
            return this;
        }

        /**
         * Set the maximum increment for a given skill.
         * @param skill the skill-name to increase.
         * @param increase the increment.
         * @return this builder instance.
         */
        public Builder addSkillIncrease(String skill, int increase){
            this.skills.put(skill, increase);
            return this;
        }

        /**
         * Create an instance of the {@code SimpleActivity}-class.
         * @return the new created instance.
         * @throws IllegalArgumentException if not all fields where
         *  set when {@code build()} was called.
         */
        public SimpleActivity build(){
            // Check values:
            if (this.name == null || this.price == 0 || this.skills.size() == 0)
                throw new IllegalArgumentException("Not enough information!");
            return new SimpleActivity(this.name, this.price, this.skills);
        }
       
    }
}
