package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.plugins.quickforms.annotations.WebFormEndRow;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperEnd;

public class BuildFormRowEnd implements IFormFieldWrapperEnd {

    @Override
    public IComponentHierarchyBase<?, ?> finalizeDiv(IComponentHierarchyBase<?, ?> finalizeDiv, WebFormEndRow annotation) {
        return finalizeDiv;
    }
}
