package com.jq.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public final class TermNode extends JqNode {
    private final String value;

    public TermNode(String value) {
        this.value = value;
    }

    @Override
    public Object executeObject(VirtualFrame frame) {
        return value;
    }
}