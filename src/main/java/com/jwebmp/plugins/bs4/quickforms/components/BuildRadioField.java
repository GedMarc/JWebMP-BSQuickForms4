package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputCheckBoxType;
import com.jwebmp.core.base.html.inputs.InputRadioType;
import com.jwebmp.plugins.bootstrap4.buttons.checkbox.BSCheckBoxGroup;
import com.jwebmp.plugins.bootstrap4.buttons.radio.BSRadioButtonGroup;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.CheckboxField;
import com.jwebmp.plugins.quickforms.annotations.RadioField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildRadioField implements IAnnotationFieldHandler<RadioField, BSRadioButtonGroup<?>> {
    @Override
    public RadioField appliedAnnotation() {
        return new RadioField() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return RadioField.class;
            }

            @Override
            public String group() {
                return null;
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
        };
    }

    @Override
    public BSRadioButtonGroup<?> buildField(QuickForms<?, ?> form, Field field, RadioField annotation, BSRadioButtonGroup<?> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (formm.getLabelFromField(field).isPresent()) {
            label = formm.getLabelFromField(field).get()
                    .value();
        }
        BSRadioButtonGroup<?> radioButtonField = formm.getForm().createRadioInput(formm.getFieldVariableName(field), label, annotation.group());
        radioButtonField.setInput(new InputRadioType<>());
        radioButtonField.bind(formm.getFieldVariableName(field));
        if (annotation.required()) {
            radioButtonField.getInput()
                    .setRequired();
        }
        if (!annotation.classes()
                .isEmpty()) {
            radioButtonField.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty()) {
            radioButtonField.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.requiredMessage()
                .isEmpty()) {
            radioButtonField.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty()) {
            radioButtonField.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, radioButtonField.getInput());

        return radioButtonField;
    }

}
