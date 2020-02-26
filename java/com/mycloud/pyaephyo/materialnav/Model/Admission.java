package com.mycloud.pyaephyo.materialnav.Model;

/**
 * Created by BarbieStyles on 10/25/2016.
 */

public class Admission
{
    int _id;
    String uniId, admissionType, testDescription;

    public Admission(int _id, String uniId, String admissionType, String testDescription) {
        this._id = _id;
        this.uniId = uniId;
        this.admissionType = admissionType;
        this.testDescription = testDescription;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUniId() {
        return uniId;
    }

    public void setUniId(String uniId) {
        this.uniId = uniId;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }
}
