package org.dima.project;


import ch.ethz.ssh2.Connection;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import sun.security.krb5.Credentials;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.security.Identity;

/**
 * Created by dmitry.zaicev on 07.04.14.
 */
public class Login implements Serializable {
    private String username;
    private int userID = 0;
    private String password;
    private boolean value1;
    private boolean value2;

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

    public int getUserID() {
        return userID;
    }
    public boolean getValue2(){return value2;}
    public void setValue2(boolean value2){this.value2 = value2;}

    public void login() throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;

        if(username != null  & username.equals("admin") & password != null  & password.equals("admin")) {
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);

            RequestContext.getCurrentInstance().showMessageInDialog(msg);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("task.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
        }

        context.addCallbackParam("loggedIn", loggedIn);

    }


    public void addMessage() {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + this.username));
        String summary = value2 ? "Checked" : "Unchecked";

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

}
