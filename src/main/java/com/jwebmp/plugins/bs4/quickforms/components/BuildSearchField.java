package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputSearchType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.SearchField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildSearchField implements IAnnotationFieldHandler<SearchField, BSFormInputGroup<?, InputSearchType<?>>> {
    @Override
    public SearchField appliedAnnotation() {
        return new SearchField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return SearchField.class;
            }

            @Override
            public String group() {
                return null;
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
        };
    }

    @Override
    public BSFormInputGroup<?, InputSearchType<?>> buildField(QuickForms<?, ?> form, Field field, SearchField annotation, BSFormInputGroup<?, InputSearchType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();
        }
        BSFormInputGroup<?, InputSearchType<?>> searchField = formm.getForm().createSearchInput(formm.getFieldVariableName(field), label, true);
        searchField.addHelpText(label);
        searchField.setInput(new InputSearchType<>());
        searchField.getInput()
                .bind(formm.getFieldVariableName(field));

        if (annotation.required())
        {
            searchField.getInput()
                    .setRequired();
        }
        if (annotation.showControlFeedback())
        {
            searchField.setStyleInputGroupTextWithValidation(true);
        }
        if (!annotation.classes()
                .isEmpty())
        {
            searchField.getInput()
                    .addClass(annotation.classes());
        }

        if (!annotation.style()
                .isEmpty())
        {
            searchField.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            searchField.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            searchField.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, searchField.getInput());

        return searchField;

    }

}
