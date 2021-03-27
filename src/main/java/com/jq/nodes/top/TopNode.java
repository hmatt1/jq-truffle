package com.jq.nodes.top;

import com.jq.nodes.JqNode;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class TopNode extends JqNode {
    @Override
    public Object executeObject(VirtualFrame frame) {
        return null;
    }
}
