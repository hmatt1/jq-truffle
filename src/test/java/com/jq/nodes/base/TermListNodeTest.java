package com.jq.nodes.base;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TermListNodeTest {

    @ParameterizedTest
    @CsvSource({
            ".,         DOT",
            "?,         QUESTION",
            "foo,       FIELD",
            "[],        SQUARE",
            "$,         DOLLAR",
            "..,        DOUBLE_DOT",
            "9,         NUMBER",
            "9.9,       NUMBER",
            "0.123,     NUMBER",
            "-0.13,     NUMBER",
            "-10,       NUMBER",
            "blah blah, STRING"
    })
    void myTest(String text, TermListNode.TYPE type) {

        assertThat(TermListNode.getType(text)).isEqualTo(type);

    }
}