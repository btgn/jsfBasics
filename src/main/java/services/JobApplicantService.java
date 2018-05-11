package services;

import java.util.List;

import model.JobApplicant;

public interface JobApplicantService {

	public void addApplicant(JobApplicant applicant);
	
	public List<JobApplicant> getApplicants();
}

