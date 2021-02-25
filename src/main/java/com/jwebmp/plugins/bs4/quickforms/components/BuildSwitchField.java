package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.bs4.toggle.BSSwitch4;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.SwitchField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

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
        BSSwitch4<?,?> input = new BSSwitch4<>();
        if (formm.getLabelFromField(field).isPresent())
        {
            String label = formm.getLabelFromField(field).get()
                    .value();
            input.setOnText(label);
        }
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
        if (!annotation.patternMessage()
                .isEmpty())
        {
            fieldGroup.addMessage(InputErrorValidations.pattern, annotation.patternMessage());
        }
        formm.setValue(field, input);

        return fieldGroup;

    }

}
