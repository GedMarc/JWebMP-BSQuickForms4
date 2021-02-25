package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.InputDateType;
import com.jwebmp.core.base.html.inputs.InputEmailType;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.DatePickerField;
import com.jwebmp.plugins.quickforms.annotations.EmailField;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Row;

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

            @Override
            public String placeholder() {
                return null;
            }
        };
    }

    @Override
    public BSFormInputGroup<?, InputEmailType<?>> buildField(QuickForms<?, ?> form, Field field, EmailField annotation, BSFormInputGroup<?, InputEmailType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        Label<?> label = new Label<>();
        LabelField labelField = form.getLabelFromField(field).orElse(null);
        if (labelField != null)
        {
            if (!annotation.classes()
                    .isEmpty())
            {
                label.addClass(labelField.classes());
            }
            if (!annotation.style()
                    .isEmpty())
            {
                label.addStyle(labelField.style());
            }
            if (annotation.showControlFeedback())
            {
                label.addClass(BSFormGroupOptions.Form_Control_Feedback);
            }
            label.setLabel(labelField.value());
        }

        BSFormInputGroup<?, InputEmailType<?>> emailField = formm.getForm().createEmailInput(formm.getFieldVariableName(field), label.getLabel(), true);
        emailField.setInput(new InputEmailType<>());
        emailField.getInput()
                .bind(formm.getFieldVariableName(field));

        if(labelField != null) {
            label.setForInputComponent(emailField.getInput());
            if(labelField.inline())
            {
                emailField.addClass(Row);
                emailField.getInput().addClass(Col);
                label.addClass(Col);
            }
        }

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
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            emailField.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            emailField.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, emailField.getInput());

        return emailField;

    }

}
