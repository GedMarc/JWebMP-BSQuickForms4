import com.jwebmp.plugins.bs4.quickforms.components.*;
import com.jwebmp.plugins.quickforms.services.IAnnotationFieldHandler;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperEnd;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperStart;

module com.jwebmp.plugins.bs4.quickforms {
    exports com.jwebmp.plugins.bs4.quickforms;
    exports com.jwebmp.plugins.bs4.quickforms.components;
    exports com.jwebmp.plugins.bs4.quickforms.annotations.implementations;
    exports com.jwebmp.plugins.bs4.quickforms.annotations.actions;
    requires com.jwebmp.plugins.quickforms;
    requires com.guicedee.guicedinjection;

    requires org.apache.commons.text;
    requires transitive com.jwebmp.core.angular;
    requires com.jwebmp.plugins.bootstrap;
    
    provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.bs4.quickforms.BSQuickFormsPageConfigurator;

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
}
