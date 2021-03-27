package com.jq.nodes.value;

import com.jq.nodes.base.TermListNode;
import com.oracle.truffle.api.frame.VirtualFrame;

public class VTermListNode extends ValNode {

    private TermListNode termListNode;

    public VTermListNode(TermListNode termListNode) {
        this.termListNode = termListNode;
    }

    @Override
    public Object executeObject(VirtualFrame frame, Object myObject) {
        return termListNode.executeObject(frame, myObject);
    }
}
