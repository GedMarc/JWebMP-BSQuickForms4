package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputHiddenType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.BSFormGroup;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.HiddenField;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildHiddenField implements IAnnotationFieldHandler<HiddenField, BSFormGroup<?, InputHiddenType<?>>> {
    @Override
    public HiddenField appliedAnnotation() {
        return new HiddenField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return HiddenField.class;
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
    public BSFormGroup<?, InputHiddenType<?>> buildField(QuickForms<?, ?> form, Field field, HiddenField annotation, BSFormGroup<?, InputHiddenType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        BSFormGroup<?, InputHiddenType<?>> textInput = formm.getForm().createHiddenInput(formm.getFieldVariableName(field), label);
        textInput.setInput(new InputHiddenType<>());
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
