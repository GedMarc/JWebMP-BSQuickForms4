package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputDateType;
import com.jwebmp.core.base.html.inputs.InputNumberType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.DatePickerField;
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
            public String requiredMessage() {
                return null;
            }

            @Override
            public String patternMessage() {
                return null;
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
        String label = null;
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();
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
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            timeInput.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            timeInput.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, timeInput.getInput());

        return timeInput;

    }

}
