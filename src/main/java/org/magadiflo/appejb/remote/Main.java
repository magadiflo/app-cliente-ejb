package org.magadiflo.appejb.remote;

import org.magadiflo.webapp.ejb.models.Producto;
import org.magadiflo.webapp.ejb.service.ServiceEJBRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        ServiceEJBRemote service = null;
//        final Properties env = new Properties();
//        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//        env.put("jboss.naming.client.ejb.context", true);
        try {
            InitialContext ctx = new InitialContext();//En automático leerá las propiedades del archivo jndi.properties
            service = (ServiceEJBRemote) ctx.lookup("ejb:/61.app-ejb-remote/ServiceEJB!org.magadiflo.webapp.ejb.service.ServiceEJBRemote");
            String saludo = service.saludar("Martín");
            String saludo2 = service.saludar("Gaspar");
            System.out.println(saludo);
            System.out.println(saludo2);

            Producto p = service.crear(new Producto("Sandia"));
            System.out.println("Nuevo producto " + p);

            service.listar().forEach(System.out::println);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
