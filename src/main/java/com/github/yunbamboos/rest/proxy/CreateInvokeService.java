package com.github.yunbamboos.rest.proxy;

import com.github.yunbamboos.dto.in.InDTO;
import com.github.yunbamboos.dto.out.OutDTO;
import com.github.yunbamboos.exception.AppException;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;

/**
 * 创建
 *
 */
public record CreateInvokeService(Class<?> interfaceClass,
                                  Class<?> serviceClass,
                                  String method,
                                  Class<? extends InDTO> in,
                                  Class<? extends OutDTO> out) {

    public InvokeService create() {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        String className = serviceClass.getSimpleName() + "$" + method;
        String classPath = className.replace('.', '/');
        String interfacePath = InvokeService.class.getName().replace('.', '/');
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, classPath, null, "java/lang/Object", new String[]{interfacePath});
        // 生成默认的构造方法
        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();

        String serviceInterfaceClassName = interfaceClass.getName().replace('.', '/');
        String inClassName = in.getName().replace('.', '/');
        String outClassName = out.getName().replace('.', '/');

        String inDTOClassName = InDTO.class.getName().replace('.', '/');
        String outDTOClassName = OutDTO.class.getName().replace('.', '/');

        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC,
                "invoke", "(Ljava/lang/Object;L"+inDTOClassName+";)Ljava/util/Optional;",
                "(Ljava/lang/Object;L"+inDTOClassName+";)Ljava/util/Optional<L"+outDTOClassName+";>;", null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, serviceInterfaceClassName);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 2);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, inClassName);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, serviceInterfaceClassName, method, "(L"+inClassName+";)L"+outClassName+";", true);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/util/Optional", "ofNullable", "(Ljava/lang/Object;)Ljava/util/Optional;", false);
        methodVisitor.visitInsn(Opcodes.ARETURN);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();

        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
        try {
            return (InvokeService) new InvokeServiceClassLoader().getClass(className, code).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new AppException("create invoke service error");
    }

}
