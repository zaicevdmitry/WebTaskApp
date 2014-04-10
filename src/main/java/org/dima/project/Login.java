package org.dima.project;


import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dmitry.zaicev on 07.04.14.
 */
@ManagedBean(name = "user")
@SessionScoped
public class Login implements Serializable {

    private String username;
    private String password;
    private List<String> taskList;
    private String name;

    public void login() throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();
        boolean loggedIn = false;

        if(username != null  & username.equals("admin") & password != null  & password.equals("admin")) {
            loggedIn = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Welcome!!!", username));
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("task.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore!!!", " Please enter valid Login and Password"));
        }

        FacesContext session = FacesContext.getCurrentInstance();
        session.getExternalContext().getSessionMap().put("user", username);

        context.addCallbackParam("loggedIn", loggedIn);

    }
    public Login() {

        taskList = new ArrayList<String>();
    }
    public void addTask() throws NullPointerException{
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        taskList.add(name);
    }


    public String getUsername() {
        FacesContext session = FacesContext.getCurrentInstance();
        return (String) session.getExternalContext().getSessionMap().get("user");
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
    public List<String> getTaskList() {
        return taskList;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

}
