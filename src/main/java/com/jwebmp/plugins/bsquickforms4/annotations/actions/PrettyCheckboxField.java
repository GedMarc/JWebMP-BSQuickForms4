package com.jwebmp.plugins.bsquickforms4.annotations.actions;

import java.lang.annotation.*;

@Target(
		{
				ElementType.FIELD, ElementType.TYPE_USE
		})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PrettyCheckboxField
{
	String value() default "";

	String label() default "";

	boolean disabled() default false;

	boolean labelLeft() default false;

	boolean multiple() default false;

	boolean required() default false;

	String requiredMessage() default "This field is required";

	boolean showControlFeedback() default true;

}
