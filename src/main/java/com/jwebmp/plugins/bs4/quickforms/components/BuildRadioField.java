package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputCheckBoxType;
import com.jwebmp.core.base.html.inputs.InputRadioType;
import com.jwebmp.plugins.bootstrap4.buttons.checkbox.BSCheckBoxGroup;
import com.jwebmp.plugins.bootstrap4.buttons.radio.BSRadioButtonGroup;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.CheckboxField;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
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
            public boolean required() {
                return false;
            }
        };
    }

    @Override
    public BSRadioButtonGroup<?> buildField(QuickForms<?, ?> form, Field field, RadioField annotation, BSRadioButtonGroup<?> fieldGroup) {

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
        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
            radioButtonField.getMessages().setShowOnEdit(true);
            radioButtonField.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            radioButtonField.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            radioButtonField.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            radioButtonField.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            radioButtonField.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            radioButtonField.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        form.setValue(field, radioButtonField.getInput());

        return radioButtonField;
    }

}
