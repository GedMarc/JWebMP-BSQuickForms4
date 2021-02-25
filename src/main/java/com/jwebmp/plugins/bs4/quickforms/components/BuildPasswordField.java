package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputPasswordType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.PasswordField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildPasswordField implements IAnnotationFieldHandler<PasswordField, BSFormInputGroup<?, InputPasswordType<?>>> {
    @Override
    public PasswordField appliedAnnotation() {
        return new PasswordField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return PasswordField.class;
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
            public boolean isConfirmPassword() {
                return false;
            }

            @Override
            public String placeholder() {
                return null;
            }

            @Override
            public int minLength() {
                return 0;
            }

            @Override
            public int maxLength() {
                return 0;
            }

            @Override
            public String requiredMessage() {
                return null;
            }

            @Override
            public String minLengthMessage() {
                return null;
            }

            @Override
            public String maxLengthMessage() {
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
    public BSFormInputGroup<?, InputPasswordType<?>> buildField(QuickForms<?, ?> form, Field field, PasswordField annotation, BSFormInputGroup<?, InputPasswordType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();

        }
        BSFormInputGroup<?, InputPasswordType<?>> passwordField = formm.getForm().createPasswordInput(formm.getFieldVariableName(field), label, true);
        passwordField.setInput(new InputPasswordType<>());
        passwordField.bind(formm.getFieldVariableName(field));
        if (annotation.required())
        {
            passwordField.getInput()
                    .setRequired();
        }
        if (annotation.showControlFeedback())
        {
            passwordField.setStyleInputGroupTextWithValidation(true);
        }
        if (!annotation.classes()
                .isEmpty())
        {
            passwordField.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.placeholder()
                .isEmpty())
        {
            passwordField.getInput()
                    .setPlaceholder(annotation.placeholder());
        }
        if (!annotation.style()
                .isEmpty())
        {
            passwordField.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            passwordField.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            passwordField.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }

        return passwordField;

    }

}
