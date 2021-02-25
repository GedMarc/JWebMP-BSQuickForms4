package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.html.interfaces.GlobalChildren;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperEnd;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperStart;

public class BuildFormRowEnd implements IFormFieldWrapperEnd {

    @Override
    public IComponentHierarchyBase<?, ?> finalizeDiv(IComponentHierarchyBase<?, ?> finalizeDiv) {
        for (GlobalChildren child : finalizeDiv.getChildren()) {
            child.asAttributeBase().addAttribute("class", "col");
        }
        return finalizeDiv;
    }
}
