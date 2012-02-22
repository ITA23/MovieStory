package ita23.moviestory.model.contract;

import ita23.managerframework.contract.Job;
import ita23.managerframework.contract.Requirement;
import ita23.managerframework.time.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple {@code Job}.</p>
 * To create an instance of this class, use the inner {@code Builder}-
 *  class.
 * @author Lukas Knuth
 * @version 1.0
 */
public class SimpleJob implements Job {

    private List<Requirement> reqs;
    private int payment;
    private String name;
    private Duration duration;

    /**
     * Creates a new instance - only called by the {@code Builder}.
     */
    private SimpleJob(String name, int payment, Duration duration,
                      List<Requirement> reqs){
        this.name = name;
        this.payment = payment;
        this.duration = duration;
        this.reqs = reqs;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public int getPayment() {
        return payment;
    }

    @Override
    public List<Requirement> getRequirements() {
        return reqs;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * The Builder to create a new instance of the {@code SimpleJob}.
     */
    public static final class Builder{
        
        private List<Requirement> reqs;
        private int payment;
        private String name;
        private Duration duration;

        /**
         * Create a new builder to create an {@code SimpleJob}-
         *  instance.
         */
        public Builder(){
            this.reqs = new ArrayList<Requirement>(4);
        }

        /**
         * Set the payment for the new job.
         * @param payment the payment.
         * @return this builder-instance.
         */
        public Builder setPayment(int payment){
            this.payment = payment;
            return this;
        }

        /**
         * Set the name for the new job.
         * @param name the new jobs name.
         * @return this builder-instance.
         */
        public Builder setName(String name){
            this.name = name;
            return this;
        }

        /**
         * Set the duration of the new job.
         * @param duration the new jobs duration.
         * @return this builder-instance.
         */
        public Builder setDuration(Duration duration){
            this.duration = duration;
            return this;
        }

        /**
         * Add a {@code Requirement} for this job.
         * @param req the new {@code Requirement}.
         * @return this builder-instance.
         */
        public Builder addRequirement(Requirement req){
            this.reqs.add(req);
            return this;
        }

        /**
         * Build a new {@code SimpleJob}-instance with the given
         *  information.
         * @return the new instance.
         * @throws IllegalArgumentException if not all information
         *  was set.
         */
        public SimpleJob build(){
            if (this.reqs.size() == 0 || this.name == null || 
                    this.duration == null || this.payment == 0)
                throw new IllegalArgumentException("Not enough information");
            return new SimpleJob(this.name, this.payment, this.duration, this.reqs);
        }
    }
}
