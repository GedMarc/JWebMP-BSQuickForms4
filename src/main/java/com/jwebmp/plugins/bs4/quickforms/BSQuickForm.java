/*
 * Copyright (C) 2017 GedMarc
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
package com.jwebmp.plugins.bs4.quickforms;

import com.guicedee.guicedinjection.GuiceContext;
import com.guicedee.logger.LogFactory;
import com.jwebmp.core.Event;
import com.jwebmp.core.base.html.H1;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.plugins.ComponentInformation;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.groups.BSFormGroup;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bs4.quickforms.annotations.implementations.DefaultLabelField;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.*;
import com.jwebmp.plugins.quickforms.annotations.states.WebReadOnly;
import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

import static com.guicedee.guicedinjection.json.StaticStrings.*;

/**
 * The Bootstrap 4 Implementation of the Quick Forms API
 *
 * @param <J>
 *
 * @author GedMarc
 * @since 25 Mar 2017
 */
@ComponentInformation(name = "Bootstrap QuickForm",description = "The implementation of the quick form for Bootstrap")
public class BSQuickForm<J extends BSQuickForm<J>>
		extends QuickForms<BSFormGroup<?, ?>, J>
{
	private static final Logger log = LogFactory.getLog("BSQuickForms4");

	/**
	 * Constructs a new BSQuickForm
	 */
	public BSQuickForm(@NotNull Object anything)
	{
		this(anything, anything.getClass().getCanonicalName().replace(CHAR_DOT,CHAR_UNDERSCORE));
	}

	public BSQuickForm(@NotNull Object anything,String formName)
	{
		setObject(anything);
		setForm(new BSForm<>());
		getForm().setID(formName);
		getForm().setName(formName);
	}

	@Override
	public void processDefaults(Field field, BSFormGroup<?, ?> groupContent)
	{
		Class<?> fieldType = field.getType();
		String typeName = fieldType.getSimpleName();

	}

	@Override
	public void configureReadOnly(BSFormGroup<?, ?> bsFormGroup, Field field)
	{
		if (bsFormGroup != null && (isReadOnlyOverride() || field.isAnnotationPresent(WebReadOnly.class)))
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
	
	

	public Optional<LabelField> getLabelFromField(Field field)
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
				public String value()
				{
					return field.getName();
				}
			};
			return Optional.of(lf);
		}
		return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> toOptions(Object object, Class classType)
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

	public BSFormGroup<?,?> buildSubHeaderField(Field field, SubHeaderField annotation, BSFormGroup<?,?> fieldGroup)
	{
		BSFormGroup<?,?> group = new BSFormGroup();
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
	public void init() {
		super.init();
		getForm().setStyleInput(true);
	}

	public BSFormGroup<?,?> buildHeaderField(Field field, HeaderField annotation, BSFormGroup<?,?> fieldGroup)
	{
		BSFormGroup<?,?> group = new BSFormGroup();
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
	public BSButton<?> buildSubmitButton(Field field, SubmitButtonField annotation, BSFormGroup<?,?> fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .value();

		}
		BSButton<?> button = getForm().createSubmitButton();
		button.setText(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			button.addClass(annotation.classes());
		}
		Event<?,?>  adapter = (Event<?, ?>) GuiceContext.get(annotation.eventClass());
		adapter.setComponent(button);
		button.addEvent(adapter);

		return button;
	}

	@Override
	public BSButton<?> buildCancelButton(Field field, CancelButtonField annotation, BSFormGroup<?,?> fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .value();
		}

		BSButton<?> button = getForm().createCancelButton();
		button.setText(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			button.addClass(annotation.classes());
		}
		Event<?,?> adapter = (Event<?, ?>) GuiceContext.get(annotation.eventClass());
		adapter.setComponent(button);
		button.addEvent(adapter);

		return button;
	}

	@Override
	public BSButton<?> buildResetButton(Field field, ResetButtonField annotation, BSFormGroup<?,?> fieldGroup)
	{
		String label = null;
		if (getLabelFromField(field).isPresent())
		{
			label = getLabelFromField(field).get()
			                                .value();

		}

		BSButton<?> button = getForm().createResetButton();
		button.setText(label);
		if (!annotation.classes()
		               .isEmpty())
		{
			button.addClass(annotation.classes());
		}
		Event<?,?>  adapter = (Event<?, ?>) GuiceContext.get(annotation.eventClass());
		adapter.setComponent(button);
		button.addEvent(adapter);

		return button;
	}
}
