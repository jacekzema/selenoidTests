package org.tmobile.runner;


import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.tmobile.steps.api")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@api")
@ExtendWith({TextReportExtension.class})
public class ApiTestRunner {
}
