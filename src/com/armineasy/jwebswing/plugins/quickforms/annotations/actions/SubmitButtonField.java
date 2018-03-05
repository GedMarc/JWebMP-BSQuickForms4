package com.armineasy.jwebswing.plugins.quickforms.annotations.actions;

import za.co.mmagon.jwebswing.plugins.bootstrap4.options.BSColumnOptions;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SubmitButtonField
{
	BSColumnOptions[] sizes() default {BSColumnOptions.Col_12, BSColumnOptions.Col_Md_4, BSColumnOptions.Col_Lg_4};

	String style() default "";

	String classes() default "";

	String label() default "";

	String iconClass() default "";

}
