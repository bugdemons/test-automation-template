package pl.bugdemons.utils;

import java.nio.file.FileSystems;

import com.idera.xray.junit.customjunitxml.XrayTestReporter;
import com.idera.xray.junit.customjunitxml.XrayTestReporterParameterResolver;
import com.idera.xray.junit.customjunitxml.annotations.XrayTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(XrayTestReporterParameterResolver.class)

//extended report will be generated as ./reports/TEST-junit-jupiter.xml
public class XrayIntegrationExampleTest {

    @XrayTest(key = "jiraKey", id = "jiraId")
    @Test
    void extendedXmlWithJiraKeyAndIdTest() {
        //this will produce test case entry with additional properties test_key and test_id
    }

    @Test
    void extendedXmlWithXrayParamsTest(XrayTestReporter xrayTestReporter) {
        //this will add comment to test case
        xrayTestReporter.addComment("hello");

        //this will add file to report
        xrayTestReporter.addTestRunEvidence(
                FileSystems.getDefault()
                        .getPath("src/test/resources/log4j2.yml")
                        .toAbsolutePath()
                        .toString()
        );
    }
}
