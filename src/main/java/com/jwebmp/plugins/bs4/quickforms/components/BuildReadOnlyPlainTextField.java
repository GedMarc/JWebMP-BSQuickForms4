package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.annotations.states.WebReadOnlyPlainText;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildReadOnlyPlainTextField implements IAnnotationFieldHandler<WebReadOnlyPlainText, BSFormInputGroup<?, InputTextType<?>>>
{
	@Override
	public WebReadOnlyPlainText appliedAnnotation()
	{
		return new WebReadOnlyPlainText()
		{
			@Override
			public String bind()
			{
				return null;
			}
			
			@Override
			public String style()
			{
				return null;
			}
			
			@Override
			public String classes()
			{
				return null;
			}
			
			@Override
			public Class<? extends Annotation> annotationType()
			{
				return WebReadOnlyPlainText.class;
			}
		};
	}
	
	@Override
	public BSFormInputGroup<?, InputTextType<?>> buildField(QuickForms<?, ?> form, Field field, WebReadOnlyPlainText annotation, BSFormInputGroup<?, InputTextType<?>> fieldGroup)
	{
		
		BSQuickForm<?> formm = (BSQuickForm<?>) form;
		BSFormLabel<?> label = new BSFormLabel<>();
		LabelField labelField = form.getLabelFromField(field)
		                            .orElse(null);
		if (labelField != null)
		{
			if (!labelField.classes()
			               .isEmpty())
			{
				label.addClass(labelField.classes());
			}
			if (!labelField.style()
			               .isEmpty())
			{
				label.addStyle(labelField.style());
			}
			if (labelField.showControlFeedback())
			{
				label.addClass(BSFormGroupOptions.Form_Control_Feedback);
			}
			label.setLabel(labelField.value());
		}
		
		
		BSFormInputGroup<?, InputTextType<?>> textInput = formm.getForm()
		                                                       .createTextInput(form.getFieldVariableName(field), label, true);
		
		textInput.setInput(new InputTextType<>());
		if (!Strings.isNullOrEmpty(annotation.bind()))
		{
			textInput.getInput()
			         .bind(annotation.bind());
		}
		else
		{
			textInput.getInput()
			         .bind(form.getFieldVariableName(field));
		}
		
		textInput.getInput()
		         .removeClass(BSFormGroupOptions.Form_Control);
		textInput.getInput()
		         .addClass(BSFormGroupOptions.Form_Control_PlainText);
		
		form.setValue(field, textInput.getInput());
		
		return textInput;
		
	}
	
}
