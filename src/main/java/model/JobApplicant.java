package model;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;


/**
 * Job Applicant model
 */
@ManagedBean
// This kind of scope will not retain any values once the page is refreshed
@ViewScoped

/*
* This kind of scope declaration will retain values when the page is refreshed
* */
//@CustomScoped(value = "#{window}")
public class JobApplicant implements Serializable {

	private String firstName;

	private String lastName;

	private String title;

	private String country;

	private String email;

	private int salary;

	@Override
	public String toString() {
		return "jobApplicant " + super.toString();
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
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

	@PostConstruct
	public void clearForm() {
		setFirstName("");
		setLastName("");
		setTitle(null);
		setCountry("");
		setSalary(0);
		setEmail("");

	}

}