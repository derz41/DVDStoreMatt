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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getFilmTitles(int startId) {
        List<Film> filmList = null;

        String sql = "select * from film order by title limit :start, :end";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            //creating query that will be executed against the database
            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Film.class);

            q.setParameter("start", startId);
            q.setParameter("end", 10);

            filmList = (List<Film>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filmList;
    }

    public int getNumberFilms() {
        List<Film> filmList = null;

        String sql = "select * from film";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            //creating query that will be executed against the database
            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Film.class);

            filmList = (List<Film>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filmList.size();
    }

    public List getActorsByID(int filmId) {
        List<Actor> actorList = null;

        String sql = "select * from actor, film_actor, film "
                + "where actor.actor_id = film_actor.actor_id "
                + "and film_actor.film_id = film.film_id "
                + "and film.film_id = :id";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            //creating query that will be executed against the database
            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Actor.class);

            q.setParameter("id", filmId);

            actorList = (List<Actor>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return actorList;
    }
    
    public Category getCategoryID (int filmId) {
        List<Category> categoryList = null;
        
        String sql = "select * from category, film_category, film "
                + "where category.category_id = film_category.category_id "
                + "and film_category.film_id = film.film_id "
                + "and film.film_id = :id";
        
        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            //creating query that will be executed against the database
            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Category.class);

            q.setParameter("id", filmId);

            categoryList = (List<Category>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return categoryList.get(0);
    }
    
    public Film getFilmDetails (int filmId) {
        Film film = null;
        
        String sql = "select * from film where film_id = :id";
        
        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            //creating query that will be executed against the database
            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Film.class);

            q.setParameter("id", filmId);

            film = (Film) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return film;
    }
    
    public String getLangByID (int langId){
        Language language = null;
        
        String sql = "select * from language where language_id = :id";
        
        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            //creating query that will be executed against the database
            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Language.class);

            q.setParameter("id", langId);

            language = (Language) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return language.getName();
    }

}
