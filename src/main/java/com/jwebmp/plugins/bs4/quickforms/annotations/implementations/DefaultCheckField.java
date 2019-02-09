package com.jwebmp.plugins.bs4.quickforms.annotations.implementations;

import com.jwebmp.plugins.quickforms.annotations.CheckboxField;

import java.lang.annotation.Annotation;

public class DefaultCheckField
		implements CheckboxField
{
	@Override
	public String style()
	{
		return "";
	}

	@Override
	public String classes()
	{
		return "";
	}

	@Override
	public boolean showControlFeedback()
	{
		return false;
	}

	@Override
	public String requiredMessage()
	{
		return "This field is required";
	}

	@Override
	public String patternMessage()
	{
		return "Please make sure your entry is correct.";
	}

	@Override
	public boolean required()
	{
		return false;
	}

	@Override
	public Class<? extends Annotation> annotationType()
	{
		return CheckboxField.class;
	}
}
