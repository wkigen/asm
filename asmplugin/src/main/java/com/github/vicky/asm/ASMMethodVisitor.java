package com.github.wkigen.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class ASMMethodVisitor extends MethodVisitor {

    public ASMMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitCode() {
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/github/wkigen/trace/TraceManager", "getInstance", "()Lcom/github/wkigen/trace/TraceManager;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/github/wkigen/trace/TraceManager", "traceStartMethod", "()V", false);
        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/github/wkigen/trace/TraceManager", "getInstance", "()Lcom/github/wkigen/trace/TraceManager;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/github/wkigen/trace/TraceManager", "traceEndMethod", "()V", false);
        super.visitInsn(opcode);
    }

}
