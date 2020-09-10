package com.cwj.ssm.template.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Generator {
    
    private static final Logger log = LoggerFactory.getLogger(Generator.class);

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        
        try (InputStream is = Generator.class.getResourceAsStream("/generator/generatorConfig.xml")) {
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            for (String warning : warnings) log.warn(warning);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
