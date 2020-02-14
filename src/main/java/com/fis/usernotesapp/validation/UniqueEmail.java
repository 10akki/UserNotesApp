package com.fis.usernotesapp.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation for checking unique email
 * @author Akhil Garg
 *
 */
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueEmail {

	public String message() default "A user with the entered email Id already exists in the system.";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};

}