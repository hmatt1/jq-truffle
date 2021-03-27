package com.jq;


import com.oracle.truffle.api.exception.AbstractTruffleException;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.*;


public class JQ {

    public static Object jq(Object input, String expression) {
        InputStream inputStream = new ByteArrayInputStream(convertToBytes(input));
        try (Context context = Context.newBuilder("jq").in(inputStream).build()) {
            Value value = context.eval("jq", expression);

            Object result;
            if (value.isHostObject()) {
                result = value.asHostObject();
            } else if (value.fitsInInt()) {
                result = value.asInt();
            } else if (value.fitsInLong()) {
                result = value.asLong();
            } else if (value.fitsInDouble()) {
                result = value.asDouble();
            } else if (value.fitsInFloat()) {
                result = value.asFloat();
            } else {
                throw new AbstractTruffleException() {
                    @Override
                    public String getMessage() {
                        return "Unknown return type for " + value.toString();
                    }
                };
            }
            return result;
        }
    }

    /**
     * Execute a jq expression for the given input
     *
     * @param input      Any Object (probably a map)
     * @param expression A JQ expression such as .foo.bar
     * @param type       The type you want the result cast to
     * @return The filtered result
     */
    public static <T> T jq(Object input, String expression, Class<T> type) {
        InputStream inputStream = new ByteArrayInputStream(convertToBytes(input));
        try (Context context = Context.newBuilder("jq").in(inputStream).build()) {
            return context.eval("jq", expression).as(type);
        }
    }

    private static byte[] convertToBytes(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new AbstractTruffleException() {
                @Override
                public String getMessage() {
                    return "Could not convert input to byte stream";
                }
            };
        }
    }
}
