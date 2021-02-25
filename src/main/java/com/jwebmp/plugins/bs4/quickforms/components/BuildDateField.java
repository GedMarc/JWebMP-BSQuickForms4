package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.InputDateType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.DatePickerField;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Row;

public class BuildDateField implements IAnnotationFieldHandler<DatePickerField, BSFormInputGroup<?, InputDateType<?>>> {
    @Override
    public DatePickerField appliedAnnotation() {
        return new DatePickerField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return DatePickerField.class;
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
        };
    }

    @Override
    public BSFormInputGroup<?, InputDateType<?>> buildField(QuickForms<?, ?> form, Field field, DatePickerField annotation, BSFormInputGroup<?, InputDateType<?>> fieldGroup) {

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
        BSFormInputGroup<?, InputDateType<?>> textInput = new BSFormInputGroup<>();

        if (annotation.showControlFeedback()) {
            textInput.setStyleInputGroupTextWithValidation(true);
        }

        textInput.setInput(new InputDateType<>());

        if(labelField != null) {
            label.setForInputComponent(textInput.getInput());
            if(labelField.inline())
            {
                textInput.addClass(Row);
                textInput.getInput().addClass(Col);
                label.addClass(Col);
            }
        }



        textInput.getInput()
                .bind(form.getFieldVariableName(field));

        if (annotation.required()) {
            textInput.getInput()
                    .setRequired();
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
        form.setValue(field, textInput.getInput());

        return textInput;

    }

}
