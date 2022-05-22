package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.InputDateType;
import com.jwebmp.core.base.html.inputs.InputEmailType;
import com.jwebmp.plugins.bootstrap.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.DatePickerField;
import com.jwebmp.plugins.quickforms.annotations.EmailField;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.jwebmp.plugins.bootstrap.options.BSColumnOptions.Col;
import static com.jwebmp.plugins.bootstrap.options.BSContainerOptions.Row;

public class BuildEmailField implements IAnnotationFieldHandler<EmailField, BSFormInputGroup<?, InputEmailType<?>>> {
    @Override
    public EmailField appliedAnnotation() {
        return new EmailField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return EmailField.class;
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
            public String regex() {
                return null;
            }

            @Override
            public String regexBind() {
                return null;
            }

            @Override
            public String placeholder() {
                return null;
            }
        };
    }

    @Override
    public BSFormInputGroup<?, InputEmailType<?>> buildField(QuickForms<?, ?> form, Field field, EmailField annotation, BSFormInputGroup<?, InputEmailType<?>> fieldGroup) {

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

        BSFormInputGroup<?, InputEmailType<?>> emailField = formm.getForm().createEmailInput(formm.getFieldVariableName(field), label.getLabel(), true);
        emailField.setInput(new InputEmailType<>());
        emailField.getInput()
                .bind(formm.getFieldVariableName(field));

        if (annotation.required())
        {
            emailField.getInput()
                    .setRequired();
        }
        if (annotation.showControlFeedback())
        {
            emailField.setStyleInputGroupTextWithValidation(true);
        }
        if (!annotation.classes()
                .isEmpty())
        {
            emailField.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.placeholder()
                .isEmpty())
        {
            emailField.getInput()
                    .setPlaceholder(annotation.placeholder());
        }
        if (!annotation.style()
                .isEmpty())
        {
            emailField.getInput()
                    .addStyle(annotation.style());
        }

        if (!Strings.isNullOrEmpty(annotation.regexBind())) {
            emailField.getInput().addAttribute("ng-pattern", annotation.regexBind());
        }
        if (!Strings.isNullOrEmpty(annotation.regex())) {
            emailField.getInput().addAttribute("pattern", annotation.regex());
        }

        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
           // emailField.getMessages().setShowOnEdit(true);
            emailField.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            emailField.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            emailField.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            emailField.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            emailField.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            emailField.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        form.setValue(field, emailField.getInput());

        return emailField;

    }

}
