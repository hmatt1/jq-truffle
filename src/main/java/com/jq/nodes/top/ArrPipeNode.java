package com.jq.nodes.top;

import com.jq.nodes.NodeHelper;
import com.jq.nodes.expd.ExpdNode;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.List;

public class ArrPipeNode extends TopNode {
    private final List<ExpdNode> expds;

    public ArrPipeNode(List<ExpdNode> expds) {
        this.expds = expds;
    }

    @Override
    public Object executeObject(VirtualFrame frame) {
        Object myObject = NodeHelper.getInput(frame);

//        for (ExpdNode expd : expds) {
//            if (myObject instanceof Map) {
//                myObject = ((Map) myObject).get(expd.executeObject(frame));
//            } else {
//                throw new AbstractTruffleException() {
//                    @Override
//                    public String getMessage() {
//                        return "Failed to reference object in map using key: " + expd.executeObject(frame);
//                    }
//                };
//            }
//        }

        return myObject;
    }
}
