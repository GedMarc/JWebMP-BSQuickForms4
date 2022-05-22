package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.InputCheckBoxType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap.buttons.checkbox.BSCheckBoxGroup;
import com.jwebmp.plugins.bootstrap.forms.BSForm;
import com.jwebmp.plugins.bootstrap.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.bs4.quickforms.annotations.implementations.DefaultCheckboxField;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.CheckboxField;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.jwebmp.plugins.bootstrap.options.BSColumnOptions.Col;
import static com.jwebmp.plugins.bootstrap.options.BSContainerOptions.Row;

public class BuildCheckboxField implements IAnnotationFieldHandler<CheckboxField, BSCheckBoxGroup<?>> {
    @Override
    public CheckboxField appliedAnnotation() {
        return new DefaultCheckboxField();
    }

    @Override
    public BSCheckBoxGroup<?> buildField(QuickForms<?, ?> form, Field field, CheckboxField annotation, BSCheckBoxGroup<?> fieldGroup) {
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

        BSCheckBoxGroup<?> checkboxField =((BSForm<?>)form.getForm()).createCheckboxInput(form.getFieldVariableName(field),label);
        checkboxField.setInput(new InputCheckBoxType<>());

        checkboxField.getInput()
                .bind(form.getFieldVariableName(field));
        if (annotation.required())
        {
            checkboxField.getInput()
                    .setRequired();
        }
        if (!annotation.classes()
                .isEmpty())
        {
            checkboxField.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty())
        {
            checkboxField.getInput()
                    .addStyle(annotation.style());
        }
        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
           // checkboxField.getMessages().setShowOnEdit(true);
            checkboxField.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            checkboxField.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            checkboxField.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            checkboxField.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            checkboxField.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            checkboxField.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }

        form.setValue(field, checkboxField.getInput());

        return checkboxField;

    }

}
