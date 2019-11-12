package com.github.wkigen.trace;

public class ASMCode {


    public void test(){
        TraceManager.getInstance().traceStartMethod();
        TraceManager.getInstance().traceEndMethod();
    }
}
