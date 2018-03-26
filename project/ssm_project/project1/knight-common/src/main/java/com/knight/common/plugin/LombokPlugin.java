package com.knight.common.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * mybatis generator lombok plugin
 *
 * @author dongfg
 * @date 2017/7/26
 */
public class LombokPlugin extends PluginAdapter {

    private static final Set<LombokEnum> LOMBOK_ENUMS = new LinkedHashSet<LombokEnum>();

    /**
     * plugin must have a public constructor
     */
    public LombokPlugin() {
        // default active annotation
        LOMBOK_ENUMS.add(LombokEnum.DATA);
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        for (LombokEnum lombok : LOMBOK_ENUMS) {
            topLevelClass.addImportedType(lombok.importedType);
            topLevelClass.addAnnotation(lombok.annotation);
        }

        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    enum LombokEnum {
        /**
         * https://projectlombok.org/features/Data
         */
        DATA("@Data", "lombok.Data"),;
        private final String annotation;
        private final String importedType;

        LombokEnum(String annotation, String importedType) {
            this.annotation = annotation;
            this.importedType = importedType;
        }
    }
}

