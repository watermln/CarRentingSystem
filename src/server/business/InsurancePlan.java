package server.business;


public class InsurancePlan {
    private String planID;
    private String planName;
    private double coverageAmount;

    public InsurancePlan(String planID, String planName, double coverageAmount) {
        this.planID = planID;
        this.planName = planName;
        this.coverageAmount = coverageAmount;
    }

    public String getPlanID() {
        return planID;
    }

    public String getPlanName() {
        return planName;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }
}
