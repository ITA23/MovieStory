package ita23.moviestory.model.contract;

import ita23.managerframework.contract.Requirement;

/**
 * A single {@code Requirement} for a {@code Job}.
 * @author Lukas Knuth
 * @version 1.0
 */
public class SimpleRequirement extends Requirement {
    
    /** The {@code Requirement}, matching the "Visual Effects"-skill */
    public static final String REQ_GRAPHICS = "Graphics";
    /** The {@code Requirement}, matching the "Sound Effects"-skill */
    public static final String REQ_SOUND = "Sound";
    /** The {@code Requirement}, matching the "Writing"-skill */
    public static final String REQ_CREATIVITY = "Creativity";
    /** The {@code Requirement}, matching the "Costumes"-skill */
    public static final String REQ_ENVIRONMENT = "Environment";
    
    private String name;
    private int req_points;
    private String matching_skill;

    /**
     * Creates a new simple {@code Requirement}.
     * @param name the name of the new requirement.
     * @param req_points the required points.
     * @param matching the matching {@code AbstractSkill}s name.
     */
    public SimpleRequirement(String name, int req_points, String matching){
        this.name = name;
        this.req_points = req_points;
        this.matching_skill = matching;
    }

    @Override
    public String getMatchingSkill() {
        return matching_skill;
    }

    @Override
    public int getRequiredPoints() {
        return req_points;
    }

    @Override
    public String getName() {
        return name;
    }
}
