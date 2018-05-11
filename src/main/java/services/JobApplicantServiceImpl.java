package services;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.JobApplicant;

@ManagedBean
@ApplicationScoped
public class JobApplicantServiceImpl implements JobApplicantService {
	private List<JobApplicant> applicants = new ArrayList<JobApplicant>();

    @Override
    public void addApplicant(JobApplicant applicant) {
    	applicants.add(applicant);
    }

	@Override
	public List<JobApplicant> getApplicants() {
		return applicants; 
	}

}

