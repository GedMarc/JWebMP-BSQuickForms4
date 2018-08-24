/*
 * Copyright (C) 2017 Marc Magon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.plugins.bsquickforms4;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.H1;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.inputs.*;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.core.utilities.StaticStrings;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.groups.BSFormGroup;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormCheckGroup;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormRadioGroup;
import com.jwebmp.plugins.bootstraptoggle.BSToggle;
import com.jwebmp.plugins.bs4datetimepicker.BS4DateTimePicker;
import com.jwebmp.plugins.bsquickforms4.annotations.implementations.*;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.*;
import com.jwebmp.plugins.quickforms.annotations.states.WebReadOnly;
import com.jwebmp.plugins.quickforms.annotations.states.WebReadOnlyPlainText;
import org.apache.commons.text.StringEscapeUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Bootstrap 4 Implementation of the Quick Forms API
 *
 * @param <J>
 *
 * @author Marc Magon
 * @since 25 Mar 2017
 */
public class BSQuickForm<J extends BSQuickForm<J>>
		extends QuickForms<BSFormGroup<?, ?>, J>
{

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogFactory.getLog("BSQuickForms4");

	/**
	 * Constructs a new BSQuickForm
	 */
	public BSQuickForm(@NotNull Object anything)
	{
		this();
		setObject(anything);
	}

	public BSQuickForm()
	{
		setForm(new BSForm<>());
	}

	@Override
	protected void processDefaults(Field field, BSFormGroup<?, ?> groupContent)
	{
		Class fieldType = field.getType();
		String typeName = fieldType.getSimpleName();

		BSFormGroup group = null;
		if (fieldType.isEnum())
		{
			typeName = "Enum";
		}
		if (fieldType.isArray())
		{
			BSQuickForm.log.warning("No implementation for array type found");
		}
		else if (JavaScriptPart.class.isAssignableFrom(fieldType))
		{
			group = buildTextField(field, new DefaultTextField()
			{}, group);
			group.setReadOnly(true);
			group.getInput()
			     .bind(null)
			     .setValue(StringEscapeUtils.escapeHtml4(fieldType.getSimpleName()))
			     .addClass(BSFormGroupOptions.Form_Control_PlainText)
			     .setText(StringEscapeUtils.escapeHtml4(fieldType.getSimpleName()));
		}
		else if (ComponentHierarchyBase.class.isAssignableFrom(fieldType))
		{
			group = buildTextField(field, new DefaultTextField()
			{

			}, group);
			group.setReadOnly(true);
			group.getInput()
			     .bind(null)
			     .setValue(StringEscapeUtils.escapeHtml4(fieldType.getSimpleName()))
			     .addClass(BSFormGroupOptions.Form_Control_PlainText)
			     .setText(StringEscapeUtils.escapeHtml4(fieldType.getSimpleName()));
		}
		else
		{
			buildDefaultGroups(group, field, typeName);
		}
		if (group != null)
		{
			setValue(field, group.getInput());
		}
	}

	@Override
	protected void configureReadOnly(BSFormGroup<?, ?> bsFormGroup, Field field)
	{
		if (bsFormGroup != null && field.isAnnotationPresent(WebReadOnlyPlainText.class))
		{
			bsFormGroup.getInput()
			           .addClass(BSFormGroupOptions.Form_Control_PlainText);
		}
		else if (bsFormGroup != null && !(isReadOnlyOverride() || field.isAnnotationPresent(WebReadOnly.class)))
		{
			bsFormGroup.getInput()
			           .addAttribute("readonly", "");
		}
	}

	@Override
	public BSForm<?> getForm()
	{
		return (BSForm<?>) super.getForm();
	}

	@Override
	public BSFormGroup buildTextField(Field field, TextField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputTextType<?>> textInput = getForm().createTextInput(getFieldVariableName(field), label, true);

		if (annotation.showControlFeedback())
		{
			textInput.setStyleInputGroupTextWithValidation(true);
		}
		textInput.setInput(new InputTextType<>());
		textInput.getInput()
		         .bind(getFieldVariableName(field));

		if (annotation.required())
		{
			textInput.getInput()
			         .setRequired();
		}

		if (!annotation.classes()
		               .isEmpty())
		{
			textInput.getInput()
			         .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			textInput.getInput()
			         .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			textInput.asMe()
			         .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			textInput.asMe()
			         .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}
		return textInput;
	}

	private BSFormGroup buildDefaultGroups(BSFormGroup group, Field field, String typeName)
	{
		switch (typeName)
		{
			case "String":
			{
				group = buildTextField(field, new DefaultTextField(), group);
				break;
			}
			case "Enum":
			{
				group = buildSelectField(field, new DefaultSelectField(), group);
				break;
			}
			case "Integer":
			{
				group = buildNumberField(field, new DefaultNumberField(), group);
				break;
			}
			case "Double":
			{
				group = buildNumberField(field, new DefaultNumberField(), group);
				break;
			}
			case "BigDecimal":
			{
				group = buildNumberField(field, new DefaultNumberField(), group);
				break;
			}
			case "Boolean":
			{
				group = buildCheckboxField(field, new DefaultCheckboxField(), group);
				break;
			}
			case "Short":
			{
				group = buildNumberField(field, new DefaultNumberField(), group);
				break;
			}
			default:
			{
				group = buildTextField(field, new DefaultTextField(), group);
				break;
			}

		}
		return group;
	}

	protected Optional<LabelField> getLabelFromField(Field field)
	{
		if (field.isAnnotationPresent(LabelField.class))
		{
			return Optional.of(field.getAnnotation(LabelField.class));
		}
		else if (isRenderDefaults())
		{
			LabelField lf = new DefaultLabelField()
			{
				@Override
				public String label()
				{
					return field.getName();
				}
			};
			return Optional.of(lf);
		}
		return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> toOptions(Object object, Class classType)
	{
		Map<String, String> maps = new ConcurrentSkipListMap<>();
		boolean isEnum = classType.isEnum();
		boolean isArray = classType.isArray();
		boolean isCollection = Collection.class.isAssignableFrom(classType);
		boolean isMap = Map.class.isAssignableFrom(classType);

		if (!(isArray || isCollection || isMap || isEnum))
		{
			BSQuickForm.log.warning("Where In List Clause was not an array collection or map");
			return new HashMap<>();
		}
		if (isEnum)
		{
			Object[] enums = classType.getEnumConstants();
			for (Object o : enums)
			{
				Enum e = (Enum) o;
				maps.put(e.name(), e.toString());
			}
		}
		else if (isArray)
		{
			Object[] arrs = (Object[]) object;
			for (Object arr : arrs)
			{
				maps.put(arr.toString(), arr.toString());
			}
		}
		else if (isCollection)
		{
			Collection collection = (Collection) object;
			collection.forEach(a -> maps.put(a.toString(), a.toString()));
		}
		else
		{
			Map map = (Map) object;
			map.forEach((key, value) ->
			            {
				            if (value != null)
				            {
					            maps.put(key.toString(), value.toString());
				            }
			            });
		}
		return maps;
	}

	@Override
	public BS4DateTimePicker buildDateTimePicker(Field field, DateTimePickerField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();
		}
		BS4DateTimePicker<?> dateTimePicker = new BS4DateTimePicker<>();
		dateTimePicker.asMe()
		              .addLabel(label);
		dateTimePicker.getInput()
		              .bind(getFieldVariableName(field));
		if (annotation.required())
		{

			dateTimePicker.getInput()
			              .setRequired();
		}
		if (annotation.showControlFeedback())
		{
			dateTimePicker.asMe()
			              .setStyleInputGroupTextWithValidation(true);
		}
		if (!annotation.classes()
		               .isEmpty())
		{
			dateTimePicker.getInput()
			              .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			dateTimePicker.getInput()
			              .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			dateTimePicker.asMe()
			              .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			dateTimePicker.asMe()
			              .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}
		getForm().add(dateTimePicker);
		return dateTimePicker;
	}

	@Override
	public BSFormInputGroup<?, InputDateType<?>> buildDatePicker(Field field, DatePickerField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();
		}
		BSFormInputGroup<?, InputDateType<?>> dateInputGroup = getForm().createDateInput(getFieldVariableName(field), label, true);
		dateInputGroup.getInput()
		              .bind(getFieldVariableName(field));
		if (annotation.required())
		{
			dateInputGroup.getInput()
			              .setRequired();
		}
		if (annotation.showControlFeedback())
		{
			dateInputGroup.setStyleInputGroupTextWithValidation(true);
		}
		if (!annotation.classes()
		               .isEmpty())
		{
			dateInputGroup.getInput()
			              .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			dateInputGroup.getInput()
			              .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			dateInputGroup.asMe()
			              .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			dateInputGroup.asMe()
			              .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}

		return dateInputGroup;
	}

	@Override
	public BSFormGroup buildEmailField(Field field, EmailField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputEmailType<?>> emailField = getForm().createEmailInput(getFieldVariableName(field), label, true);
		emailField.setInput(new InputEmailType<>());
		emailField.getInput()
		          .bind(getFieldVariableName(field));
		if (annotation.required())
		{
			emailField.getInput()
			          .setRequired();
		}
		if (annotation.showControlFeedback())
		{
			emailField.setStyleInputGroupTextWithValidation(true);
		}
		if (!annotation.classes()
		               .isEmpty())
		{
			emailField.getInput()
			          .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			emailField.getInput()
			          .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			emailField.asMe()
			          .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			emailField.asMe()
			          .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}
		return emailField;
	}

	@Override
	public BSFormGroup buildSubHeaderField(Field field, SubHeaderField annotation, BSFormGroup fieldGroup)
	{
		BSFormGroup group = new BSFormGroup();
		H3 label = new H3<>(annotation.label());
		group.add(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			label.addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			label.addStyle(annotation.style());
		}
		if (annotation.showControlFeedback())
		{
			label.addClass(BSFormGroupOptions.Form_Control_Feedback);
		}
		getForm().add(group);
		return group;
	}

	@Override
	public BSFormGroup buildHeaderField(Field field, HeaderField annotation, BSFormGroup fieldGroup)
	{
		BSFormGroup group = new BSFormGroup();
		H1 label = new H1<>(annotation.label());
		group.add(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			label.addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			label.addStyle(annotation.style());
		}
		if (annotation.showControlFeedback())
		{
			label.addClass(BSFormGroupOptions.Form_Control_Feedback);
		}
		getForm().add(group);
		return group;
	}

	@Override
	public BSFormGroup buildFieldLabel(Field field, LabelField annotation, BSFormGroup fieldGroup)
	{
		BSFormGroup group = new BSFormGroup();
		Label label = new Label<>(annotation.label());
		group.add(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			label.addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			label.addStyle(annotation.style());
		}
		if (annotation.showControlFeedback())
		{
			label.addClass(BSFormGroupOptions.Form_Control_Feedback);
		}
		getForm().add(group);
		return group;
	}

	@Override
	public BSFormGroup buildPasswordField(Field field, PasswordField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputPasswordType<?>> passwordField = getForm().createPasswordInput(getFieldVariableName(field), label, true);
		passwordField.setInput(new InputPasswordType<>());
		passwordField.bind(getFieldVariableName(field));
		if (annotation.required())
		{
			passwordField.getInput()
			             .setRequired();
		}
		if (annotation.showControlFeedback())
		{
			passwordField.setStyleInputGroupTextWithValidation(true);
		}
		if (!annotation.classes()
		               .isEmpty())
		{
			passwordField.getInput()
			             .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			passwordField.getInput()
			             .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			passwordField.asMe()
			             .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			passwordField.asMe()
			             .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}
		return passwordField;
	}

	@Override
	public BSFormGroup buildColourField(Field field, ColorField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputColourType<?>> colourField = new BSFormInputGroup<>();
		colourField.addHelpText(label);
		colourField.setInput(new InputColourType<>());
		colourField.bind(getFieldVariableName(field));
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
		return colourField;
	}

	@Override
	public BSFormGroup<?, ?> buildCheckboxField(Field field, CheckboxField annotation, BSFormGroup<?, ?> fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();
		}
		BSFormCheckGroup<?> checkboxField = getForm().createCheckboxInput(getFieldVariableName(field), label);
		checkboxField.setInput(new InputCheckBoxType<>());
		checkboxField.getInput()
		             .bind(getFieldVariableName(field));
		if (annotation.required())
		{
			checkboxField.getInput()
			             .setRequired();
		}
		if (!annotation.classes()
		               .isEmpty())
		{
			checkboxField.getInput()
			             .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			checkboxField.getInput()
			             .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			checkboxField.asMe()
			             .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			checkboxField.asMe()
			             .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}

		return checkboxField;
	}

	@Override
	public BSFormInputGroup<?, InputFileType<?>> buildFileUploadField(Field field, FileField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();
		}
		BSFormInputGroup<?, InputFileType<?>> fileUploadField = getForm().createFileInput(getFieldVariableName(field), label, true);
		fileUploadField.setInput(new InputFileType<>());
		fileUploadField.getInput()
		               .addAttribute("ng-file-model", getFieldVariableName(field));
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

	@Override
	public BSFormGroup buildRadioField(Field field, RadioField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();
		}
		BSFormRadioGroup<?> radioButtonField = getForm().createRadioInput(getFieldVariableName(field), label, annotation.group());
		radioButtonField.setInput(new InputRadioType<>());
		radioButtonField.bind(getFieldVariableName(field));
		if (annotation.required())
		{
			radioButtonField.getInput()
			                .setRequired();
		}
		if (!annotation.classes()
		               .isEmpty())
		{
			radioButtonField.getInput()
			                .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			radioButtonField.getInput()
			                .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			radioButtonField.asMe()
			                .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			radioButtonField.asMe()
			                .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}

		return radioButtonField;
	}

	@Override
	public BSFormGroup buildSearchField(Field field, SearchField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();
		}
		BSFormInputGroup<?, InputSearchType<?>> searchField = getForm().createSearchInput(getFieldVariableName(field), label, true);
		searchField.addHelpText(label);
		searchField.setInput(new InputSearchType<>());
		searchField.getInput()
		           .bind(getFieldVariableName(field));

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

		return searchField;
	}

	@Override
	public BSFormGroup<?, ?> buildNumberSpinnerField(Field field, NumberSpinnerField annotation, BSFormGroup<?, ?> fieldGroup)
	{
		return null;
	}

	@Override
	public BSFormGroup buildSwitchField(Field field, SwitchField annotation, BSFormGroup fieldGroup)
	{
		BSToggle input = new BSToggle();
		if (getLabelFromField(field).isPresent())
		{
			String label = getLabelFromField(field).get()
			                                       .label();
			input.setOnText(label);
		}
		input.bind(getID() + StaticStrings.STRING_DOT + field.getName());
		fieldGroup.setInput(input);

		if (annotation.required())
		{
			input.setRequired();
		}
		if (!annotation.onText()
		               .isEmpty())
		{
			input.setOnText(annotation.onText());
		}
		if (!annotation.offText()
		               .isEmpty())
		{
			input.setOffText(annotation.offText());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			fieldGroup.addMessage(InputErrorValidations.pattern, annotation.patternMessage());
		}

		setValue(field, input);

		return fieldGroup;
	}

	@Override
	public BSFormGroup buildSelectField(Field field, SelectField annotation, BSFormGroup fieldGroup)
	{
		field.setAccessible(true);
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();
		}
		BSFormInputGroup<?, InputSelectType<?>> selectGroup = getForm().createSelectDropdown(getFieldVariableName(field), label, true);
		InputSelectType input = new InputSelectType();
		input.bind(getFieldVariableName(field));
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
			Object fieldObject = field.get(getObject());
			Map<String, String> keys = toOptions(fieldObject, field.getType());
			keys.forEach((key, value) -> selectGroup.getInput()
			                                        .addOption(key, value));
		}
		catch (Exception e)
		{
			BSQuickForm.log.log(Level.WARNING, "Unable to generate select list", e);
		}
		return selectGroup;
	}

	@Override
	public BSFormGroup buildTelephoneField(Field field, TelephoneField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputTelephoneType<?>> textInput = getForm().createTelephoneInput(getFieldVariableName(field), label, true);

		if (annotation.showControlFeedback())
		{
			textInput.setStyleInputGroupTextWithValidation(true);
		}
		textInput.setInput(new InputTelephoneType<>());
		textInput.getInput()
		         .bind(getFieldVariableName(field));
		if (annotation.required())
		{
			textInput.getInput()
			         .setRequired();
		}

		if (!annotation.classes()
		               .isEmpty())
		{
			textInput.getInput()
			         .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			textInput.getInput()
			         .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			textInput.asMe()
			         .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			textInput.asMe()
			         .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}
		return textInput;
	}

	@Override
	public BSFormInputGroup<?, InputTextAreaType<?>> buildTextAreaField(Field field, TextAreaField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputTextAreaType<?>> textAreaInput = getForm().createTextArea(getFieldVariableName(field), label, true);

		if (annotation.showControlFeedback())
		{
			textAreaInput.setStyleInputGroupTextWithValidation(true);
		}
		textAreaInput.setInput(new InputTextAreaType<>());
		textAreaInput.getInput()
		             .bind(getFieldVariableName(field));
		if (annotation.required())
		{
			textAreaInput.getInput()
			             .setRequired();
		}

		if (!annotation.classes()
		               .isEmpty())
		{
			textAreaInput.getInput()
			             .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			textAreaInput.getInput()
			             .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			textAreaInput.asMe()
			             .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			textAreaInput.asMe()
			             .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}
		return textAreaInput;
	}

	@Override
	public BSFormGroup buildNumberField(Field field, NumberField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputNumberType<?>> numberInput = getForm().createNumberInput(getFieldVariableName(field), label, true);
		if (annotation.showControlFeedback())
		{
			numberInput.setStyleInputGroupTextWithValidation(true);
		}
		numberInput.setInput(new InputNumberType<>());
		numberInput.getInput()
		           .bind(getFieldVariableName(field));
		if (annotation.required())
		{
			numberInput.getInput()
			           .setRequired();
		}
		if (annotation.minimumValue() != Integer.MIN_VALUE)
		{
			numberInput.getInput()
			           .setMinimumLength(annotation.minimumValue());
		}
		if (annotation.maximumValue() != Integer.MIN_VALUE)
		{
			numberInput.getInput()
			           .setMaximumLength(annotation.maximumValue());
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
		return numberInput;
	}

	@Override
	public BSFormGroup buildTimeField(Field field, TimePickerField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputNumberType<?>> timeInput = getForm().createNumberInput(getFieldVariableName(field), label, true);
		if (annotation.showControlFeedback())
		{
			timeInput.setStyleInputGroupTextWithValidation(true);
		}
		timeInput.setInput(new InputNumberType<>());
		timeInput.getInput()
		         .bind(getFieldVariableName(field));
		if (annotation.required())
		{
			timeInput.getInput()
			         .setRequired();
		}

		if (!annotation.classes()
		               .isEmpty())
		{
			timeInput.getInput()
			         .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			timeInput.getInput()
			         .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			timeInput.asMe()
			         .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			timeInput.asMe()
			         .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}
		return timeInput;
	}

	@Override
	public BSFormGroup buildUrlField(Field field, UrlField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormInputGroup<?, InputUrlType<?>> urlInput = getForm().createUrlInput(getFieldVariableName(field), label, true);
		if (annotation.showControlFeedback())
		{
			urlInput.setStyleInputGroupTextWithValidation(true);
		}
		urlInput.setInput(new InputUrlType<>());
		urlInput.getInput()
		        .bind(getFieldVariableName(field));
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
		return urlInput;
	}

	@Override
	public BSFormGroup buildHiddenField(Field field, HiddenField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSFormGroup<?, InputHiddenType<?>> textInput = getForm().createHiddenInput(getFieldVariableName(field), label);
		textInput.setInput(new InputHiddenType<>());
		textInput.getInput()
		         .bind(getFieldVariableName(field));
		if (annotation.required())
		{
			textInput.getInput()
			         .setRequired();
		}

		if (!annotation.classes()
		               .isEmpty())
		{
			textInput.getInput()
			         .addClass(annotation.classes());
		}
		if (!annotation.style()
		               .isEmpty())
		{
			textInput.getInput()
			         .addStyle(annotation.style());
		}
		if (!annotation.requiredMessage()
		               .isEmpty())
		{
			textInput.asMe()
			         .addMessage(InputErrorValidations.required, annotation.requiredMessage());
		}
		if (!annotation.patternMessage()
		               .isEmpty())
		{
			textInput.asMe()
			         .addMessage(InputErrorValidations.pattern, annotation.requiredMessage());
		}
		return textInput;
	}

	@Override
	public ComponentHierarchyBase buildSubmitButton(Field field, SubmitButtonField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}
		BSButton<?> button = getForm().createSubmitButton();
		button.setText(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			button.addClass(annotation.classes());
		}
		ClickAdapter adapter = GuiceContext.getInstance(annotation.eventClass());
		adapter.setComponent(button);
		button.addEvent(adapter);

		return button;
	}

	@Override
	public ComponentHierarchyBase buildCancelButton(Field field, CancelButtonField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}

		BSButton<?> button = getForm().createCancelButton();
		button.setText(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			button.addClass(annotation.classes());
		}
		ClickAdapter adapter = GuiceContext.getInstance(annotation.eventClass());
		adapter.setComponent(button);
		button.addEvent(adapter);

		return button;
	}

	@Override
	public ComponentHierarchyBase buildResetButton(Field field, ResetButtonField annotation, BSFormGroup fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .label();

		}

		BSButton<?> button = getForm().createResetButton();
		button.setText(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			button.addClass(annotation.classes());
		}
		ClickAdapter adapter = GuiceContext.getInstance(annotation.eventClass());
		adapter.setComponent(button);
		button.addEvent(adapter);

		return button;
	}
}
