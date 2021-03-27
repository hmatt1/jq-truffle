package com.jq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

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

    public static class MyCoolObject implements Serializable {
        String status;
        int code;

        public MyCoolObject(String status, int code) {
            this.status = status;
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "MyCoolObject{" +
                    "status='" + status + '\'' +
                    ", code=" + code +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyCoolObject that = (MyCoolObject) o;
            return code == that.code && Objects.equals(status, that.status);
        }

        @Override
        public int hashCode() {
            return Objects.hash(status, code);
        }
    }
}

