package com.wv.talktopros;
import android.os.Bundle;
import android.widget.TextView;
public class Specialist {
    String fname,lname,speciality;

    public Specialist() {
    }

    public Specialist(String fname, String lname, String speciality) {
        this.fname = fname;
        this.lname = lname;
        this.speciality = speciality;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}