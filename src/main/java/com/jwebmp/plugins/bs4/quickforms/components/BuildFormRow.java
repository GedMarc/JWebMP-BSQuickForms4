package com.jwebmp.plugins.bs4.quickforms.components;

import com.google.common.base.Strings;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.quickforms.annotations.WebFormStartRow;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperStart;

public class BuildFormRow implements IFormFieldWrapperStart {

    @Override
    public IComponentHierarchyBase<?, ?> createWrapper(WebFormStartRow annotation) {
        BSRow<?> row = new BSRow<>();
        if (!Strings.isNullOrEmpty(annotation.classes())) {
            row.addClass(annotation.classes());
        }
        return row;
    }
}
