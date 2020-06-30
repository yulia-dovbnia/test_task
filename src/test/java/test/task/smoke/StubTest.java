package test.task.smoke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.task.AbstractTest;

public class StubTest extends AbstractTest {

    @Test
    @DisplayName("This test should be failed")
    public void test1() {
        softAssertions.assertThat(true).isFalse();
    }

    @Test
    @DisplayName("This test should be passed")
    public void test2() {
        softAssertions.assertThat(true).isTrue();
    }
}
