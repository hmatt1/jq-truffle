package com.jq.nodes.base;

import com.jq.nodes.JqNode;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class BaseNode extends JqNode {

    @Override
    public Object executeObject(VirtualFrame frame) {
        return null;
    }
}
