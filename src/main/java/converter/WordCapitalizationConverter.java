package converter;

import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="wordCapitalization")
public class WordCapitalizationConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return fixCapitalization(value);
	}

	@SuppressWarnings("unused")
	private String fixCapitalization(String value) {
		if(!value.isEmpty()) {
			String str = value.substring(0, 1);
			System.out.println(str.toUpperCase() + value.substring(1, value.length()));
			return str.toUpperCase() + value.substring(1, value.length());		
		}
		else {
			return value;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return fixCapitalization((String)value);
	}

}
