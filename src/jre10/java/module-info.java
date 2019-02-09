import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions;
import com.jwebmp.plugins.bsquickforms4.QuickFormsPageConfigurator;
import com.jwebmp.plugins.bs4.quickforms.implementations.BSQuickForms4ExclusionsModule;

module com.jwebmp.plugins.bsquickforms4 {
	exports com.jwebmp.plugins.bsquickforms4;

	requires com.jwebmp.core;
	requires com.jwebmp.logmaster;
	requires com.fasterxml.jackson.annotation;

	requires java.validation;

	requires com.jwebmp.plugins.bootstrap4;
	requires com.jwebmp.plugins.quickforms;
	requires com.jwebmp.plugins.bs4datetimepicker;
	requires com.jwebmp.plugins.bootstrap.switch4;
	requires com.jwebmp.guicedinjection;

	requires java.logging;
	requires org.apache.commons.text;

	provides IPageConfigurator with QuickFormsPageConfigurator;

	provides IGuiceScanModuleExclusions with BSQuickForms4ExclusionsModule;
	provides IGuiceScanJarExclusions with BSQuickForms4ExclusionsModule;

	opens com.jwebmp.plugins.bs4.quickforms.annotations.actions to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.bs4.quickforms.annotations.implementations to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.bsquickforms4 to com.fasterxml.jackson.databind, com.jwebmp.core;
}
