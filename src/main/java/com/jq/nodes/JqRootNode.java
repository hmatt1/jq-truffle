package com.jq.nodes;

import com.jq.JqContext;
import com.jq.JqLang;
import com.oracle.truffle.api.exception.AbstractTruffleException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


public final class JqRootNode extends RootNode {
    @SuppressWarnings("FieldMayBeFinal")
    @Child
    private JqNode exprNode;

    /**
     * The JqRootNode parses a byte input stream on stdin into an object that is added to the frame as "input"
     *
     * @param exprNode The expression node
     */
    public JqRootNode(JqNode exprNode) {
        super(null);

        this.exprNode = exprNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        try (InputStream inputStream = JqLang.getContext().getEnv().in()) {
            Object myObject = convertFromBytes(inputStream.readAllBytes());
            frame.setObject(getFrameDescriptor().addFrameSlot("input"), myObject);
        } catch (IOException | ClassNotFoundException e) {
            throw new AbstractTruffleException() {
                @Override
                public String getMessage() {
                    return "Could not parse input";
                }
            };
        }

        return JqLang.getContext().getEnv().asGuestValue(this.exprNode.executeObject(frame));
    }

    private static Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }
}