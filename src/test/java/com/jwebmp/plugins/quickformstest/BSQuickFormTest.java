package com.jwebmp.plugins.quickformstest;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.Form;
import com.jwebmp.core.base.html.inputs.InputFileType;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.blueimp.fileupload.parts.BlueImpUploadButtonBar;
import com.jwebmp.plugins.blueimp.fileupload.parts.BlueImpUploadForm;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap4.forms.groups.BSFormGroup;
import com.jwebmp.plugins.bootstrap4.forms.groups.enumerations.BSFormGroupOptions;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bootstrap4.options.BSDefaultOptions;
import com.jwebmp.plugins.bs4.quickforms.BSQuickForm;
import com.jwebmp.plugins.quickforms.annotations.*;
import com.jwebmp.plugins.quickforms.annotations.states.WebField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

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
	public void testClassAddition2()
	{
		Div d = new Div<>();
		d.addClass(BSDefaultOptions.Active);
		System.out.println(d.toString(0));
		
		BSForm<?> form = new BSForm<>().setID("outer form");
		BSFormInputGroup<?, ?> group = new BSFormInputGroup<>();
		
		BlueImpUploadForm<?> jBlueImpUploadForm = new BlueImpUploadForm<>();
		jBlueImpUploadForm.addButtonBar();
		group.add(jBlueImpUploadForm);
		
		form.add(group);
		
		System.out.println(form.toString(0));
		
		System.out.println("DTTTOOOOOO");
		
		Dto dto = new Dto();
		BSQuickForm<?> quickForm = new BSQuickForm<>(dto);
		System.out.println(quickForm.toString(0));
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
		fieldGroup.setAddInput(false);
		fieldGroup.addLabel(label);
		
		BlueImpUploadForm<?> b = new BlueImpUploadForm<>();
		BlueImpUploadButtonBar<?> blueImpUploadButtonBar = b.addButtonBar()
		                                                    .addAddButton("btn btn-success", "fas fa-plus", "Add", false)
		                                                    .addStartButton("btn btn-primary", "fas fa-upload", "Start Upload")
		                                                    .addCancelButton("btn btn-warning", "fas fa-do-not-enter", "Cancel")
		                                                    .addDeleteSelected("btn btn-danger", "fas fa-times", "Delete Selected")
		                                                    .addDeleteCheckbox("", "fa fas-cancel", "Delete Selected")
		                                                    .addGlobalFileProcessingState()
		                                                    .addGlobalProgressState();
		b.addDisplayTable();
		fieldGroup.setInput(blueImpUploadButtonBar.getFileInput());
		fieldGroup.add(b);
		
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
			extends JavaScriptPart<Dto>
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
