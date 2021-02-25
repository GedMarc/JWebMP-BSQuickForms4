package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.core.base.html.inputs.InputUrlType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.annotations.UrlField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildUrlField implements IAnnotationFieldHandler<UrlField, BSFormInputGroup<?, InputUrlType<?>>> {
    @Override
    public UrlField appliedAnnotation() {
        return new UrlField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return UrlField.class;
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
    public BSFormInputGroup<?, InputUrlType<?>> buildField(QuickForms<?, ?> form, Field field, UrlField annotation, BSFormInputGroup<?, InputUrlType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();

        }
        BSFormInputGroup<?, InputUrlType<?>> urlInput = formm.getForm().createUrlInput(formm.getFieldVariableName(field), label, true);
        if (annotation.showControlFeedback())
        {
            urlInput.setStyleInputGroupTextWithValidation(true);
        }
        urlInput.setInput(new InputUrlType<>());
        urlInput.getInput()
                .bind(formm.getFieldVariableName(field));
        if (annotation.required())
        {
            urlInput.getInput()
                    .setRequired();
        }

        if (!annotation.classes()
                .isEmpty())
        {
            urlInput.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty())
        {
            urlInput.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            urlInput.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            urlInput.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, urlInput.getInput());

        return urlInput;

    }

}
