package com.yugandhar.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean
@RequestScoped
public class RequestController {

    private String param1;
    private String param2;

    public String parametersAction(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        param1 = params.get("param1Name");
        param2 = params.get("param2Name");
        return "result";
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

}