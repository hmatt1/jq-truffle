package com.jq.nodes.base;

import com.oracle.truffle.api.exception.AbstractTruffleException;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.List;
import java.util.Map;

import static com.jq.nodes.base.TermListNode.TYPE.*;

public class TermListNode extends BaseNode {

    private List<TermNode> terms;

    public TermListNode(List<TermNode> terms) {
        this.terms = terms;
    }

    @Override
    public Object executeObject(VirtualFrame frame, Object myObject) {
        TYPE previousType = FIRST;

        for (TermNode term : terms) {
            String text = term.executeObject(frame);

            TYPE currentType = getType(text);

            if (myObject instanceof Map) {
                if (previousType == DOT && currentType == FIELD) {
                    myObject = ((Map) myObject).get(term.executeObject(frame));
                }

            } else {
                throw new AbstractTruffleException() {
                    @Override
                    public String getMessage() {
                        return "Failed to reference object in map using key: " + term.executeObject(frame);
                    }
                };
            }

            previousType = currentType;
        }

        return myObject;
    }

    protected static TYPE getType(String text) {
        if (text.equals(".")) {
            return DOT;
        } else if (text.equals("$")) {
            return DOLLAR;
        } else if (text.equals("?")) {
            return QUESTION;
        } else if (text.equals("[]")) {
            return SQUARE;
        } else if (text.equals("..")) {
            return DOUBLE_DOT;
        } else if (text.matches("-?\\d+(.[0-9]+)?")) {
            return NUMBER;
        } else if (text.matches("[a-zA-Z0-9_]+")) {
            return FIELD;
        } else {
            return STRING;
        }
    }

    public enum TYPE {
        FIRST,
        DOT,
        DOLLAR,
        FIELD,
        STRING,
        QUESTION,
        SQUARE,
        IDENT,
        NUMBER,
        DOUBLE_DOT
    }


}