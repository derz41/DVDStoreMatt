
package dvdrental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Matt
 */
public class ActorHelper {
    
    Session session = null;
    
    public ActorHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public int insertActor(Actor a) {
        int result = 0;
        
        String sql = "insert into actor(first_name, last_name, last_update) "
                + "Values (:fName, :lName, :update)";
        
        try {
            //starting the transaction if one is not active
            if (!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            //creaing query that can be executed
            SQLQuery q = session.createSQLQuery(sql);
            // associating actor POJO and table with the query
            q.addEntity(Actor.class);
            
            //binding the values to the placeholders in the query
            q.setParameter("fName", a.getFirstName());
            q.setParameter("lName", a.getLastName());
            q.setParameter("update", a.getLastUpdate());
            
            //executing the query
            result = q.executeUpdate();
            
            //commiting the query to the database
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}
