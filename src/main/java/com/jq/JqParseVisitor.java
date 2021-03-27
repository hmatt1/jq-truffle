package com.jq;

import com.jq.JqParser.*;
import com.jq.nodes.JqNode;
import com.jq.nodes.JqRootNode;
import com.jq.nodes.base.TermListNode;
import com.jq.nodes.base.TermNode;
import com.jq.nodes.expd.ExpdNode;
import com.jq.nodes.expd.VValueNode;
import com.jq.nodes.top.PipeNode;
import com.jq.nodes.value.VTermListNode;
import com.jq.nodes.value.ValNode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class JqParseVisitor extends JqBaseVisitor<JqNode> {
    @Override
    public JqNode visitVPipe(JqParser.VPipeContext ctx) {
        List<ExpdNode> expdNodes = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            expdNodes.add((ExpdNode) visit(ctx.getChild(i)));
        }

        return new PipeNode(expdNodes);
    }

    @Override
    public JqNode visitVArrPipe(JqParser.VArrPipeContext ctx) {
        return super.visitVArrPipe(ctx);
    }

    @Override
    public JqNode visitVValuePipe(JqParser.VValuePipeContext ctx) {
        return super.visitVValuePipe(ctx);
    }

    @Override
    public JqNode visitVValue(JqParser.VValueContext ctx) {
        return new VValueNode((ValNode) visit(ctx.getChild(0)));
    }

    @Override
    public JqNode visitVExp(JqParser.VExpContext ctx) {
        return super.visitVExp(ctx);
    }

    @Override
    public JqNode visitVJson(JqParser.VJsonContext ctx) {
        return super.visitVJson(ctx);
    }

    @Override
    public JqNode visitVArr(JqParser.VArrContext ctx) {
        return super.visitVArr(ctx);
    }

    @Override
    public JqNode visitVTermList(JqParser.VTermListContext ctx) {
        return new VTermListNode((TermListNode) visit(ctx.getChild(0)));
    }

    @Override
    public JqNode visitVKeyword(JqParser.VKeywordContext ctx) {
        return super.visitVKeyword(ctx);
    }

    @Override
    public JqNode visitVFunction(JqParser.VFunctionContext ctx) {
        return super.visitVFunction(ctx);
    }

    @Override
    public JqNode visitVTermListFilter(JqParser.VTermListFilterContext ctx) {
        return super.visitVTermListFilter(ctx);
    }

    @Override
    public JqNode visitJson(JqParser.JsonContext ctx) {
        return super.visitJson(ctx);
    }

    @Override
    public JqNode visitPair(JqParser.PairContext ctx) {
        return super.visitPair(ctx);
    }

    @Override
    public JqNode visitArr(JqParser.ArrContext ctx) {
        return super.visitArr(ctx);
    }

    @Override
    public JqNode visitOperation(JqParser.OperationContext ctx) {
        return super.visitOperation(ctx);
    }

    @Override
    public JqNode visitFunction(JqParser.FunctionContext ctx) {
        return super.visitFunction(ctx);
    }

    @Override
    public JqNode visitArgs(JqParser.ArgsContext ctx) {
        return super.visitArgs(ctx);
    }

    @Override
    public JqNode visitKeyword(JqParser.KeywordContext ctx) {
        return super.visitKeyword(ctx);
    }

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
        return new TermNode(ctx.getChild(0).getText());
    }
}
