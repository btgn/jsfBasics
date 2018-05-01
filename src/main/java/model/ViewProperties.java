package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="viewProperties")
@SessionScoped
public class ViewProperties implements Serializable{

	@Override
	public String toString() {
		return "viewProperties "
				+ super.toString();
	}

}
