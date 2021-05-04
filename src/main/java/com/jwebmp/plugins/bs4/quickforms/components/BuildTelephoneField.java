package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputTelephoneType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
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

        if (!Strings.isNullOrEmpty(annotation.regexBind())) {
            textInput.getInput().addAttribute("ng-pattern", annotation.regexBind());
        }
        if (!Strings.isNullOrEmpty(annotation.regex())) {
            textInput.getInput().addAttribute("pattern", annotation.regex());
        }


        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
            textInput.getMessages().setShowOnEdit(true);
            textInput.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            textInput.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            textInput.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            textInput.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            textInput.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            textInput.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        form.setValue(field, textInput.getInput());

        return textInput;

    }

}
