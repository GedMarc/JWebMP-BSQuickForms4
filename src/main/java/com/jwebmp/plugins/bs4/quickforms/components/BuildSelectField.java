package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputSearchType;
import com.jwebmp.core.base.html.inputs.InputSelectType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.SearchField;
import com.jwebmp.plugins.quickforms.annotations.SelectField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuildSelectField implements IAnnotationFieldHandler<SelectField, BSFormInputGroup<?, InputSelectType<?>>> {
    @Override
    public SelectField appliedAnnotation() {
        return new SelectField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return SelectField.class;
            }

            @Override
            public boolean multiple() {
                return false;
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
    public BSFormInputGroup<?, InputSelectType<?>> buildField(QuickForms<?, ?> form, Field field, SelectField annotation, BSFormInputGroup<?, InputSelectType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();
        }
        BSFormInputGroup<?, InputSelectType<?>> selectGroup = formm.getForm().createSelectDropdown(formm.getFieldVariableName(field), label, true);
        InputSelectType<?> input = new InputSelectType<>();
        input.bind(formm.getFieldVariableName(field));
        selectGroup.setInput(input);

        if (annotation.multiple())
        {
            selectGroup.getInput()
                    .setMultiple(true);
        }
        if (annotation.required())
        {
            input.setRequired();
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            selectGroup.addMessage(InputErrorValidations.pattern, annotation.patternMessage());
        }

        try
        {
            Object fieldObject = field.get(formm.getObject());
            Map<String, String> keys = formm.toOptions(fieldObject, field.getType());
            keys.forEach((key, value) -> selectGroup.getInput()
                    .addOption(key, value));
        }
        catch (Exception e)
        {
            Logger.getLogger("BuildSelectField").log(Level.WARNING, "Unable to generate select list", e);
        }
        form.setValue(field, selectGroup.getInput());

        return selectGroup;
    }

}
