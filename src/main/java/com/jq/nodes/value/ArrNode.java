package com.jq.nodes.value;

import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.List;
import java.util.stream.Collectors;

public class ArrNode extends ValNode {
    private List<ValNode> values;

    public ArrNode(List<ValNode> values) {
        this.values = values;
    }

    @Override
    public Object executeObject(VirtualFrame frame, Object myObject) {
        return values.stream().map(valNode -> valNode.executeObject(frame, myObject)).collect(Collectors.toList());
    }
}
