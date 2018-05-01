package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="requestTest")
@RequestScoped
public class RequestTest implements Serializable {

	@Override
	public String toString() {
		return "RequestTest "
				+ super.toString();
	}
	
	

}
