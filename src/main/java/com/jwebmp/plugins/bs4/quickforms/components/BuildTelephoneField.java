package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputTelephoneType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.TelephoneField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildTelephoneField implements IAnnotationFieldHandler<TelephoneField, BSFormInputGroup<?, InputTelephoneType<?>>> {
    @Override
    public TelephoneField appliedAnnotation() {
        return new TelephoneField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return TelephoneField.class;
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
            public boolean showControlFeedback() {
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
    public BSFormInputGroup<?, InputTelephoneType<?>> buildField(QuickForms<?, ?> form, Field field, TelephoneField annotation, BSFormInputGroup<?, InputTelephoneType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();

        }
        BSFormInputGroup<?, InputTelephoneType<?>> textInput = formm.getForm().createTelephoneInput(formm.getFieldVariableName(field), label, true);

        if (annotation.showControlFeedback())
        {
            textInput.setStyleInputGroupTextWithValidation(true);
        }
        textInput.setInput(new InputTelephoneType<>());
        textInput.getInput()
                .bind(formm.getFieldVariableName(field));
        if (annotation.required())
        {
            textInput.getInput()
                    .setRequired();
        }

        if (!annotation.classes()
                .isEmpty())
        {
            textInput.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty())
        {
            textInput.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            textInput.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            textInput.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, textInput.getInput());

        return textInput;

    }

}
