package com.jq.nodes;

import com.jq.JqContext;
import com.jq.JqLang;
import com.oracle.truffle.api.TruffleContext;
import com.oracle.truffle.api.TruffleException;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.exception.AbstractTruffleException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalvm.polyglot.Value;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class TermListNode extends JqNode {
    private final List<TermNode> terms;

    public TermListNode(List<TermNode> terms) {
        this.terms = terms;
    }

    @Override
    public Object executeObject(VirtualFrame frame) {
        Object myObject = NodeHelper.getInput(frame);

        for (TermNode term : terms) {
            if (myObject instanceof Map) {
                myObject = ((Map) myObject).get(term.executeObject(frame));
            } else {
                throw new AbstractTruffleException() {
                    @Override
                    public String getMessage() {
                        return "Failed to reference object in map using key: " + term.executeObject(frame);
                    }
                };
            }
        }

        return myObject;
    }
}