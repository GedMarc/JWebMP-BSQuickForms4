package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.InputDateType;
import com.jwebmp.plugins.bootstrap4.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.datetimepicker.BS4DateTimePicker;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.DatePickerField;
import com.jwebmp.plugins.quickforms.annotations.DateTimePickerField;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Row;

public class BuildDateTimeField implements IAnnotationFieldHandler<DateTimePickerField, BS4DateTimePicker<?>> {
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
    public BS4DateTimePicker<?> buildField(QuickForms<?, ?> form, Field field, DateTimePickerField annotation, BS4DateTimePicker<?> fieldGroup) {

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
        BS4DateTimePicker<?> dateTimePicker = new BS4DateTimePicker<>();
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
