package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.annotations.states.WebReadOnlyPlainText;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildReadOnlyPlainTextField implements IAnnotationFieldHandler<WebReadOnlyPlainText, BSFormInputGroup<?, InputTextType<?>>> {
    @Override
    public WebReadOnlyPlainText appliedAnnotation() {
        return new WebReadOnlyPlainText(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return WebReadOnlyPlainText.class;
            }
        };
    }

    @Override
    public BSFormInputGroup<?, InputTextType<?>> buildField(QuickForms<?, ?> form, Field field, WebReadOnlyPlainText annotation, BSFormInputGroup<?, InputTextType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (form.getLabelFromField(field).isPresent()) {
            label = form.getLabelFromField(field).get()
                    .value();
        }
        BSFormInputGroup<?, InputTextType<?>> textInput = formm.getForm().createTextInput(form.getFieldVariableName(field), label, true);

        textInput.setInput(new InputTextType<>());
        textInput.getInput()
                .bind(form.getFieldVariableName(field));

        textInput.getInput().addClass(BSFormGroupOptions.Form_Control_PlainText);

        form.setValue(field, textInput.getInput());

        return textInput;

    }

}
