package com.jq.nodes.json;

import com.jq.nodes.JqNode;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonNode extends JqNode {
    private List<PairNode> pairs;

    public JsonNode(List<PairNode> pairs) {
        this.pairs = pairs;
    }

    @Override
    public Object executeObject(VirtualFrame frame, Object myObject) {
        Map myMap = new HashMap();
        for (PairNode pair : pairs) {
            Map pairMap = (Map) pair.executeObject(frame, myObject);

            for ( Object key : pairMap.keySet()) {
                myMap.put(key, pairMap.get(key));
            }
        }
        return myMap;
    }
}
