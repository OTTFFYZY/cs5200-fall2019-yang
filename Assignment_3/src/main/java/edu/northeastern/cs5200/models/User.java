/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northeastern.cs5200.models;

import java.sql.Date;

/**
 *
 * @author Zhenyu Yang
 */
public class User extends Person {
    private int userAgreement;

    public User(int id, String firstname, String lastname) {
        super(id, firstname, lastname);
    }
    
    public User(int id, String firstname, String lastname, String username, String password, String email, Date dob) {
    	super(id, firstname, lastname, username, password, email, dob);
    }

    public int getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(int userAgreement) {
        this.userAgreement = userAgreement;
    }

        
}
