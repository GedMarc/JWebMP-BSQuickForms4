package com.jwebmp.plugins.bs4.quickforms.annotations.implementations;

import com.jwebmp.plugins.quickforms.annotations.SelectField;

import java.lang.annotation.Annotation;

public class DefaultSelectField
		implements SelectField
{

	@Override
	public boolean multiple()
	{
		return false;
	}

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
		return SelectField.class;
	}
}
