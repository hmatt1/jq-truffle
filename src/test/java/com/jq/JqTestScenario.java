package com.jq;

public class JqTestScenario {
    private String program;
    private Object input;
    private Object output;
    private Class clazz;

    public JqTestScenario(String program, Object input, Object output, Class clazz) {
        this.program = program;
        this.input = input;
        this.output = output;
        this.clazz = clazz;
    }

    public String getProgram() {
        return program;
    }

    public Object getInput() {
        return input;
    }

    public Object getOutput() {
        return output;
    }

    public Class getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "JqTestScenario: " +
                "program='" + program + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", clazz=" + clazz.getSimpleName();
    }
}
