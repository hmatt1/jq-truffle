package com.jq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.management.ManagementFactory;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JqTest {

    @Test
    @DisplayName("Test .foo.bar")
    public void myTest() {
        System.out.println(ManagementFactory.getRuntimeMXBean().getVmVersion());

        Map<String, Object> input = Map.of("foo", Map.of("bar", 1));
        int result = JQ.jq(input, ".foo.bar", Integer.class);

        assertThat(result).isEqualTo(1);
    }
}

