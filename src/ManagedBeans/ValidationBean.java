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
	
	//Customer validator for making sure the password and confirmPassword fields on the register page are the same
	//Throwing a validator exception causes the page to display the error message
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		//Values are pulled directly from the page before being submitted to the register backing bean
		String password = value.toString();
		String confirmPassword;
		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
				.get("confirmPassword");
		
		try{
			confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
		}catch(NullPointerException e) {
			throw new ValidatorException(new FacesMessage("Password cannot be null."));
		}
		if (!password.equals(confirmPassword)) {
			uiInputConfirmPassword.setValid(false);
			throw new ValidatorException(new FacesMessage("Passwords must match."));
		}
		uiInputConfirmPassword.setValid(true);//the value entered is valid, allows submission of the form element on the page
		return;
	}
}
