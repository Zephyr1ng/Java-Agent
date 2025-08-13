import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class Inject_Agent {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, AgentLoadException, AgentInitializationException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, AgentLoadException, AgentInitializationException, AgentLoadException, AgentInitializationException {
        //调用VirtualMachine.list()获取正在运行的JVM列表
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for(VirtualMachineDescriptor vmd : list){
            System.out.println(vmd.displayName());
            //遍历每一个正在运行的JVM，如果JVM名称为Sleep_Hello则连接该JVM并加载特定Agent
            if(vmd.displayName().equals("Hello_Sleep")){

                //连接指定JVM
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                //加载Agent
                virtualMachine.loadAgent("D:\\Security\\JavaStudy\\java_src\\serialize\\Agent\\Instrumentation\\src\\main\\java\\agentmain_transform.jar");
                //断开JVM连接
                virtualMachine.detach();
            }

        }
    }
}