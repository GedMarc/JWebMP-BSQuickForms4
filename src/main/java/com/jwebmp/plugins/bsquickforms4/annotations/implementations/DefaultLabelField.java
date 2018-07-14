package com.jwebmp.plugins.bsquickforms4.annotations.implementations;

import com.jwebmp.plugins.quickforms.annotations.LabelField;

import java.lang.annotation.Annotation;

public class DefaultLabelField
		implements LabelField
{

	@Override
	public String label()
	{
		return null;
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
