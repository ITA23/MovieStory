package ita23.moviestory.model.staff;

import ita23.managerframework.skill.AbstractSkill;

/**
 * A simple {@code AbstractSkill}-implementation to create
 *  a single skill for an {@code Employee}.
 * @author Lukas Knuth
 * @version 1.0
 */
public class SimpleSkill extends AbstractSkill {

    /** The name of the skill associated with "writing" */
    public static final String SKILL_WRITING = "Writing";
    /** The name of the skill associated with "visual effects" */
    public static final String SKILL_EFFECTS = "Visual Effects";
    /** The name of the skill associated with "sound effects" */
    public static final String SKILL_SOUND = "Sound Effects";
    /** The name of the skill associated with "costumes" */
    public static final String SKILL_COSTUMES = "Costumes";
    
    /** The name of this skill */
    private String name;

    /**
     * Creates a new {@code SimpleSkill} with the given name.</p>
     * The constructor is package-private so that only the
     *  {@code ImageEmployee}-class can create instances.
     * @param name
     */
    SimpleSkill(String name){
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
}
