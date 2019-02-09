import com.jwebmp.plugins.bs4.quickforms.BSQuickFormsPageConfigurator;

module com.jwebmp.plugins.bs4.quickforms {
	exports com.jwebmp.plugins.bs4.quickforms;
	exports com.jwebmp.plugins.bs4.quickforms.annotations.implementations;
	exports com.jwebmp.plugins.bs4.quickforms.annotations.actions;

	requires com.jwebmp.core;
	requires com.jwebmp.logmaster;
	requires com.fasterxml.jackson.annotation;

	requires java.validation;

	requires com.jwebmp.plugins.bootstrap4;
	requires com.jwebmp.plugins.quickforms;
	requires com.jwebmp.plugins.bs4.datetimepicker;
	requires com.jwebmp.plugins.bs4.toggle;
	requires com.jwebmp.guicedinjection;

	requires java.logging;
	requires org.apache.commons.text;
	requires com.jwebmp.core.angularjs;

	provides com.jwebmp.core.services.IPageConfigurator with BSQuickFormsPageConfigurator;

	provides com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions with com.jwebmp.plugins.bs4.quickforms.implementations.BSQuickForms4ExclusionsModule;
	provides com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions with com.jwebmp.plugins.bs4.quickforms.implementations.BSQuickForms4ExclusionsModule;

	opens com.jwebmp.plugins.bs4.quickforms.annotations.actions to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.bs4.quickforms.annotations.implementations to com.fasterxml.jackson.databind, com.jwebmp.core;

	opens com.jwebmp.plugins.bs4.quickforms to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.bs4.quickforms.implementations to com.fasterxml.jackson.databind, com.jwebmp.core;
}
