package com.sagar.chrysalis2019.ui.Registration;

public class technicalTeam {
    private String contactNumber;
    private String frstPersonName;
    private String registrationEmail;
    private String secondPersonName;
    private String teamName;
    private String thirdPersonName;

    public technicalTeam(String contactNumber, String frstPersonName, String registrationEmail, String secondPersonName, String teamName, String thirdPersonName) {
        this.contactNumber = contactNumber;
        this.frstPersonName = frstPersonName;
        this.registrationEmail = registrationEmail;
        this.secondPersonName = secondPersonName;
        this.teamName = teamName;
        this.thirdPersonName = thirdPersonName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getFrstPersonName() {
        return frstPersonName;
    }

    public String getRegistrationEmail() {
        return registrationEmail;
    }

    public String getSecondPersonName() {
        return secondPersonName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getThirdPersonName() {
        return thirdPersonName;
    }
}
