package com.jq;

import com.jq.JqParser.*;
import com.jq.nodes.JqNode;
import com.jq.nodes.JqRootNode;
import com.jq.nodes.TermListNode;
import com.jq.nodes.TermNode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class JqParseVisitor extends JqBaseVisitor<JqNode> {

    private JqRootNode rootNode;

    public JqParseVisitor() {
        super();
    }

    @Override
    public JqNode visitExp(JqParser.ExpContext ctx) {
        return this.visit(ctx.getChild(0));
    }

    @Override
    public JqNode visitTerm_list(JqParser.Term_listContext ctx) {
        List<TermNode> terms = new ArrayList<>();
        for (ParseTree child :
             ctx.children) {
            JqNode termNode = this.visit(child);
            if (termNode instanceof TermNode) {
                terms.add((TermNode) termNode);
            }
        }

        return new TermListNode(terms);
    }

    @Override
    public JqNode visitTerm(JqParser.TermContext ctx) {
        return new TermNode(ctx.getChild(1).getText());
    }
}
