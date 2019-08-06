package com.jwebmp.plugins.quickformstest;

import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.annotations.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BSQuickFormTest

{
	private BSQuickForm form;

	@BeforeEach
	public void setUp()
	{
		form = new BSQuickForm(new Dto());
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
		@TextField
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

}
