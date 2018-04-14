package com.armineasy.jwebswing.plugins.quickforms;

import com.jwebmp.BaseTestClass;
import com.jwebmp.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.bootstrap.forms.groups.BSFormGroup;
import com.jwebmp.plugins.quickforms.events.QuickFormsCancelEvent;
import com.jwebmp.plugins.quickforms.events.QuickFormsClearEvent;
import com.jwebmp.plugins.quickforms.events.QuickFormsSubmitEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BSQuickFormTest
		extends BaseTestClass
{
	private BSQuickForm form;

	@BeforeEach
	public void setUp()
	{
		form = new BSQuickFormImpl(new Dto());
	}

	@Test
	public void buildForm()
	{
		System.out.println(form.toString(true));
	}

	@Test
	public void buildTextForm()
	{
	}

	@Test
	public void buildDatePicker()
	{
	}

	@Test
	public void buildEmailField()
	{
	}

	@Test
	public void buildPasswordField()
	{
	}

	@Test
	public void buildColourField()
	{
	}

	@Test
	public void buildCheckboxField()
	{
	}

	@Test
	public void buildFileUploadField()
	{
	}

	@Test
	public void buildPrettyCheckbox()
	{

	}

	@Test
	public void buildFieldLabel()
	{
	}

	@Test
	public void buildRadioField()
	{
	}

	@Test
	public void buildSearchField()
	{
	}

	@Test
	public void buildSelectField()
	{
	}

	@Test
	public void buildTelephoneField()
	{
	}

	@Test
	public void buildTextAreaField()
	{
	}

	@Test
	public void buildTimeField()
	{
	}

	@Test
	public void buildUrlField()
	{
	}

	public class Dto
			extends JavaScriptPart
	{
		private String name;
		private String description;
		private String textLine;
		private String textArea;
		private boolean booleanField;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getDescription()
		{
			return description;
		}

		public void setDescription(String description)
		{
			this.description = description;
		}

		public String getTextLine()
		{
			return textLine;
		}

		public void setTextLine(String textLine)
		{
			this.textLine = textLine;
		}

		public String getTextArea()
		{
			return textArea;
		}

		public void setTextArea(String textArea)
		{
			this.textArea = textArea;
		}
	}

	class BSQuickFormImpl<G extends BSFormGroup<G>, J extends BSQuickFormImpl<G, J>>
			extends BSQuickForm<Dto, G, J>
	{

		/**
		 * Constructs a new BSQuickForm
		 *
		 * @param anything
		 */
		public BSQuickFormImpl(Dto anything)
		{
			super(anything);
		}

		@Override
		protected G buildFieldGroup()
		{
			return null;
		}

		@Override
		protected Class<? extends QuickFormsSubmitEvent> onSubmit()
		{
			return null;
		}

		@Override
		protected Class<? extends QuickFormsCancelEvent> onCancel()
		{
			return null;
		}

		@Override
		protected Class<? extends QuickFormsClearEvent> onClear()
		{
			return null;
		}
	}
}
