package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.InputCheckBoxType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.buttons.checkbox.BSCheckBoxGroup;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.bs4.quickforms.annotations.implementations.DefaultCheckboxField;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.CheckboxField;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Row;

public class BuildCheckboxField implements IAnnotationFieldHandler<CheckboxField, BSCheckBoxGroup<?>> {
    @Override
    public CheckboxField appliedAnnotation() {
        return new DefaultCheckboxField();
    }

    @Override
    public BSCheckBoxGroup<?> buildField(QuickForms<?, ?> form, Field field, CheckboxField annotation, BSCheckBoxGroup<?> fieldGroup) {


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

        BSCheckBoxGroup<?> checkboxField =((BSForm<?>)form.getForm()).createCheckboxInput(form.getFieldVariableName(field),null);
        checkboxField.setInput(new InputCheckBoxType<>());

        if(labelField != null) {
            label.setForInputComponent(checkboxField.getInput());
            if(labelField.inline())
            {
                checkboxField.addClass(Row);
                checkboxField.getInput().addClass(Col);
                label.addClass(Col);
            }

        }


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
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            checkboxField.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            checkboxField.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, checkboxField.getInput());

        return checkboxField;

    }

}
