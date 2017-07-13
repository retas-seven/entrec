package net.live_on.itariya.producer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.live_on.itariya.util.Log;
import net.live_on.itariya.util.PropertiesUtil;

@Dependent
public class EntityManagerProducer {
    //private EntityManager em;

    @PostConstruct
    public void initialize(){
        Log.out.debug("::: ENTITYMANAGER_PRODUCER IS INITIALIZED! ::: " + System.identityHashCode(this));
    }

    @RequestScoped
    @Produces
    public EntityManager getEntityManager() {
    	String unitName = PropertiesUtil.getString(PropertiesUtil.UNIT_NAME);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(unitName);
        EntityManager em = factory.createEntityManager();
        Log.out.debug("::: ENTITYMANAGER IS CREATED! ::: " + System.identityHashCode(em) + "on " + System.identityHashCode(this));
        return em;
    }

    public void closeEntityManager(@Disposes EntityManager em) {
    	Log.out.debug("::: ENTITYMANAGER IS CLOSED! ::: " + System.identityHashCode(em) + "on " + System.identityHashCode(this));
        em.close();
    }
}
