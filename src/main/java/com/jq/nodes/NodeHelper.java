package com.jq.nodes;

import com.oracle.truffle.api.exception.AbstractTruffleException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.Optional;

public class NodeHelper {
    public static Object getInput(VirtualFrame frame) {
        Optional<? extends FrameSlot> input = frame.getFrameDescriptor().getSlots().stream().filter(slot -> slot.getIdentifier().equals("input")).findFirst();
        FrameSlot slot = input.orElseThrow();
        Object myObject = null;
        try {
            myObject = frame.getObject(slot);
        } catch (FrameSlotTypeException e) {
            throw new AbstractTruffleException() {
                @Override
                public String getMessage() {
                    return "Could not get input";
                }
            };
        }

        return myObject;
    }
}
