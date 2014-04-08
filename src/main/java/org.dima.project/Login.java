package org.dima.project;


import ch.ethz.ssh2.Connection;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import sun.security.krb5.Credentials;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.awt.event.ActionEvent;
import java.security.Identity;

/**
 * Created by dmitry.zaicev on 07.04.14.
 */
public class Login {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(ActionEvent actionEvent) throws IllegalArgumentException{
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;

        if(username != null  && username.equals("admin") && password != null  && password.equals("admin")) {
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
    }
}
