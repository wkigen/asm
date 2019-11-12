package com.github.wkigen.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ASMClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;

    public ASMClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (!mClassName.startsWith("android") && !mClassName.startsWith("com/github/wkigen/trace") ){
            System.out.println("ASMPlugin : class:" + mClassName+"-->visitMethod :"+name);
            return new ASMMethodVisitor(mv);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

}
