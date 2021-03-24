package com.jq;


import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;


public class Application {

    public static void main(String[] args) throws Exception {
        System.out.println("Truffle runtime: " + Truffle.getRuntime().getName());

        Map<String, Object> input = Map.of("foo", Map.of("bar", 5));

        InputStream inputStream = new ByteArrayInputStream(convertToBytes(input));

        Context context = Context.newBuilder("jq").in(inputStream).build();
        //context.getBindings("jq").putMember("input", 42);

        //context.getPolyglotBindings().putMember("input", 42);

        Object answer = context.eval("jq", ".foo.bar");
        System.out.println("answer: " + answer);
        System.out.println();
    }

    private static byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }



//        boolean blah =  context.getBindings("jq").hasMembers();
//        context.getBindings("jq")
//                .putMember("javaObj",
//                Map.of("foo",
//                        Map.of("bar",
//                                42)));
}
