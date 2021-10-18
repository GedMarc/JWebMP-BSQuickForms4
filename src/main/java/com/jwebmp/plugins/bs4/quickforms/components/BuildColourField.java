package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.*;
import com.jwebmp.core.base.angular.forms.enumerations.*;
import com.jwebmp.core.base.html.inputs.*;
import com.jwebmp.plugins.bootstrap4.forms.*;
import com.jwebmp.plugins.bootstrap4.forms.groups.*;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.*;
import com.jwebmp.plugins.bs4.quickforms.*;
import com.jwebmp.plugins.quickforms.*;
import com.jwebmp.plugins.quickforms.annotations.*;
import com.jwebmp.plugins.quickforms.services.*;
import com.jwebmp.plugins.spectrum.colourpicker.*;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class BuildColourField implements IAnnotationFieldHandler<ColorField, BSFormGroup<?, InputTextType<?>>>
{
	@Override
	public ColorField appliedAnnotation()
	{
		return new ColorField()
		{
			@Override
			public Class<? extends Annotation> annotationType()
			{
				return ColorField.class;
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
			public String placeholder()
			{
				return null;
			}
			
			@Override
			public int minLength()
			{
				return 0;
			}
			
			@Override
			public int maxLength()
			{
				return 0;
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
	public BSFormGroup<?, InputTextType<?>> buildField(QuickForms<?, ?> form, Field field, ColorField annotation, BSFormGroup<?, InputTextType<?>> fieldGroup)
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
		BSFormGroup<?, InputTextType<?>> colourField = formm.getForm()
		                                                    .createTextInput(formm.getFieldVariableName(field), label);
		JQSpectrumColourPicker<?> colourPicker = new JQSpectrumColourPicker<>();
		colourPicker.getOptions()
		            .setPreferredFormat(JQSpectrumaletteFormats.RGB);
		colourPicker.getOptions()
		            .setShowAlpha(true);
		colourField.setInput(colourPicker);
		
		colourField.getInput().bind(formm.getFieldVariableName(field));
		
		
		if (annotation.required())
		{
			colourField.getInput()
			           .setRequired();
		}
      /*  if (annotation.showControlFeedback())
        {
            colourField.setStyleInputGroupTextWithValidation(true);
        }*/
		if (!annotation.classes()
		               .isEmpty())
		{
			colourField.getInput()
			           .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			colourField.getInput()
			           .addStyle(annotation.style());
		}
		if (!annotation.placeholder()
		               .isEmpty())
		{
			colourField.getInput()
			           .setPlaceholder(annotation.placeholder());
		}
		if (!Strings.isNullOrEmpty(annotation.regexBind()))
		{
			colourField.getInput()
			           .addAttribute("ng-pattern", annotation.regexBind());
		}
		if (!Strings.isNullOrEmpty(annotation.regex()))
		{
			colourField.getInput()
			           .addAttribute("pattern", annotation.regex());
		}
		
		if (field.isAnnotationPresent(ErrorMessages.class))
		{
			ErrorMessages em = field.getAnnotation(ErrorMessages.class);
			colourField.getMessages()
			           .setShowOnEdit(true);
			colourField.getMessages()
			           .addMessage(InputErrorValidations.min, em.minMessage(), em.inline());
			colourField.getMessages()
			           .addMessage(InputErrorValidations.minLength, em.minLengthMessage(), em.inline());
			colourField.getMessages()
			           .addMessage(InputErrorValidations.max, em.maxMessage(), em.inline());
			colourField.getMessages()
			           .addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(), em.inline());
			colourField.getMessages()
			           .addMessage(InputErrorValidations.pattern, em.patternMessage(), em.inline());
			colourField.getMessages()
			           .addMessage(InputErrorValidations.required, em.requiredMessage(), em.inline());
		}
		form.setValue(field, colourField.getInput());
		
		return colourField;
		
	}
	
}
