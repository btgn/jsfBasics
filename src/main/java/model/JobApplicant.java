package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="jobApplicant")
@ViewScoped
public class JobApplicant implements Serializable {

	@Override
	public String toString() {
		return "JobApplicant "
				+ super.toString();
	}
	
	

}
