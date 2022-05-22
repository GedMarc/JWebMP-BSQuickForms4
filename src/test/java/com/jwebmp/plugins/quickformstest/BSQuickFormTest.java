package com.jwebmp.plugins.quickformstest;

import com.jwebmp.core.base.angular.services.interfaces.*;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.base.html.inputs.*;
import com.jwebmp.core.htmlbuilder.javascript.*;
import com.jwebmp.plugins.bootstrap.forms.*;
import com.jwebmp.plugins.bootstrap.forms.groups.*;
import com.jwebmp.plugins.bootstrap.forms.groups.enumerations.*;
import com.jwebmp.plugins.bootstrap.forms.groups.sets.*;
import com.jwebmp.plugins.bootstrap.options.*;
import com.jwebmp.plugins.bs4.quickforms.*;
import com.jwebmp.plugins.quickforms.annotations.*;
import com.jwebmp.plugins.quickforms.annotations.states.*;
import org.junit.jupiter.api.*;

import java.lang.annotation.*;

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
	public void testClassAddition()
	{
		Div d = new Div<>();
		d.addClass(BSDefaultOptions.Active);
		System.out.println(d.toString(0));
		
		BSForm<?> form = new BSForm<>().setID("outer form");
		BSFormInputGroup<?, ?> group = new BSFormInputGroup<>();
		
		group.add(new Form<>().setID("inner form"));
		
		form.add(group);
		
		System.out.println(form.toString(0));
	}
	
	@Test
	public void buildTextForm()
	{
	}
	
	@Test
	public void buildImageForm()
	{
		BSForm<?> form = new BSForm<>();
		
		BSFormLabel<?> label = new BSFormLabel<>();
		LabelField labelField = new LabelField(){
			@Override
			public Class<? extends Annotation> annotationType()
			{
				return LabelField.class;
			}
			
			@Override
			public String value()
			{
				return "label value";
			}
			
			@Override
			public boolean inline()
			{
				return false;
			}
			
			@Override
			public String style()
			{
				return "";
			}
			
			@Override
			public String classes()
			{
				return "";
			}
			
			@Override
			public boolean showControlFeedback()
			{
				return true;
			}
		};
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
		
		BSFormGroup<?, InputFileType<?>> fieldGroup = new BSFormGroup<>();
		fieldGroup.setAddInput(true);
		fieldGroup.addLabel(label);
		
		form.add(fieldGroup);
		
		form.setStyleInput(true);
		form.applyClassesToForm(form);
		
		System.out.println(form.toString(0));
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

	public static class Dto
			extends JavaScriptPart<Dto> implements INgDataType<Dto>
	{
		@TextField
		@WebField
		private String name;
		@WebField
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
