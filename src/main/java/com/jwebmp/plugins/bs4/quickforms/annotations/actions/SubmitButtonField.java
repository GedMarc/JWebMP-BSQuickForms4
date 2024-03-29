package com.jwebmp.plugins.bs4.quickforms.annotations.actions;

import com.jwebmp.plugins.bootstrap.options.BSColumnOptions;

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
