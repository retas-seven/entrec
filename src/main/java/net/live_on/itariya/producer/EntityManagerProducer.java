package net.live_on.itariya.producer;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Dependent
public class EntityManagerProducer {
    @PersistenceContext(unitName = "entrecUnit")
    @Produces
    private EntityManager em;

//    @PostConstruct
//    public void initialize(){
//        Log.out.debug("::: ENTITYMANAGER_PRODUCER IS INITIALIZED! ::: " + System.identityHashCode(this));
//    }
//
//    @RequestScoped
//    @Produces
//    public EntityManager getEntityManager() {
//    	String unitName = PropertiesUtil.getString(PropertiesUtil.UNIT_NAME);
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory(unitName);
//        EntityManager em = factory.createEntityManager();
//        Log.out.debug("::: ENTITYMANAGER IS CREATED! ::: " + System.identityHashCode(em) + "on " + System.identityHashCode(this));
//        return em;
//    }
//
//    public void closeEntityManager(@Disposes EntityManager em) {
//    	Log.out.debug("::: ENTITYMANAGER IS CLOSED! ::: " + System.identityHashCode(em) + "on " + System.identityHashCode(this));
//        em.close();
//    }
}
