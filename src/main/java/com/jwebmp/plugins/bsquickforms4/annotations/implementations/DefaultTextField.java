package com.jwebmp.plugins.bsquickforms4.annotations.implementations;

import com.jwebmp.plugins.quickforms.annotations.TextField;

import java.lang.annotation.Annotation;

public class DefaultTextField
		implements TextField
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
	public String placeholder()
	{
		return "";
	}

	@Override
	public int minLength()
	{
		return 0;
	}

	@Override
	public int maxLength()
	{
		return 250;
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
		return TextField.class;
	}
}
