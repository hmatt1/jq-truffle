package com.jq.nodes.json;

import com.jq.nodes.JqNode;
import com.jq.nodes.base.TermListNode;
import com.jq.nodes.value.ValNode;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.Map;

public class PairNode extends JqNode {
    private TermListNode termListNode;
    private ValNode valNode;

    public PairNode(TermListNode termListNode, ValNode valNode) {
        this.termListNode = termListNode;
        this.valNode = valNode;
    }

    @Override
    public Object executeObject(VirtualFrame frame, Object myObject) {
        Object key = termListNode.executeObject(frame, myObject);
        Object value = valNode.executeObject(frame, myObject);
        return Map.of(key, value);
    }
}
