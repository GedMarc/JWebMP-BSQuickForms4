package com.jwebmp.plugins.bs4.quickforms.components;

import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.quickforms.services.IFormFieldWrapperStart;

public class BuildFormRow implements IFormFieldWrapperStart {

    @Override
    public IComponentHierarchyBase<?, ?> createWrapper() {
        BSRow<?> row = new BSRow<>();
        return row;
    }
}
