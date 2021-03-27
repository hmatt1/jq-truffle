package com.jq.nodes.expd;

import com.jq.nodes.value.ValNode;
import com.oracle.truffle.api.frame.VirtualFrame;

public class VValueNode extends ExpdNode {

    private final ValNode valNode;

    public VValueNode(ValNode value) {
        this.valNode = value;
    }

    @Override
    public Object executeObject(VirtualFrame frame, Object myObject) {
        myObject = valNode.executeObject(frame, myObject);

        return myObject;
    }
}
