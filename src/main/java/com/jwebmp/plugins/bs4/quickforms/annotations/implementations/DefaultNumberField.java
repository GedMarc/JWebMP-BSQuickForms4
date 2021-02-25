package com.jwebmp.plugins.bs4.quickforms.annotations.implementations;

import com.jwebmp.plugins.quickforms.annotations.NumberField;

import java.lang.annotation.Annotation;

public class DefaultNumberField
		implements NumberField
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
	public int precision() {
		return 0;
	}

	@Override
	public int scale() {
		return 0;
	}

	@Override
	public int minimumValue()
	{
		return 0;
	}

	@Override
	public int maximumValue()
	{
		return 100;
	}

	@Override
	public boolean showControlFeedback()
	{
		return false;
	}

	@Override
	public String regex()
	{
		return "";
	}

	@Override
	public String regexBind()
	{
		return "";
	}

	@Override
	public Class<? extends Annotation> annotationType()
	{
		return NumberField.class;
	}
}
