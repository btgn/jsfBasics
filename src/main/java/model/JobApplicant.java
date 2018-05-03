package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;

/**
 * Job Applicant model
 */
@ManagedBean
@ViewScoped
public class JobApplicant implements Serializable{

	private String firstName;
	private String lastName;
	private String title;
	private String country;
	
	@Override
    public String toString(){
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
		System.out.println(title);
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
	
	
	
}
