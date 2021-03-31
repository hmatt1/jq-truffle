package com.jq;

import com.jq.JqParser.*;
import com.jq.nodes.JqNode;
import com.jq.nodes.JqRootNode;
import com.jq.nodes.base.TermListNode;
import com.jq.nodes.base.TermNode;
import com.jq.nodes.expd.ExpdNode;
import com.jq.nodes.expd.VValueNode;
import com.jq.nodes.json.JsonNode;
import com.jq.nodes.json.PairNode;
import com.jq.nodes.top.PipeNode;
import com.jq.nodes.value.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class JqParseVisitor extends JqBaseVisitor<JqNode> {
    @Override
    public JqNode visitVPipe(JqParser.VPipeContext ctx) {
        List<ExpdNode> expdNodes = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (!ctx.getChild(i).getText().equals("|")) {
                expdNodes.add((ExpdNode) visit(ctx.getChild(i)));
            }
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
        JqNode child = visit(ctx.getChild(0));

        return new VValueNode((ValNode) child);
    }

    @Override
    public JqNode visitVExp(JqParser.VExpContext ctx) {
        return super.visitVExp(ctx);
    }

    @Override
    public JqNode visitVJson(JqParser.VJsonContext ctx) {
        return new VJsonNode((JsonNode) visit(ctx.getChild(0)));
    }

    @Override
    public JqNode visitVArr(JqParser.VArrContext ctx) {
        return new VArrNode((ArrNode) visit(ctx.getChild(0)));
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
        List<PairNode> pairNodes = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            JqNode jqNode = visit(ctx.getChild(i));
            if (jqNode instanceof PairNode) {
                pairNodes.add((PairNode) jqNode);
            }
        }

        return new JsonNode(pairNodes);
    }

    @Override
    public JqNode visitPair(JqParser.PairContext ctx) {

        TermListNode termListNode = null;
        ValNode valNode = null;

        for (int i = 0; i < ctx.getChildCount(); i++) {
            JqNode jqNode = visit(ctx.getChild(i));
            if (jqNode instanceof TermListNode) {
                termListNode = (TermListNode) jqNode;
            } else if (jqNode instanceof ValNode) {
                valNode = (ValNode) jqNode;
            }
        }

        return new PairNode(termListNode, valNode);
    }

    @Override
    public JqNode visitArr(JqParser.ArrContext ctx) {
        List<ValNode> valNodes = new ArrayList<>();

        for (int i = 0; i < ctx.getChildCount(); i++) {
            JqNode jqNode = visit(ctx.getChild(i));
            if (jqNode instanceof ValNode) {
                valNodes.add((ValNode) jqNode);
            }
        }

        return new ArrNode(valNodes);
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
