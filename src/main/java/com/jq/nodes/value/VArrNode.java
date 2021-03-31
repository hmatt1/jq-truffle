package com.jq.nodes.value;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VArrNode extends ValNode {
    private ArrNode arrNode;

    public VArrNode(ArrNode arrNode) {
        this.arrNode = arrNode;
    }

    @Override
    public Object executeObject(VirtualFrame frame, Object myObject) {
        return arrNode.executeObject(frame, myObject);
    }
}
