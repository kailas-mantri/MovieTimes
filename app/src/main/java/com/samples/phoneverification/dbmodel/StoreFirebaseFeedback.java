package com.samples.phoneverification.dbmodel;

public class StoreFirebaseFeedback {

    private String email, timeStamp, feedback;

    public StoreFirebaseFeedback(String email, String timeStamp, String feedback) {
        this.email = email;
        this.timeStamp = timeStamp;
        this.feedback = feedback;
    }

    public StoreFirebaseFeedback() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
