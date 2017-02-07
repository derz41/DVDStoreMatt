
package dvdrental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Matt
 */
public class FilmActorHelper {
    
    Session session = null;
    
    public FilmActorHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public List getActors(){
        List<Actor> actorList = null;
        
        String sql = "select * from actor";
        
    try{ 
        if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        
        SQLQuery q = session.createSQLQuery(sql);
        
        q.addEntity(Actor.class);
        
        actorList = (List<Actor>) q.list();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
        
        return actorList;
    }
    
    public List getCategories(){
        List<Category> categoryList = null;
        
        String sql = "select * from category";
        
    try{ 
        if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        
        SQLQuery q = session.createSQLQuery(sql);
        
        q.addEntity(Category.class);
        
        categoryList = (List<Category>) q.list();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
        
        return categoryList;
    }
    
    public List getLanguages(){
        //declaring local variable that will be returned as a list of languages
        List<Language> languageList = null;
        
        //creating query as a string
        String sql = "select * from language";
        
    try{ 
        //beggining transaction of the current one is not active
        if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        //creating query that will be executed against the database
        SQLQuery q = session.createSQLQuery(sql);
        //associating the language POJO and table with the query
        q.addEntity(Language.class);
        //executing the query
        languageList = (List<Language>) q.list();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
        
        return languageList;
    }

    
}
