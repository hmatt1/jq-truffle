package com.jq;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JqScenariosTest {

    static Stream<JqTestScenario> jqTestScenarioProvider() {
        MyCoolObject myCoolObject = new MyCoolObject("biz", 1);
        MyCoolObject myCoolObject1 = new MyCoolObject("biz", 1);

        List<MyCoolObject> myCoolObjectList = List.of(myCoolObject, myCoolObject1);

        BigDecimal bigDecimal = new BigDecimal("11289349081723498072314987");
        Map<String, Object> fooBar = Map.of("foo", Map.of("bar", 1));
        Map<String, Object> fooBarDouble = Map.of("foo", Map.of("bar", 2.5));
        Map<String, Object> fooBarBigDecimal = Map.of("foo", Map.of("bar", bigDecimal));
        Map<String, Object> fooBarCoolObject = Map.of("foo", Map.of("bar", myCoolObject));
        Map<String, Object> fooBarBaz = Map.of("foo", Map.of("bar", Map.of("baz", 1)));
        Map<String, Object> fooBarCoolObjectList = Map.of("foo", Map.of("bar", myCoolObjectList));


        return Stream.of(
                new JqTestScenario(".", fooBar, fooBar, Map.class),
                new JqTestScenario(".foo.bar", fooBar, 1, Integer.class),
                new JqTestScenario(".foo.bar", fooBarDouble, 2.5, Double.class),
                new JqTestScenario(".foo.bar", fooBarBigDecimal, bigDecimal, BigDecimal.class),
                new JqTestScenario(".foo.bar", fooBarCoolObject, myCoolObject, MyCoolObject.class),
                new JqTestScenario(".foo.bar", fooBarCoolObjectList, myCoolObjectList, List.class),
                new JqTestScenario(".foo | .bar", fooBar, 1, Integer.class),
                new JqTestScenario(".foo | .bar", fooBarBaz, Map.of("baz", 1), Map.class),
                new JqTestScenario(".foo | .bar | .baz", fooBarBaz, 1, Integer.class),
                new JqTestScenario("{\"foo\": 1}", null, Map.of("foo", new BigDecimal(1)), Map.class)
        );
    }

    @ParameterizedTest
    @MethodSource("jqTestScenarioProvider")
    void scenarioTest(JqTestScenario scenario) {
        try {
            Object result = JQ.jq(scenario.getInput(), scenario.getProgram(), scenario.getClazz());

            assertThat(result).isNotNull();
            assertThat(result).isInstanceOf(scenario.getClazz());
            assertThat(result).usingRecursiveComparison().isEqualTo(scenario.getOutput());

            Object result2 = JQ.jq(scenario.getInput(), scenario.getProgram());
            assertThat(result2).isNotNull();
            assertThat(result2).isInstanceOf(scenario.getClazz());
            assertThat(result2).usingRecursiveComparison().isEqualTo(scenario.getOutput());
        } catch (Exception e) {
            // set breakpoint here to debug exceptions
            assertThat(e).isNull();
        }
    }
}

