package com.jq.nodes;

import com.oracle.truffle.api.TruffleException;
import com.oracle.truffle.api.exception.AbstractTruffleException;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.List;
import java.util.Map;

public final class TermListNode extends JqNode {
    private final List<TermNode> terms;

    public TermListNode(List<TermNode> terms) {
        this.terms = terms;
    }

    @Override
    public Object executeObject(VirtualFrame frame) {
        Object myObject = Map.<String, Object>of("foo", Map.of("bar", 1));
        for (TermNode term : terms) {
            if (myObject instanceof Map) {
                myObject = ((Map) myObject).get(term.executeObject(frame));
            } else {
                throw new AbstractTruffleException() {
                    @Override
                    public String getMessage() {
                        return "Failed to reference object in map using key: " + term.executeObject(frame);
                    }
                };
            }
        }

        return myObject;
    }
}