package com.jq;

import com.jq.JqParser.*;
import com.jq.nodes.JqNode;
import com.jq.nodes.JqRootNode;

public class JqParseVisitor extends JqBaseVisitor<JqNode> {

    private JqRootNode rootNode;

    public JqParseVisitor() {
        super();
    }

    @Override
    public JqNode visitExp(JqParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public JqNode visitTerm_list(JqParser.Term_listContext ctx) {
        return super.visitTerm_list(ctx);
    }

    @Override
    public JqNode visitTerm(JqParser.TermContext ctx) {
        return super.visitTerm(ctx);
    }
}
