package com.jq.nodes.top;

import com.jq.nodes.NodeHelper;
import com.jq.nodes.expd.ExpdNode;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.List;

public class PipeNode extends TopNode {
    private final List<ExpdNode> expds;

    public PipeNode(List<ExpdNode> expds) {
        this.expds = expds;
    }

    @Override
    public Object executeObject(VirtualFrame frame) {
        Object myObject = NodeHelper.getInput(frame);

        for (ExpdNode expd : expds) {
                myObject = expd.executeObject(frame, myObject);
        }

        return myObject;
    }
}
