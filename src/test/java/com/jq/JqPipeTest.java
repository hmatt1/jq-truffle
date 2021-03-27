package com.jq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JqPipeTest {

    @Test
    @DisplayName("Test .foo | .bar")
    public void myTest() {
        Map<String, Object> input = Map.of("foo", Map.of("bar", 1));
        int result = JQ.jq(input, ".foo | .bar", Integer.class);

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Test .foo | .bar returns map")
    public void myTest2() {
        Map<String, Object> input = Map.of("foo", Map.of("bar", Map.of("baz", 1)));
        Map result = JQ.jq(input, ".foo | .bar", Map.class);

        assertThat(result.get("baz")).isEqualTo(1);
    }

}

