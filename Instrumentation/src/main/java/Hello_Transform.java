import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class Hello_Transform implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {


            ClassPool classPool = ClassPool.getDefault();

            //添加额外的类搜索路径
            if (classBeingRedefined != null) {
                ClassClassPath ccp = new ClassClassPath(classBeingRedefined);
                classPool.insertClassPath(ccp);
            }


            CtClass ctClass = classPool.get("Hello_Sleep");
            System.out.println(ctClass);


            CtMethod ctMethod = ctClass.getDeclaredMethod("hello");


            String body = "{System.out.println(\"Hacker!\");}";
            ctMethod.setBody(body);

            //返回目标类字节码
            byte[] bytes = ctClass.toBytecode();
            return bytes;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}