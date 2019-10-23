/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northeastern.cs5200.models;


import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Zhenyu Yang
 */
public class Developer extends Person {
    private String developerKey;

    public Developer(String developerKey, int id, String firstname, String lastname) {
        super(id, firstname, lastname);
        this.developerKey = developerKey;
    }
    
    public Developer(String developerKey, int id, String firstname, String lastname, String username, String password, String email, Date dob) {
        super(id, firstname, lastname, username, password, email, dob);
        this.developerKey = developerKey;
    }

    public Developer(String developerKey, int id, String firstname, String lastname, String username, String password, String email, Date dob, ArrayList<Address> addresses, ArrayList<Phone> phones) {
        super(id, firstname, lastname, username, password, email, dob, addresses, phones);
        this.developerKey = developerKey;
    }
    
    public String getDeveloperKey() {
        return developerKey;
    }

    public void setDeveloperKey(String developerKey) {
        this.developerKey = developerKey;
    }
    
}
