package com.mastrHyperion98.struct;

import com.mastrHyperion98.Encoder.AES;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Password {
    private SimpleIntegerProperty _id;
    private SimpleStringProperty _domain;
    private SimpleStringProperty _username;
    private SimpleStringProperty _email;
    private SimpleStringProperty _password;

    public Password(int id, String domain, String username, String email, String password){
       _id = new SimpleIntegerProperty(id);
       _domain=new SimpleStringProperty(domain);
       _email=new SimpleStringProperty(email);
       _username = new SimpleStringProperty(username);
       _password = new SimpleStringProperty(password);
    }
    public Password(int id, String domain, String email, String password){
        _id = new SimpleIntegerProperty(id);
        _domain=new SimpleStringProperty(domain);
        _email=new SimpleStringProperty(email);
        _username = new SimpleStringProperty("NA");
        _password = new SimpleStringProperty(password);
    }

    public int getId(){
        return _id.get();
    }

    public void setId(int id){
        _id.set(id);
    }

    public String getEmail(){
        return _email.get();
    }

    public void setEmail(String email){
        _email.set(email);
    }

    public String getDomain(){
        return _domain.get();
    }

    public void setDomain(String domain){
        _domain.set(domain);
    }

    public String getUsername(){
        return _username.get();
    }

    public void setUsername(String username){
        _username.set(username);
    }

    public String getPassword(){
        return AES.decrypt(_password.get());
    }

    public void setPassword(String password){
        _password.set(AES.encrypt(password));
    }
}