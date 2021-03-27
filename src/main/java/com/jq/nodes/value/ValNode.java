package com.jq.nodes.value;

import com.jq.nodes.JqNode;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class ValNode extends JqNode {
    @Override
    public Object executeObject(VirtualFrame frame) {
        return null;
    }
}
