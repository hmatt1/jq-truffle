package com.jq.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public final class JqRootNode extends RootNode {
    @SuppressWarnings("FieldMayBeFinal")
    @Child
    private JqNode exprNode;

    public JqRootNode(JqNode exprNode) {
        super(null);

        this.exprNode = exprNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return this.exprNode.executeObject(frame);
    }
}