package com.jwebmp.plugins.bs4.quickforms.implementations;

import com.guicedee.guicedinjection.interfaces.IGuiceScanModuleExclusions;

import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class BSQuickForms4ExclusionsModule
		implements IGuiceScanModuleExclusions<BSQuickForms4ExclusionsModule>
{
	@Override
	public @NotNull Set<String> excludeModules()
	{
		Set<String> strings = new HashSet<>();
		strings.add("com.jwebmp.plugins.bsquickforms4");
		return strings;
	}
}
