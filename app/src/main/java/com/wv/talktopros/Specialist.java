//package com.wv.talktopros;
//import android.os.Bundle;
//import android.widget.TextView;
//public class Specialist {
//    String fname,lname,speciality;
//
//    public Specialist() {
//    }
//
//    public Specialist(String fname, String lname, String speciality) {
//        this.fname = fname;
//        this.lname = lname;
//        this.speciality = speciality;
//    }
//
//
//
//    public String getFname() {
//        return fname;
//    }
//
//    public void setFname(String fname) {
//        this.fname = fname;
//    }
//
//    public String getLname() {
//        return lname;
//    }
//
//    public void setLname(String lname) {
//        this.lname = lname;
//    }
//
//    public String getSpeciality() {
//        return speciality;
//    }
//
//    public void setSpeciality(String speciality) {
//        this.speciality = speciality;
//    }
//}


package com.wv.talktopros;
import android.os.Bundle;
import android.widget.TextView;
public class Specialist {
    String name, type;
    String age, experience,uid;

    public Specialist() {
    }

    public Specialist(String name, String type, String age, String experience, String uid) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.experience = experience;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age + " ans";
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getExperience() {
        return experience + " ans";
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}