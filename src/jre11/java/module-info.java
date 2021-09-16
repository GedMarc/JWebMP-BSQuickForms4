import com.jwebmp.plugins.bs4.quickforms.components.*;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperEnd;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperStart;

module com.jwebmp.plugins.bs4.quickforms {
    exports com.jwebmp.plugins.bs4.quickforms;
    exports com.jwebmp.plugins.bs4.quickforms.components;
   // exports com.jwebmp.plugins.bs4.quickforms.annotations;
    exports com.jwebmp.plugins.bs4.quickforms.annotations.implementations;
    exports com.jwebmp.plugins.bs4.quickforms.annotations.actions;

    requires com.jwebmp.core;
    requires com.guicedee.logmaster;

    requires jakarta.validation;

    requires com.jwebmp.plugins.bootstrap4;
    requires com.jwebmp.plugins.quickforms;
    requires com.jwebmp.plugins.bs4.datetimepicker;
    requires com.jwebmp.plugins.bs4.toggle;
    requires com.guicedee.guicedinjection;
    requires com.jwebmp.plugins.blueimp.fileupload;
    requires com.jwebmp.plugins.bs4.nyaselect;
    requires com.jwebmp.plugins.spectrum.colourpicker;

    requires java.logging;
    requires org.apache.commons.text;
    requires com.jwebmp.core.angularjs;

    provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.bs4.quickforms.BSQuickFormsPageConfigurator;

    provides com.guicedee.guicedinjection.interfaces.IGuiceScanModuleExclusions with com.jwebmp.plugins.bs4.quickforms.implementations.BSQuickForms4ExclusionsModule;

    provides IAnnotationFieldHandler with BuildTextField,
            BuildCheckboxField,
            BuildColourField,
            BuildDateField,
            BuildDateTimeField,
            BuildEmailField,
            BuildFileUploadField,
            BuildHiddenField,
            BuildNumberField,
            BuildPasswordField,
            BuildRadioField,
            BuildSearchField,
            BuildSelectField,
            BuildTelephoneField,
            BuildTextAreaField,
            BuildTimeField,
            BuildUrlField,
            BuildReadOnlyPlainTextField;


    provides IFormFieldWrapperStart with BuildFormRow;
    provides IFormFieldWrapperEnd with BuildFormRowEnd;

    opens com.jwebmp.plugins.bs4.quickforms.annotations.actions to com.google.guice, com.fasterxml.jackson.databind, com.jwebmp.core;
    opens com.jwebmp.plugins.bs4.quickforms.annotations.implementations to com.google.guice,com.fasterxml.jackson.databind, com.jwebmp.core;
    opens com.jwebmp.plugins.bs4.quickforms.components to com.google.guice,com.fasterxml.jackson.databind, com.jwebmp.core;

    opens com.jwebmp.plugins.bs4.quickforms to com.google.guice,com.fasterxml.jackson.databind, com.jwebmp.core;
    opens com.jwebmp.plugins.bs4.quickforms.implementations to com.google.guice,com.fasterxml.jackson.databind, com.jwebmp.core;
}
