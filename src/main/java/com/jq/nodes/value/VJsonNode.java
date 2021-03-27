package com.jq.nodes.value;

import com.jq.nodes.json.JsonNode;
import com.oracle.truffle.api.frame.VirtualFrame;

public class VJsonNode extends ValNode {
    private JsonNode jsonNode;

    public VJsonNode(JsonNode jsonNode) {

        this.jsonNode = jsonNode;
    }

    @Override
    public Object executeObject(VirtualFrame frame, Object myObject) {
        return jsonNode.executeObject(frame, myObject);
    }
}
