package org.Zephyr;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassHacker implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(className.equals("org/Zephyr/People")){
            System.out.println("loader:"+loader);
            System.out.println("className:"+className);
            String loadName = className.replaceAll("/", ".");
            try {
                ClassPool classPool = new ClassPool();
                classPool.appendClassPath(new LoaderClassPath(loader));
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

                // 获取类中的方法
                CtMethod ctMethod = ctClass.getDeclaredMethod("setName");
                ctMethod.insertAfter("this.name=\"WX\";");
                byte[] newClassBytes = ctClass.toBytecode();
                ctClass.detach();
                return newClassBytes;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }
}

