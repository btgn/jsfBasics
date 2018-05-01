package controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="applicantController")
@ApplicationScoped
public class ApplicantController {

	@Override
	public String toString() {
		return " Applicant Controller  "
				+ super.toString();
	}
	
}
