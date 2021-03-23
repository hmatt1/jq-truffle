package com.jq.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

import java.util.Map;

public abstract class JqNode extends Node {
    public abstract Object executeObject(VirtualFrame frame);
}

