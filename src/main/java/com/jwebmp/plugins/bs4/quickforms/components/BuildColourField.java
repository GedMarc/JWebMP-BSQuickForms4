package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.InputColourType;
import com.jwebmp.core.base.html.inputs.InputTextType;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.ColorField;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Row;

public class BuildColourField implements IAnnotationFieldHandler<ColorField, BSFormInputGroup<?, InputColourType<?>>> {
    @Override
    public ColorField appliedAnnotation() {
        return new ColorField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return ColorField.class;
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
    public BSFormInputGroup<?, InputColourType<?>> buildField(QuickForms<?, ?> form, Field field, ColorField annotation, BSFormInputGroup<?, InputColourType<?>> fieldGroup) {

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
        BSFormInputGroup<?, InputColourType<?>> colourField = formm.getForm().createDateInput()
        colourField.setInput(new InputColourType<>());

        if(labelField != null) {
            label.setForInputComponent(colourField.getInput());
            if(labelField.inline())
            {
                colourField.addClass(Row);
                colourField.getInput().addClass(Col);
                label.addClass(Col);
            }
        }


        colourField.bind(formm.getFieldVariableName(field));
        if (annotation.required())
        {
            colourField.getInput()
                    .setRequired();
        }
        if (annotation.showControlFeedback())
        {
            colourField.setStyleInputGroupTextWithValidation(true);
        }
        if (!annotation.classes()
                .isEmpty())
        {
            colourField.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty())
        {
            colourField.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.placeholder()
                .isEmpty())
        {
            colourField.getInput()
                    .setPlaceholder(annotation.placeholder());
        }
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            colourField.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            colourField.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, colourField.getInput());

        return colourField;

    }

}
