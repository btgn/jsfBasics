package model;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import lombok.Getter;
import lombok.Setter;

/**
 * Job Applicant model
 */
@ManagedBean
@ViewScoped
public class JobApplicant implements Serializable {

	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private Integer title;
	
	@Getter
	@Setter
	private String country;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private int salary;

	@Override
	public String toString() {
		return "jobApplicant " + super.toString();
	}

	public void submit(ActionEvent ae) {
		if (firstName.equals("John") && lastName.equals("Doe")) {
			String msg = "John Doe already works for us";
			FacesMessage facesMessage = new FacesMessage(msg);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String clientId = null; // this is a global message
			facesContext.addMessage(clientId, facesMessage);
		}
	}

}
