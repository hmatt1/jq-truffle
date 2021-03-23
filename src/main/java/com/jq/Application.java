package com.jq;


import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;


public class Application {

    public static void main(String[] args) throws Exception {
        System.out.println("Truffle runtime: " + Truffle.getRuntime().getName());

        Context context = Context.create("jqlang");
        context.getEngine().getLanguages().forEach((s, l) -> System.out.println("Language: " + l.getName()));

        Object answer = runCode(context, ".foo.bar");
        System.out.println("answer: " + answer);
        System.out.println();
    }

    private static Object runCode(Context context, String program) throws IOException {
        Source source = Source.newBuilder("jqlang", program, "NAME").build();
        return context.eval(source);
    }
}
