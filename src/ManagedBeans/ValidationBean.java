package ManagedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validator")
public class ValidationBean implements Validator{
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		String password = value.toString();
		String confirmPassword;
		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
				.get("confirmPassword");
		
		try{
			confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
		}catch(NullPointerException e) {
			throw new ValidatorException(new FacesMessage("Passwords must match."));
		}
		if (!password.equals(confirmPassword)) {
			uiInputConfirmPassword.setValid(false);
			throw new ValidatorException(new FacesMessage("Passwords must match."));
		}
		uiInputConfirmPassword.setValid(true);
		return;
	}
}
