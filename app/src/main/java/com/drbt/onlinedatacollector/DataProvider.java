package com.drbt.onlinedatacollector;

public class DataProvider {
    private String dnsoName,district,year,month,firstLine,frontLine,male,female,anthropocentric,synced;

    public DataProvider(String dnsoName, String district, String year, String month, String firstLine, String frontLine, String male, String female, String anthropocentric, String synced) {
        this.anthropocentric = anthropocentric;
        this.district = district;
        this.dnsoName = dnsoName;
        this.female = female;
        this.firstLine = firstLine;
        this.frontLine = frontLine;
        this.male = male;
        this.month = month;
        this.synced = synced;
        this.year = year;
    }

    public String getAnthropocentric() {
        return anthropocentric;
    }

    public void setAnthropocentric(String anthropocentric) {
        this.anthropocentric = anthropocentric;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDnsoName() {
        return dnsoName;
    }

    public void setDnsoName(String dnsoName) {
        this.dnsoName = dnsoName;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getFrontLine() {
        return frontLine;
    }

    public void setFrontLine(String frontLine) {
        this.frontLine = frontLine;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
