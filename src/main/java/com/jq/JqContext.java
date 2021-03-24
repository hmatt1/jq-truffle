package com.jq;

import com.oracle.truffle.api.TruffleLanguage.Env;

public class JqContext {
    private Env env;

    public JqContext(Env env) {
        this.env = env;
    }

    public Env getEnv() {
        return env;
    }
}
