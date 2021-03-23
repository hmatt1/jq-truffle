package com.jq;

import com.jq.nodes.JqNode;
import com.jq.nodes.JqRootNode;
import com.jq.nodes.TermListNode;
import com.jq.nodes.TermNode;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JqTest {

    @Test
    @DisplayName("Test .foo.bar")
    public void myTest() {
        JqNode exprNode = new TermListNode(List.of(new TermNode("foo"), new TermNode("bar")));
        var rootNode = new JqRootNode(exprNode);

        CallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);

        var result = callTarget.call();

        assertEquals(1, result);
    }
}

