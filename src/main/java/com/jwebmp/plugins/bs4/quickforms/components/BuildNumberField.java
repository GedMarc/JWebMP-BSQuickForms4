package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.inputs.InputDateType;
import com.jwebmp.core.base.html.inputs.InputNumberType;
import com.jwebmp.plugins.bootstrap.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.bs4.quickforms.annotations.implementations.DefaultNumberField;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.DatePickerField;
import com.jwebmp.plugins.quickforms.annotations.ErrorMessages;
import com.jwebmp.plugins.quickforms.annotations.LabelField;
import com.jwebmp.plugins.quickforms.annotations.NumberField;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;

import java.lang.reflect.Field;

import static com.jwebmp.core.base.html.attributes.InputRangeAttributes.Step;

public class BuildNumberField implements IAnnotationFieldHandler<NumberField, BSFormInputGroup<?, InputNumberType<?>>> {
    @Override
    public NumberField appliedAnnotation() {
        return new DefaultNumberField();
    }

    @Override
    public BSFormInputGroup<?, InputNumberType<?>> buildField(QuickForms<?, ?> form, Field field, NumberField annotation, BSFormInputGroup<?, InputNumberType<?>> fieldGroup) {

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

        if (annotation.step() != 0d) {
            numberInput.getInput().addAttribute("step", annotation.step() + "");
        }

        if(!Strings.isNullOrEmpty(annotation.placeholder()))
        {
            numberInput.getInput().setPlaceholder(annotation.placeholder());
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

        if (!Strings.isNullOrEmpty(annotation.regexBind())) {
            numberInput.getInput().addAttribute("ng-pattern", annotation.regexBind());
        }
        if (!Strings.isNullOrEmpty(annotation.regex())) {
            numberInput.getInput().addAttribute("pattern", annotation.regex());
        }

        if (field.isAnnotationPresent(ErrorMessages.class)) {
            ErrorMessages em = field.getAnnotation(ErrorMessages.class);
        //    numberInput.getMessages().setShowOnEdit(true);
            numberInput.getMessages().addMessage(InputErrorValidations.min, em.minMessage(),em.inline());
            numberInput.getMessages().addMessage(InputErrorValidations.minLength, em.minLengthMessage(),em.inline());
            numberInput.getMessages().addMessage(InputErrorValidations.max, em.maxMessage(),em.inline());
            numberInput.getMessages().addMessage(InputErrorValidations.maxLength, em.maxLengthMessage(),em.inline());
            numberInput.getMessages().addMessage(InputErrorValidations.pattern, em.patternMessage(),em.inline());
            numberInput.getMessages().addMessage(InputErrorValidations.required, em.requiredMessage(),em.inline());
        }
        form.setValue(field, numberInput.getInput());

        return numberInput;
    }

}
