package model;

import util.FacesUtils;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class Applicants implements Serializable {



    private List<JobApplicant> applicantList = new ArrayList<JobApplicant>();

    public List<JobApplicant> getApplicantList() {
        return applicantList;
    }

    public void setApplicantList(List<JobApplicant> applicantList) {
        this.applicantList = applicantList;
    }

    public void refresh(List<JobApplicant> refreshApplicants){
        setApplicantList(refreshApplicants);
    }

    
}
