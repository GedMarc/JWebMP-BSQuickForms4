package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputNumberType;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.NumberField;
import com.jwebmp.plugins.quickforms.annotations.NumberSpinnerField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BuildNumberSpinnerField implements IAnnotationFieldHandler<NumberSpinnerField, BSFormInputGroup<?, InputNumberType<?>>> {
    @Override
    public NumberSpinnerField appliedAnnotation() {
        return new NumberSpinnerField(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return NumberSpinnerField.class;
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
            public int minimumValue() {
                return 0;
            }

            @Override
            public int maximumValue() {
                return 0;
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
    public BSFormInputGroup<?, InputNumberType<?>> buildField(QuickForms<?, ?> form, Field field, NumberSpinnerField annotation, BSFormInputGroup<?, InputNumberType<?>> fieldGroup) {

        BSQuickForm<?> formm = (BSQuickForm<?>) form;
        String label = null;
        if (formm.getLabelFromField(field).isPresent())
        {
            label = formm.getLabelFromField(field).get()
                    .value();

        }
        BSFormInputGroup<?, InputNumberType<?>> numberInput = formm.getForm().createNumberInput(formm.getFieldVariableName(field), label, true);
        if (annotation.showControlFeedback())
        {
            numberInput.setStyleInputGroupTextWithValidation(true);
        }
        numberInput.setInput(new InputNumberType<>());
        numberInput.getInput()
                .bind(formm.getFieldVariableName(field));
        if (annotation.required())
        {
            numberInput.getInput()
                    .setRequired();
        }
        if (annotation.minimumValue() != Integer.MIN_VALUE)
        {
            numberInput.getInput()
                    .setMinimum(annotation.minimumValue());
        }
        if (annotation.maximumValue() != Integer.MIN_VALUE)
        {
            numberInput.getInput()
                    .setMaximum(annotation.maximumValue());
        }

        if (!annotation.classes()
                .isEmpty())
        {
            numberInput.getInput()
                    .addClass(annotation.classes());
        }
        if (!annotation.style()
                .isEmpty())
        {
            numberInput.getInput()
                    .addStyle(annotation.style());
        }
        if (!annotation.requiredMessage()
                .isEmpty())
        {
            numberInput.asMe()
                    .addMessage(InputErrorValidations.required, annotation.requiredMessage());
        }
        if (!annotation.patternMessage()
                .isEmpty())
        {
            numberInput.asMe()
                    .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
        }
        form.setValue(field, numberInput.getInput());

        return numberInput;

    }

}
