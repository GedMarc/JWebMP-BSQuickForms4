package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.*;
import com.jwebmp.core.base.angular.forms.enumerations.*;
import com.jwebmp.plugins.bootstrap.datetimepicker.*;
import com.jwebmp.plugins.bootstrap.forms.*;
import com.jwebmp.plugins.bootstrap.forms.groups.enumerations.*;
import com.jwebmp.plugins.quickforms.*;
import com.jwebmp.plugins.quickforms.annotations.*;
import com.jwebmp.plugins.quickforms.services.*;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class BuildDateTimeField implements IAnnotationFieldHandler<DateTimePickerField, BSDateTimePicker<?>>
{
    @Override
    public DateTimePickerField appliedAnnotation() {
        return new DateTimePickerField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return DateTimePickerField.class;
            }

            @Override
            public String style() {
                return null;
            }

            @Override
            public String classes() {
                return null;
            }

            @Override
            public boolean showControlFeedback() {
                return false;
            }
            
            @Override
            public boolean required() {
                return false;
            }

            @Override
            public String regex() {
                return null;
            }

            @Override
            public String regexBind() {
                return null;
            }
        };
    }

    @Override
    public BSDateTimePicker<?> buildField(QuickForms<?, ?> form, Field field, DateTimePickerField annotation, BSDateTimePicker<?> fieldGroup) {

        BSFormLabel<?> label = new BSFormLabel<>();
        LabelField labelField = form.getLabelFromField(field).orElse(null);
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
        BSDateTimePicker<?> dateTimePicker = new BSDateTimePicker<>();
        dateTimePicker.asMe()
                .addLabel(label.getLabel());

        dateTimePicker.getInput()
                .bind(form.getFieldVariableName(field));

        if (annotation.required())
        {

            dateTimePicker.getInput()
                    .setRequired();
        }
        if (annotation.showControlFeedback())
        {
            dateTimePicker.asMe()
                    .setStyleInputGroupTextWithValidation(true);
        }
        if (!annotation.classes()
                .isEmpty())
        {
            dateTimePicker.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty())
        {
            dateTimePicker.getInput()
                    .addStyle(annotation.style());
        }
        if (!Strings.isNullOrEmpty(annotation.regexBind())) {
            dateTimePicker.getInput().addAttribute("ng-pattern", annotation.regexBind());
        }
        if (!Strings.isNullOrEmpty(annotation.regex())) {
            dateTimePicker.getInput().addAttribute("pattern", annotation.regex());
        }

        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
            dateTimePicker.getMessages().setShowOnEdit(true);
            dateTimePicker.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            dateTimePicker.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            dateTimePicker.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            dateTimePicker.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            dateTimePicker.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            dateTimePicker.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        form.setValue(field, dateTimePicker.getInput());

        return dateTimePicker;

    }

}
