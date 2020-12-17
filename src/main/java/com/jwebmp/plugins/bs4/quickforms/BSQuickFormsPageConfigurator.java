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

import com.jwebmp.core.Page;
import com.jwebmp.core.base.angular.AngularPageConfigurator;
import com.jwebmp.core.plugins.PluginInformation;
import com.jwebmp.core.plugins.PluginStatus;
import com.jwebmp.core.services.IPageConfigurator;

import jakarta.validation.constraints.NotNull;

/**
 * @author GedMarc
 * @since 25 Mar 2017
 */
@PluginInformation(pluginName = "Bootstrap QuickForms",
		pluginUniqueName = "quickforms-bootstrap",
		pluginDescription = "Create forms out of objects automatically using annotations,reference any plugin and create automated forms with sending",
		pluginVersion = "1.0.0",
		pluginDependancyUniqueIDs = "jquery,google-code-prettify",
		pluginCategories = "forms,frameworks,ui",
		pluginSubtitle = "Create forms out of objects automatically using annotations",
		pluginGitUrl = "https://github.com/GedMarc/JWebMP-BSQuickForms4",
		pluginSourceUrl = "https://github.com/GedMarc/JWebMP-BSQuickForms4",
		pluginWikiUrl = "https://github.com/GedMarc/JWebMP-BSQuickForms4/wiki",
		pluginOriginalHomepage = "http://www.jwebmp.com/",
		pluginIconUrl = "",
		pluginIconImageUrl = "",
		pluginDownloadUrl = "https://mvnrepository.com/artifact/com.jwebmp.plugins.forms/jwebmp-bootstrap-quick-forms",
		pluginGroupId = "com.jwebmp.plugins.forms",
		pluginArtifactId = "jwebmp-bootstrap-quick-forms",
		pluginModuleName = "com.jwebmp.plugins.bs4.quickforms",
		pluginStatus = PluginStatus.Released,
		pluginLastUpdatedDate = "2020/12/16"
)
public class BSQuickFormsPageConfigurator
		implements IPageConfigurator<BSQuickFormsPageConfigurator>
{
	/**
	 * If this configurator is enabled
	 */
	private static boolean enabled = true;

	/*
	 * Constructs a new BSQuickFormsPageConfigurator
	 */
	public BSQuickFormsPageConfigurator()
	{
		//Nothing needed
	}

	/**
	 * Method isEnabled returns the enabled of this AngularAnimatedChangePageConfigurator object.
	 * <p>
	 * If this configurator is enabled
	 *
	 * @return the enabled (type boolean) of this AngularAnimatedChangePageConfigurator object.
	 */
	public static boolean isEnabled()
	{
		return BSQuickFormsPageConfigurator.enabled;
	}

	/**
	 * Method setEnabled sets the enabled of this AngularAnimatedChangePageConfigurator object.
	 * <p>
	 * If this configurator is enabled
	 *
	 * @param mustEnable
	 * 		the enabled of this AngularAnimatedChangePageConfigurator object.
	 */
	public static void setEnabled(boolean mustEnable)
	{
		BSQuickFormsPageConfigurator.enabled = mustEnable;
	}

	@NotNull
	@Override
	 public Page<?> configure(Page<?> page)
	{
		if (!page.isConfigured() && enabled())
		{
			AngularPageConfigurator.setRequired(true);
		}
		return page;
	}

	@Override
	public boolean enabled()
	{
		return BSQuickFormsPageConfigurator.enabled;
	}
}
