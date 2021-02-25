package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.bs4.quickforms.annotations.implementations.DefaultTextField;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.reflect.Field;

public class BuildTextField implements IAnnotationFieldHandler<TextField, BSFormInputGroup<?, InputTextType<?>>> {
    @Override
    public TextField appliedAnnotation() {

        return new DefaultTextField();
    }

    @Override
    public BSFormInputGroup<?, InputTextType<?>> buildField(QuickForms<?, ?> form, Field field, TextField annotation, BSFormInputGroup<?, InputTextType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (form.getLabelFromField(field).isPresent()) {
            label = form.getLabelFromField(field).get()
                    .value();
        }
        BSFormInputGroup<?, InputTextType<?>> textInput = formm.getForm().createTextInput(form.getFieldVariableName(field), label, true);


        if (annotation.showControlFeedback()) {
            textInput.setStyleInputGroupTextWithValidation(true);
        }

        textInput.setInput(new InputTextType<>());
        textInput.getInput()
                .bind(form.getFieldVariableName(field));

        if (annotation.required()) {
            textInput.getInput()
                    .setRequired();
        }

        if (!annotation.placeholder()
                .isEmpty()) {
            textInput.getInput()
                    .setPlaceholder(annotation.placeholder());
        }

        if (!annotation.classes()
                .isEmpty()) {
            textInput.getInput()
                    .addClass(annotation.classes());
        }

        if (!annotation.style()
                .isEmpty()) {
            textInput.getInput()
                    .addStyle(annotation.style());
        }

        if (!annotation.requiredMessage()
                .isEmpty() && annotation.required()) {
            textInput.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }

        if (!annotation.patternMessage()
                .isEmpty()) {
            textInput.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.patternMessage());
        }

        if (annotation.minLength() != Integer.MIN_VALUE) {
            textInput.getInput().setMinimumLength(annotation.minLength());
        }
        if (annotation.maxLength() != Integer.MIN_VALUE) {
            textInput.getInput().setMaximumLength(annotation.maxLength());
        }
        form.setValue(field, textInput.getInput());

        return textInput;

    }

}
