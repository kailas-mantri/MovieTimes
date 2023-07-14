package com.samples.phoneverification.apimodel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CastCrewArray implements Serializable {

    @SerializedName("adult")
    public boolean isAdult;

    @SerializedName("gender")
    public int genderValue;

    @SerializedName("id")
    public String personId;

    @SerializedName("known_for_department")
    public String departmentName;

    @SerializedName("name")
    public String realName;

    @SerializedName("original_name")
    public String originalRealName;

    @SerializedName("popularity")
    public float personPopularity;

    @SerializedName("profile_path")
    public String personProfile_path;

    @SerializedName("credit_id")
    public String cast_credit_id;

    //Cast Array changes
    @SerializedName("cast_id")
    public Integer cast_id;

    @SerializedName("character")
    public String rollName;

    @SerializedName("order")
    public int cast_order;

    //Crew Array changes
    @SerializedName("department")
    public String crewDepartment;

    @SerializedName("job")
    public String crewJob;

    public CastCrewArray() {
    }

    public CastCrewArray(boolean isAdult, int genderValue, String personId, String departmentName, String realName, String originalRealName, float personPopularity,
                         String personProfile_path, String cast_credit_id, Integer cast_id, String rollName, int cast_order, String crewDepartment, String crewJob) {
        this.isAdult = isAdult;
        this.genderValue = genderValue;
        this.personId = personId;
        this.departmentName = departmentName;
        this.realName = realName;
        this.originalRealName = originalRealName;
        this.personPopularity = personPopularity;
        this.personProfile_path = personProfile_path;
        this.cast_credit_id = cast_credit_id;
        this.cast_id = cast_id;
        this.rollName = rollName;
        this.cast_order = cast_order;
        this.crewDepartment = crewDepartment;
        this.crewJob = crewJob;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public int getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(int genderValue) {
        this.genderValue = genderValue;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOriginalRealName() {
        return originalRealName;
    }

    public void setOriginalRealName(String originalRealName) {
        this.originalRealName = originalRealName;
    }

    public float getPersonPopularity() {
        return personPopularity;
    }

    public void setPersonPopularity(float personPopularity) {
        this.personPopularity = personPopularity;
    }

    public String getPersonProfile_path() {
        return personProfile_path;
    }

    public void setPersonProfile_path(String personProfile_path) {
        this.personProfile_path = personProfile_path;
    }

    public String getCast_credit_id() {
        return cast_credit_id;
    }

    public void setCast_credit_id(String cast_credit_id) {
        this.cast_credit_id = cast_credit_id;
    }

    public Integer getCast_id() {
        return cast_id;
    }

    public void setCast_id(Integer cast_id) {
        this.cast_id = cast_id;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public int getCast_order() {
        return cast_order;
    }

    public void setCast_order(int cast_order) {
        this.cast_order = cast_order;
    }

    public String getCrewDepartment() {
        return crewDepartment;
    }

    public void setCrewDepartment(String crewDepartment) {
        this.crewDepartment = crewDepartment;
    }

    public String getCrewJob() {
        return crewJob;
    }

    public void setCrewJob(String crewJob) {
        this.crewJob = crewJob;
    }

    @NonNull
    @Override
    public String toString() {
        return "CastCrewArray{" +
                "isAdult=" + isAdult +
                ", genderValue=" + genderValue +
                ", personId='" + personId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", realName='" + realName + '\'' +
                ", originalRealName='" + originalRealName + '\'' +
                ", personPopularity=" + personPopularity +
                ", personProfile_path='" + personProfile_path + '\'' +
                ", cast_credit_id='" + cast_credit_id + '\'' +
                ", cast_id=" + cast_id +
                ", rollName='" + rollName + '\'' +
                ", cast_order=" + cast_order +
                ", crewDepartment='" + crewDepartment + '\'' +
                ", crewJob='" + crewJob + '\'' +
                '}';
    }
}
