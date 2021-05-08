package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Option;
import com.jwebmp.core.base.html.Select;
import com.jwebmp.core.base.html.inputs.InputSearchType;
import com.jwebmp.core.base.html.inputs.InputSelectType;
import com.jwebmp.plugins.bootstrap4.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bootstrap4.select.BSSelect;
import com.jwebmp.plugins.bs4.nyaselect.NyaSelect;
import com.jwebmp.plugins.bs4.nyaselect.NyaSelectItem;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.SearchField;
import com.jwebmp.plugins.quickforms.annotations.SelectField;
import com.jwebmp.plugins.quickforms.annotations.formtypes.WebSelectComponent;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuildSelectField implements IAnnotationFieldHandler<SelectField, BSFormInputGroup<?, InputSelectType<?>>>
{
	@Override
	public SelectField appliedAnnotation()
	{
		return new SelectField()
		{
			@Override
			public Class<? extends Annotation> annotationType()
			{
				return SelectField.class;
			}
			
			@Override
			public boolean multiple()
			{
				return false;
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
				return null;
			}
			
			@Override
			public String regexBind()
			{
				return null;
			}
		};
	}
	
	@Override
	public BSFormInputGroup<?, InputSelectType<?>> buildField(QuickForms<?, ?> form, Field field, SelectField annotation, BSFormInputGroup<?, InputSelectType<?>> fieldGroup)
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
		
		
		Select<?> select = new BSSelect<>();
		try
		{
			String mName = "get" + StringUtils.capitalize(field.getName());
			try
			{
				Method method = formm.getObject()
				                     .getClass()
				                     .getMethod(mName);
				Object value = method.invoke(formm.getObject());
				
				
				Collection l = null;
				if(method.getReturnType().isEnum())
				{
					List ll = new ArrayList();
					for (Object enumConstant : method.getReturnType()
					                                 .getEnumConstants())
					{
						ll.add(enumConstant);
					}
					l = ll;
				}
				else if(value instanceof Collection)
				{
					l = (Collection) value;
				}
				
				Collection c = l;
				
				for (Object o : c)
				{
					if(o.getClass().isAnnotationPresent(WebSelectComponent.class))
					{
						WebSelectComponent wsc = o.getClass()
						                          .getAnnotation(WebSelectComponent.class);
						Option<?> option = new Option<>();
						String labelFieldName = "get" + StringUtils.capitalize(wsc.labelField());
						String valueFieldName = "get" + StringUtils.capitalize(wsc.valueField());
						
						Method lFieldMethod = o.getClass()
						                       .getMethod(labelFieldName);
						Method vFieldMethod = o.getClass()
						                       .getMethod(valueFieldName);
						Object ll = lFieldMethod.invoke(o);
						Object vv = vFieldMethod.invoke(o);
						
						option.setLabel(ll.toString());
						option.setValue(vv.toString());
						
						select.add(option);
					}
					else
					{
						Option<?> option = new Option<>();
						option.setLabel(o.toString());
						option.setValue(o.toString());
						select.add(option);
					}
				}
			}
			catch (NoSuchMethodException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		BSFormInputGroup<?, InputSelectType<?>> selectGroup = formm.getForm()
		                                                           .createSelectDropdown(formm.getFieldVariableName(field), label, true);
		InputSelectType<?> input = select;
		input.bind(formm.getFieldVariableName(field));
		selectGroup.setInput(input);
		
		if (annotation.multiple())
		{
			selectGroup.getInput()
			           .setMultiple(true);
		}
		if (annotation.required())
		{
			input.setRequired();
		}
		
		if (field.isAnnotationPresent(ErrorMessages.class))
		{
			ErrorMessages em = field.getAnnotation(ErrorMessages.class);
			selectGroup.getMessages()
			           .setShowOnEdit(true);
			selectGroup.getMessages()
			           .addMessage(InputErrorValidations.min, em.minMessage(), em.inline());
			selectGroup.getMessages()
			           .addMessage(InputErrorValidations.minLength, em.minLengthMessage(), em.inline());
			selectGroup.getMessages()
			           .addMessage(InputErrorValidations.max, em.maxMessage(), em.inline());
			selectGroup.getMessages()
			           .addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(), em.inline());
			selectGroup.getMessages()
			           .addMessage(InputErrorValidations.pattern, em.patternMessage(), em.inline());
			selectGroup.getMessages()
			           .addMessage(InputErrorValidations.required, em.requiredMessage(), em.inline());
		}
		
		form.setValue(field, selectGroup.getInput());
		
		return selectGroup;
	}
	
}
