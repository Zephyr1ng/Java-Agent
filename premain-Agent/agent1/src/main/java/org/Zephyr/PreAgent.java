package org.Zephyr;

import java.lang.instrument.Instrumentation;

/*
Instrumentation类可以与JVMI通讯，实现类加载时获取类信息，当然也可以改变类信息。
改变类信息就涉及到修改加载到内存中的java的字节码（class文件）
修改字节码工具有两个，一个是javassist，较为人性化，效率稍低。
另一个是asm，偏底层，复杂但效率高。

修改class字节码是通过实现ClassFileTransformer接口，并将实现类注册到Instrumentation中。
这样一旦有class的加载或者重载，这个修改就会生效。
*/
public class PreAgent {
    // premain方法
    public static void premain(String args, Instrumentation inst) throws Exception {
        inst.addTransformer(new ClassHacker(), true);
    }
}
