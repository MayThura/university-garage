package com.mycloud.pyaephyo.materialnav.Model;

import java.io.Serializable;

public class UniversityInfo implements Serializable
{
    int _id;
    String uniId, uniName, uniLocation, uniCost, uniUniform, uniPhone, uniEmail, uniType, uniWebsite,uniMark, uniRegion, uniLat, uniLng;
    int boyMark,girlMark;
    String isFavorite;
    byte[] uniPhoto;

    public int getBoyMark() {
        return boyMark;
    }

    public int getGirlMark() {
        return girlMark;
    }

    public void setFavorite(String favorite) {
        isFavorite = favorite;
    }

    public String isFavorite() {

        return isFavorite;
    }

    public UniversityInfo(int _id, String uniId, String uniName, String uniLocation, String uniCost, String uniUniform, String uniPhone, String uniEmail, String uniType, String uniWebsite, String uniMark, String uniRegion, String uniLat, String uniLng, byte[] uniPhoto, int boyMark, int girlMark, String isFavorite) {
        this._id = _id;
        this.uniId = uniId;
        this.uniName = uniName;
        this.uniLocation = uniLocation;
        this.uniCost = uniCost;
        this.uniUniform = uniUniform;
        this.uniPhone = uniPhone;
        this.uniEmail = uniEmail;
        this.uniType = uniType;
        this.uniWebsite = uniWebsite;
        this.uniMark = uniMark;
        this.uniRegion = uniRegion;
        this.uniLat = uniLat;
        this.uniLng = uniLng;
        this.uniPhoto = uniPhoto;
        this.boyMark=boyMark;
        this.girlMark=girlMark;
        this.isFavorite=isFavorite;
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

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniLocation() {
        return uniLocation;
    }

    public void setUniLocation(String uniLocation) {
        this.uniLocation = uniLocation;
    }

    public String getUniCost() {
        return uniCost;
    }

    public void setUniCost(String uniCost) {
        this.uniCost = uniCost;
    }

    public String getUniUniform() {
        return uniUniform;
    }

    public void setUniUniform(String uniUniform) {
        this.uniUniform = uniUniform;
    }

    public String getUniPhone() {
        return uniPhone;
    }

    public void setUniPhone(String uniPhone) {
        this.uniPhone = uniPhone;
    }

    public String getUniEmail() {
        return uniEmail;
    }

    public void setUniEmail(String uniEmail) {
        this.uniEmail = uniEmail;
    }

    public String getUniType() {
        return uniType;
    }

    public void setUniType(String uniType) {
        this.uniType = uniType;
    }

    public String getUniWebsite() {
        return uniWebsite;
    }

    public void setUniWebsite(String uniWebsite) {
        this.uniWebsite = uniWebsite;
    }

    public String getUniMark() {
        return uniMark;
    }

    public void setUniMark(String uniMark) {
        this.uniMark = uniMark;
    }

    public String getUniRegion() {
        return uniRegion;
    }

    public void setUniRegion(String uniRegion) {
        this.uniRegion = uniRegion;
    }

    public String getUniLat() {
        return uniLat;
    }

    public void setUniLat(String uniLat) {
        this.uniLat = uniLat;
    }

    public String getUniLng() {
        return uniLng;
    }

    public void setBoyMark(int boyMark) {
        this.boyMark = boyMark;
    }

    public void setGirlMark(int girlMark) {
        this.girlMark = girlMark;
    }

    public void setUniLng(String uniLng) {
        this.uniLng = uniLng;
    }

    public byte[] getUniPhoto() {
        return uniPhoto;
    }

    public void setUniPhoto(byte[] uniPhoto) {
        this.uniPhoto = uniPhoto;
    }
}
