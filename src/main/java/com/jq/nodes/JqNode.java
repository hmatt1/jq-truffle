package com.jq.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class JqNode extends Node {
    public Object executeObject(VirtualFrame frame) {
        return null;
    }

    public Object executeObject(VirtualFrame frame, Object myObject) {
        return myObject;
    };
}

