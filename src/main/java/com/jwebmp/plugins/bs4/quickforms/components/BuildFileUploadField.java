package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.InputFileType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
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
        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
         //   fileUploadField.getMessages().setShowOnEdit(true);
            fileUploadField.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            fileUploadField.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            fileUploadField.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            fileUploadField.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            fileUploadField.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            fileUploadField.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        return fileUploadField;

    }

}
