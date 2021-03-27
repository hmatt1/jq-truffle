package com.jq;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JqScenariosTest {

    static Stream<JqTestScenario> jqTestScenarioProvider() {
        Map<String, Object> fooBar = Map.of("foo", Map.of("bar", 1));
        Map<String, Object> fooBarBaz = Map.of("foo", Map.of("bar", Map.of("baz", 1)));

        return Stream.of(
                new JqTestScenario(".foo | .bar", fooBar, 1, Integer.class),
                new JqTestScenario(".foo | .bar", fooBarBaz, Map.of("baz", 1), Map.class),
                new JqTestScenario(".foo | .bar | .baz", fooBarBaz, 1, Integer.class)
        );
    }

    @ParameterizedTest
    @MethodSource("jqTestScenarioProvider")
    void scenarioTest(JqTestScenario scenario) {
        Object result = JQ.jq(scenario.getInput(), scenario.getProgram(), scenario.getClazz());

        assertThat(result).isInstanceOf(scenario.getClazz());
        assertThat(scenario.getClazz().cast(result)).isEqualTo(scenario.getOutput());
    }
}

