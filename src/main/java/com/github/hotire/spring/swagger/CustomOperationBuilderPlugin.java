package com.github.hotire.spring.swagger;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

public class CustomOperationBuilderPlugin implements OperationBuilderPlugin {
    @Override
    public void apply(OperationContext context) {
        context.getParameters();
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return false;
    }
}
