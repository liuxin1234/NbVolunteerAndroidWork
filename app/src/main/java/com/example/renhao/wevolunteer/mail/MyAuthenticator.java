package com.example.renhao.wevolunteer.mail;
/*
 *
 * Created by Ge on 2016/9/17  14:46.
 *
 */

import javax.mail.*;

public class MyAuthenticator extends Authenticator {
    String userName = null;
    String password = null;

    public MyAuthenticator() {
    }

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}

