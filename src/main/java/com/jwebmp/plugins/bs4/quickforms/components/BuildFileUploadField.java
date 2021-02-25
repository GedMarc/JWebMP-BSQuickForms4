package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputFileType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.FileField;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildFileUploadField implements IAnnotationFieldHandler<FileField, BSFormInputGroup<?, InputFileType<?>>> {
    @Override
    public FileField appliedAnnotation() {
        return new FileField(){

            @Override
            public Class<? extends Annotation> annotationType() {
                return FileField.class;
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
            public String label() {
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
    public BSFormInputGroup<?, InputFileType<?>> buildField(QuickForms<?, ?> form, Field field, FileField annotation, BSFormInputGroup<?, InputFileType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        LabelField labelField = form.getLabelFromField(field).orElse(null);
        if (labelField != null)
        {

        }
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();
        }
        BSFormInputGroup<?, InputFileType<?>> fileUploadField = formm.getForm().createFileInput(formm.getFieldVariableName(field), label, true);
        fileUploadField.setInput(new InputFileType<>());
        fileUploadField.getInput()
                .addAttribute("ng-file-model", formm.getFieldVariableName(field));
        fileUploadField.addHelpText(label);

        if (annotation.required())
        {
            fileUploadField.getInput()
                    .setRequired();
        }
        if (annotation.showControlFeedback())
        {
            fileUploadField.setStyleInputGroupTextWithValidation(true);
        }
        if (!annotation.classes()
                .isEmpty())
        {
            fileUploadField.getInput()
                    .addClass(annotation.classes());
        }

        if (!annotation.style()
                .isEmpty())
        {
            fileUploadField.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            fileUploadField.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            fileUploadField.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }

        return fileUploadField;

    }

}
