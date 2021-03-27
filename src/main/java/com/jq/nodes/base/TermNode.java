package com.jq.nodes.base;

import com.oracle.truffle.api.frame.VirtualFrame;

public class TermNode extends BaseNode {
    private final String value;

    public TermNode(String value) {
        this.value = value;
    }

    @Override
    public String executeObject(VirtualFrame frame) {
        return value;
    }
}
