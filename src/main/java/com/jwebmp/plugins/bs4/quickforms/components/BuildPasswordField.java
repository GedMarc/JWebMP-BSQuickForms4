package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputPasswordType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
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
        BSFormInputGroup<?, InputPasswordType<?>> passwordField = formm.getForm().createPasswordInput(formm.getFieldVariableName(field), label, true);
        InputPasswordType<?> passwordType = new InputPasswordType<>();
        passwordField.setInput(passwordType);
        
        passwordType.bind(formm.getFieldVariableName(field));
        if (annotation.required())
        {
            passwordType.setRequired();
        }
        if (annotation.showControlFeedback())
        {
            passwordField.setStyleInputGroupTextWithValidation(true);
        }
        if (!annotation.classes()
                .isEmpty())
        {
            passwordType .addClass(annotation.classes());
        }
        if (!annotation.placeholder()
                .isEmpty())
        {
            passwordType.setPlaceholder(annotation.placeholder());
        }
        if (!annotation.style()
                .isEmpty())
        {
            passwordType.addStyle(annotation.style());
        }

        if (!Strings.isNullOrEmpty(annotation.regexBind())) {
            passwordField.getInput().addAttribute("ng-pattern", annotation.regexBind());
        }
        if (!Strings.isNullOrEmpty(annotation.regex())) {
            passwordField.getInput().addAttribute("pattern", annotation.regex());
        }

        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
          //  passwordField.getMessages().setShowOnEdit(true);
            passwordField.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            passwordField.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            passwordField.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            passwordField.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            passwordField.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            passwordField.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        return passwordField;

    }

}
