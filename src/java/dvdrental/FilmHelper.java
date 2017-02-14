
package dvdrental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

/**
 *
 * @author Matt
 */
public class FilmHelper {
    
    Session session = null;
    
    public FilmHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public List getFilmTitles (int startId) {
        List<Film> filmList = null;
        
        String sql = "select * from film order by title limit :start, :end";
        
        try {
            
            if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        //creating query that will be executed against the database
        SQLQuery q = session.createSQLQuery(sql);
        
        q.addEntity(Film.class);
        
        q.setParameter("start", startId);
        q.setParameter("end", 10);
        
        filmList = (List<Film>) q.list();
        
        }  catch (Exception e) {
            e.printStackTrace();
        }
        
        return filmList;
    }
    
    public int getNumberFilms(){
         List<Film> filmList = null;
        
        String sql = "select * from film";
        
        try {
            
            if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        //creating query that will be executed against the database
        SQLQuery q = session.createSQLQuery(sql);
        
        q.addEntity(Film.class);
        
        filmList = (List<Film>) q.list();
        
        }  catch (Exception e) {
            e.printStackTrace();
        }
        
        return filmList.size();
    }
    
}
