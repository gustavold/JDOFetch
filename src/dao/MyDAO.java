/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.jdo.FetchPlan;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

/**
 *
 * @author gustavold
 */
public class MyDAO implements ClinicDAO {

    PersistenceManagerFactory pmf;

    /** Constructor, defining the PersistenceManagerFactory to use. */
    public MyDAO(PersistenceManagerFactory pmf) {
        this.pmf = pmf;
    }

    public MyDAO() {
        this.pmf = JDOHelper.getPersistenceManagerFactory("dao/datanucleus.properties");
    }

    /** Accessor for a PersistenceManager */
    protected PersistenceManager getPersistenceManager() {
        return pmf.getPersistenceManager();
    }

    public Collection getOwners() {
        Collection owners;
        PersistenceManager pm = getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();

            // Define the objects to be fetched/detached with our owner objects.
            pm.getFetchPlan().addGroup("detach_owner_pets");
            pm.getFetchPlan().addGroup("detach_pet_type");
            pm.getFetchPlan().setMaxFetchDepth(-1);


            //Query q = pm.newQuery(entities.Owner.class);
            Query q = pm.newQuery("SELECT FROM entities.Owner");
            q.getFetchPlan().setFetchSize(FetchPlan.FETCH_SIZE_GREEDY);
            //q.addExtension("datanucleus.query.jdoql.{varName}.join=LEFTOUTERJOIN");

            Collection query_owners = (Collection) q.execute();

            // Detach our owner objects for use elsewhere
            owners = pm.detachCopyAll(query_owners);

            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return owners;
    }
}
