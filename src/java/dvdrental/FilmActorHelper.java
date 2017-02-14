
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
    
    // this method is going to insert into the fil tabble
    private int insertFilm(String title, String description, int language,
            String rating, Timestamp timeStamp){
        int result = 0;
        
        String sql = "insert into film "
                + "(title, description, language_id, rental_duration, rental_rate, "
                + "replacement_cost, rating, last_update) "
                + "values (:title, :description, :language_Id, :rentalDuration, "
                + ":rentalRate, :replacementCost, :rating, :update)";
        
        try {
            
            //beggining transaction of the current one is not active
        if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        //creating query that will be executed against the database
        SQLQuery q = session.createSQLQuery(sql);
        
        q.addEntity(Film.class);
        
        q.setParameter("title", title);
        q.setParameter("description", description);
        q.setParameter("languageId", language);
        q.setParameter("rentalDuration", 3);
        q.setParameter("rantalRate", 4.99);
        q.setParameter("replacementCost", 19.99);
        q.setParameter("rating", rating);
        q.setParameter("update", timeStamp);
        
        result = q.executeUpdate();
        
        session.getTransaction().commit();
        
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    // return the film id of the last film inserted into the table
    private int getFilmId() {
        
        List<Film> filmList = null;
        
        String sql = "select * from film order by last_update desc limit 1";
        
        try {
            
            //beggining transaction of the current one is not active
        if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        //creating query that will be executed against the database
        SQLQuery q = session.createSQLQuery(sql);
        
        q.addEntity(Film.class);
        
        filmList = (List<Film>) q.list();
                
        session.getTransaction().commit();
        
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return filmList.get(0).getFilmId();
    }
    
    //this method is going to insert a film actor into the film actor table
    private int insertFilmActor(int actor, int film, Timestamp timeStamp){
        int result = 0;
        
        String sql = "insert into film_actor values (:actorId, :filmId, :update)";
        
        try {
            
            //beggining transaction of the current one is not active
        if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        //creating query that will be executed against the database
        SQLQuery q = session.createSQLQuery(sql);
        
        q.addEntity(FilmActor.class);
        
        q.setParameter("actorId", actor);
        q.setParameter("filmId", film);
        q.setParameter("update", timeStamp);
        
        
        result = q.executeUpdate();
        
        session.getTransaction().commit();
        
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    //this will insert a film category into the film category table
    private int insertFilmCategory(int film, int category, Timestamp timeStamp){
        int result = 0;
        
        String sql = "insert into film_category values (:filmId, :categoryId, :update)";
        
        try {
            
            //beggining transaction of the current one is not active
        if(!this.session.getTransaction().isActive()){
            session.beginTransaction();
        }
        //creating query that will be executed against the database
        SQLQuery q = session.createSQLQuery(sql);
        
        q.addEntity(FilmCategory.class);
        
        q.setParameter("categoryId", category);
        q.setParameter("filmId", film);
        q.setParameter("update", timeStamp);
        
        
        result = q.executeUpdate();
        
        session.getTransaction().commit();
        
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public int insert(String title, String description, int actor, int category,
            int language, String rating, Timestamp timeStamp){
        
    int result = 0;
    
    int filmResult = insertFilm(title, description, language, rating, timeStamp);
    int filmId = getFilmId();
    int actorResult = insertFilmActor(actor, filmId, timeStamp);
    int categoryResult = insertFilmCategory(filmId, category, timeStamp);
    
    if(filmResult == 1 && actorResult == 1 && categoryResult == 1){
        result = 1;
    }
    return result;
    }
}
