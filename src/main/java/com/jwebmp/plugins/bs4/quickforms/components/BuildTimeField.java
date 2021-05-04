package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputDateType;
import com.jwebmp.core.base.html.inputs.InputNumberType;
import com.jwebmp.plugins.bootstrap4.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.DatePickerField;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.TimePickerField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildTimeField implements IAnnotationFieldHandler<TimePickerField, BSFormInputGroup<?, InputNumberType<?>>> {
    @Override
    public TimePickerField appliedAnnotation() {

        return new TimePickerField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return TimePickerField.class;
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
    public BSFormInputGroup<?, InputNumberType<?>> buildField(QuickForms<?, ?> form, Field field, TimePickerField annotation, BSFormInputGroup<?, InputNumberType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
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
        BSFormInputGroup<?, InputNumberType<?>> timeInput = formm.getForm().createNumberInput(formm.getFieldVariableName(field), label, true);
        if (annotation.showControlFeedback())
        {
            timeInput.setStyleInputGroupTextWithValidation(true);
        }
        timeInput.setInput(new InputNumberType<>());
        timeInput.getInput()
                .bind(formm.getFieldVariableName(field));
        if (annotation.required())
        {
            timeInput.getInput()
                    .setRequired();
        }

        if (!annotation.classes()
                .isEmpty())
        {
            timeInput.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty())
        {
            timeInput.getInput()
                    .addStyle(annotation.style());
        }

        if (!Strings.isNullOrEmpty(annotation.regexBind())) {
            timeInput.getInput().addAttribute("ng-pattern", annotation.regexBind());
        }
        if (!Strings.isNullOrEmpty(annotation.regex())) {
            timeInput.getInput().addAttribute("pattern", annotation.regex());
        }

        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
            timeInput.getMessages().setShowOnEdit(true);
            timeInput.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            timeInput.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            timeInput.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            timeInput.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            timeInput.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            timeInput.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        form.setValue(field, timeInput.getInput());

        return timeInput;

    }

}
