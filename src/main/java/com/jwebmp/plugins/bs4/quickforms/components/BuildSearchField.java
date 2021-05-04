package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputSearchType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
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
            public boolean required() {
                return false;
            }
    
            @Override
            public int minLength()
            {
                return 0;
            }
    
            @Override
            public int maxLength()
            {
                return 0;
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
    public BSFormInputGroup<?, InputSearchType<?>> buildField(QuickForms<?, ?> form, Field field, SearchField annotation, BSFormInputGroup<?, InputSearchType<?>> fieldGroup) {

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

        if (!Strings.isNullOrEmpty(annotation.regexBind())) {
            searchField.getInput().addAttribute("ng-pattern", annotation.regexBind());
        }
        if (!Strings.isNullOrEmpty(annotation.regex())) {
            searchField.getInput().addAttribute("pattern", annotation.regex());
        }

        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
            searchField.getMessages().setShowOnEdit(true);
            searchField.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            searchField.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            searchField.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            searchField.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            searchField.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            searchField.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        form.setValue(field, searchField.getInput());

        return searchField;

    }

}
