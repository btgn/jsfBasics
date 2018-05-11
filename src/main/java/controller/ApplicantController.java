package controller;


import model.Applicants;
import model.JobApplicant;
import services.JobApplicantServiceImpl;
import util.FacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Iterator;
/**
 * Applicant Controller. 
 */
@ManagedBean(name="applicantController")
@ApplicationScoped
public class ApplicantController {
	
	@ManagedProperty(value="#{jobApplicantServiceImpl}")
	private JobApplicantServiceImpl jobApplicantService;


    @Override
    public String toString(){
        return "jobApplication " + super.toString();
    }
    
	public String addApplicant() {
        
		JobApplicant jobApplicant = (JobApplicant) FacesUtils.getManagedBean("jobApplicant");        
		// Interdependent field validation
		if (jobApplicant.getFirstName().equals("John") && jobApplicant.getLastName().equals("Doe")) {
			String msg = "John Doe already works for us";
			FacesMessage facesMessage = new FacesMessage(msg);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String clientId = null; // this is a global message
			facesContext.addMessage(clientId, facesMessage);
			return null;
		// Validation passed, add applicant
		}else{
			jobApplicantService.addApplicant(jobApplicant);
            Applicants applicants = (Applicants) FacesUtils.getManagedBean("applicants");
            applicants.refresh(jobApplicantService.getApplicants());			
			return "applicants?faces-redirect=true";
		}
	}
	
	public void clearForm(ActionEvent ae) {
		
		// Clear bean values
		JobApplicant jobApplicant = (JobApplicant) FacesUtils.getManagedBean("jobApplicant");
        jobApplicant.clearForm();
		
        // Clear component values
        // Retrieve a reference to the containing form
		UIComponent form = getContainingForm(ae.getComponent());
		// Clear all input components in the form
        clearEditableValueHolders(form);
	}
	
	public UIComponent getContainingForm(UIComponent component){		
		if(!(component.getParent() instanceof UIForm)){
			return getContainingForm(component.getParent());
		}else{
			return component.getParent();
		}		
	}
	
	public void clearEditableValueHolders(UIComponent form){		
		Iterator<UIComponent> iterator = form.getFacetsAndChildren();
		while(iterator.hasNext()){
			UIComponent component = iterator.next();
			if(component instanceof EditableValueHolder){
				((EditableValueHolder) component).resetValue();
			}
			clearEditableValueHolders(component);
		}
	}

	public JobApplicantServiceImpl getJobApplicantService() {
		return jobApplicantService;
	}

	public void setJobApplicantService(JobApplicantServiceImpl jobApplicantService) {
		this.jobApplicantService = jobApplicantService;
	}
	
	

}
