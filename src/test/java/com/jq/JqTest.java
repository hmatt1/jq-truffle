package com.jq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.List;
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

    @Test
    @DisplayName("Test .foo.bar with BigDecimal")
    public void myTest2() {
        Map<String, Object> input = Map.of("foo", Map.of("bar", new BigDecimal(1)));
        BigDecimal result = JQ.jq(input, ".foo.bar", BigDecimal.class);

        assertThat(result.toPlainString()).isEqualTo("1");
    }

    @Test
    @DisplayName("Test .foo.bar with custom Java object")
    public void myTest3() {
        Map<String, Object> input = Map.of("foo", Map.of("bar", new MyCoolObject("biz", 1)));
        MyCoolObject result = JQ.jq(input, ".foo.bar", MyCoolObject.class);

        assertThat(result).isExactlyInstanceOf(MyCoolObject.class);
        assertThat(result.getStatus()).isEqualTo("biz");
        assertThat(result.getCode()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test .foo.bar with custom Java object")
    public void myTest4() {
        Map<String, Object> input = Map.of("foo", Map.of("bar", new MyCoolObject("biz", 1)));
        Object result = JQ.jq(input, ".foo.bar");

        assertThat(result).isExactlyInstanceOf(MyCoolObject.class);
        assertThat(((MyCoolObject) result).getStatus()).isEqualTo("biz");
        assertThat(((MyCoolObject) result).getCode()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test .foo.bar with a list of custom Java object")
    public void myTest5() {
        Map<String, Object> input = Map.of("foo", Map.of("bar", List.of(
                new MyCoolObject("baz", 2),
                new MyCoolObject("biz", 1))));
        Object result = JQ.jq(input, ".foo.bar");

        assertThat(result).isInstanceOf(List.class);
        assertThat(((List<MyCoolObject>) result).get(0).getStatus()).isEqualTo("baz");
        assertThat(((List<MyCoolObject>) result).get(0).getCode()).isEqualTo(2);
        assertThat(((List<MyCoolObject>) result).get(1).getStatus()).isEqualTo("biz");
        assertThat(((List<MyCoolObject>) result).get(1).getCode()).isEqualTo(1);
    }

}

