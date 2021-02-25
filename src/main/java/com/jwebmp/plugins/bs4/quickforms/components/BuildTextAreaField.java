package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputTextAreaType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.TextAreaField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildTextAreaField implements IAnnotationFieldHandler<TextAreaField, BSFormInputGroup<?, InputTextAreaType<?>>> {
    @Override
    public TextAreaField appliedAnnotation() {
        return new TextAreaField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return TextAreaField.class;
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
            public String placeholder() {
                return null;
            }

            @Override
            public int minLength() {
                return 0;
            }

            @Override
            public int maxLength() {
                return 0;
            }

            @Override
            public String minLengthMessage() {
                return null;
            }

            @Override
            public String maxLengthMessage() {
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
            public String regex() {
                return null;
            }

            @Override
            public String regexBind() {
                return null;
            }

            @Override
            public boolean showControlFeedback() {
                return false;
            }
        };
    }

    @Override
    public BSFormInputGroup<?, InputTextAreaType<?>> buildField(QuickForms<?, ?> form, Field field, TextAreaField annotation, BSFormInputGroup<?, InputTextAreaType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();

        }
        BSFormInputGroup<?, InputTextAreaType<?>> textAreaInput = formm.getForm().createTextArea(formm.getFieldVariableName(field), label, true);

        if (annotation.showControlFeedback())
        {
            textAreaInput.setStyleInputGroupTextWithValidation(true);
        }
        textAreaInput.setInput(new InputTextAreaType<>());
        textAreaInput.getInput()
                .bind(formm.getFieldVariableName(field));
        if (annotation.required())
        {
            textAreaInput.getInput()
                    .setRequired();
        }

        if (!annotation.classes()
                .isEmpty())
        {
            textAreaInput.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty())
        {
            textAreaInput.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            textAreaInput.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            textAreaInput.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, textAreaInput.getInput());

        return textAreaInput;

    }

}
