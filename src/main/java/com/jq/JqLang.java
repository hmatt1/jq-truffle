package com.jq;

import com.jq.JqParser.JqLexer;
import com.jq.JqParser.JqParser;
import com.jq.nodes.JqNode;
import com.jq.nodes.JqRootNode;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.source.Source;
import org.antlr.v4.runtime.*;

import java.io.IOException;

/**
 * run code with ANTLR and Truffle.
 */
@TruffleLanguage.Registration(
        id = "jqlang",
        name = "jq Language",
        version = "0.0.2")
public class JqLang extends TruffleLanguage<JqContext> {

    @Override
    protected JqContext createContext(Env env) {
        return new JqContext();
    }

    @Override
    protected CallTarget parse(ParsingRequest request) throws Exception {
        JqRootNode main = parseSource(request.getSource());
        return Truffle.getRuntime().createCallTarget(main);
    }

    private JqRootNode parseSource(Source source) throws IOException {
        // get user input
        String input = source.getCharacters().toString();

        System.out.println("inputted String: " + input);

        JqLexer jqLexer = new JqLexer(CharStreams.fromString(
                input));
        JqParser jqParser = new JqParser(new CommonTokenStream(jqLexer));

        JqParser.ExpContext expContext = jqParser.exp();

        // creates Truffle nodes from ANTLR parse result
        JqParseVisitor visitor = new JqParseVisitor();
        JqNode jqNode = visitor.visitExp(expContext);

        return new JqRootNode(jqNode);
    }
}
