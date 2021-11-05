package pl.bugdemons.utils.junit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

@Slf4j
public class TestListenerExtension implements TestWatcher, AfterAllCallback, BeforeTestExecutionCallback {

    private static final String FINISH_MSG = " * # * # * # * # * # * # * # * FINISH * # * # * # * # * # * # * # * ";
    private final List<TestResultStatus> testResultStatuses = new ArrayList<>();

    @Override
    public void afterAll(ExtensionContext context) {
        Map<TestResultStatus, Long> summary = testResultStatuses.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        log.info("Test result summary for {} {}", context.getDisplayName(), summary.toString());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info("Test {} Disabled due to {}", context.getParent().get().getDisplayName(), reason.orElse("Reason unknown"));
        log.info(FINISH_MSG);

        testResultStatuses.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("Test {} Successful", context.getParent().get().getDisplayName());
        log.info(FINISH_MSG);

        testResultStatuses.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info("Test {} Aborted due to {}", context.getParent().get().getDisplayName(), cause.getMessage());
        log.info(FINISH_MSG);

        testResultStatuses.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("Test {} Failed due to {}", context.getParent().get().getDisplayName(), cause.getMessage());
        log.info(FINISH_MSG);

        testResultStatuses.add(TestResultStatus.FAILED);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        log.info(" * # * # * # * # * # * # * # * START * # * # * # * # * # * # * # * ");
        log.info("Running test: {}", context.getParent().get().getDisplayName());
    }

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    }
}
