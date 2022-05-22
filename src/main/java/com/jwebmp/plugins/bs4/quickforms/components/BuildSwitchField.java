package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.plugins.bootstrap.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.SwitchField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.guicedee.guicedinjection.json.StaticStrings.STRING_DOT;

public class BuildSwitchField implements IAnnotationFieldHandler<SwitchField, BSFormInputGroup<?, BSSwitch4<?,?>>> {
    @Override
    public SwitchField appliedAnnotation() {
        return new SwitchField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return SwitchField.class;
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
            public boolean required() {
                return false;
            }

            @Override
            public boolean showControlFeedback() {
                return false;
            }

            @Override
            public String onText() {
                return null;
            }

            @Override
            public String offText() {
                return null;
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
    public BSFormInputGroup<?, BSSwitch4<?,?>> buildField(QuickForms<?, ?> form, Field field, SwitchField annotation, BSFormInputGroup<?, BSSwitch4<?,?>> fieldGroup) {

        fieldGroup = new BSFormInputGroup<>();
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

        BSSwitch4<?,?> input = new BSSwitch4<>();
        input.bind(formm.getID() + STRING_DOT + field.getName());
        fieldGroup.setInput(input);
        if (annotation.required())
        {
            input.setRequired();
        }
        if (!annotation.onText()
                .isEmpty())
        {
            input.setOnText(annotation.onText());
        }
        if (!annotation.offText()
                .isEmpty())
        {
            input.setOffText(annotation.offText());
        }
        if (field.isAnnotationPresent(ErrorMessages.class)) {
            //noinspection ConstantConditions
            if(fieldGroup != null) {
                ErrorMessages em = field.getAnnotation(ErrorMessages.class);
                fieldGroup.getMessages().setShowOnEdit(true);
                fieldGroup.getMessages().addMessage(InputErrorValidations.min, em.minMessage(), em.inline());
                fieldGroup.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(), em.inline());
                fieldGroup.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(), em.inline());
                fieldGroup.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(), em.inline());
                fieldGroup.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(), em.inline());
                fieldGroup.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(), em.inline());
            }
        }
        formm.setValue(field, input);

        return fieldGroup;

    }

}
