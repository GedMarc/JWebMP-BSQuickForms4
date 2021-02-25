package com.jwebmp.plugins.bs4.quickforms.annotations.implementations;

import com.jwebmp.plugins.quickforms.annotations.LabelField;

import java.lang.annotation.Annotation;

public class DefaultLabelField
		implements LabelField
{

	@Override
	public String value()
	{
		return "";
	}

	@Override
	public boolean inline() {
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
	public boolean showControlFeedback()
	{
		return false;
	}

	@Override
	public Class<? extends Annotation> annotationType()
	{
		return LabelField.class;
	}
}
