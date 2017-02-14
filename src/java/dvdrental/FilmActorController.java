
package dvdrental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Matt
 */
@Named(value = "filmActorController")
@SessionScoped
public class FilmActorController implements Serializable {
    
    //these fields map to the options in the selec compononents in the fil.xhtml
    DataModel actorValues;
    DataModel categoryValues;
    DataModel languageValues;
    
    //this is our helper class that uses hibernate to query the database
    FilmActorHelper helper;
    
    // these feilds map to components in the film.xhtml
    //the represent the actual values input and selected by the user
    String title;
    String description;
    int actor;
    String rating;
    int category;
    int language;
    String response;
           

    /**
     * Creates a new instance of FilmActorController
     */
    public FilmActorController() {
        helper = new FilmActorHelper();
    }

    public DataModel getActorValues() {
        if(actorValues == null){
            actorValues = new ListDataModel(helper.getActors());
        }
        return actorValues;
    }

    public void setActorValues(DataModel actorValues) {
        this.actorValues = actorValues;
    }

    public DataModel getCategoryValues() {
        if(categoryValues == null){
            categoryValues = new ListDataModel(helper.getCategories());
        }
        return categoryValues;
    }

    public void setCategoryValues(DataModel categoryValues) {
        this.categoryValues = categoryValues;
    }

    public DataModel getLanguageValues() {
        if(languageValues == null){
            languageValues = new ListDataModel(helper.getLanguages());
        }
        return languageValues;
    }

    public void setLanguageValues(DataModel languageValues) {
        this.languageValues = languageValues;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActor() {
        return actor;
    }

    public void setActor(int actor) {
        this.actor = actor;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String getResponse() {
        
        if(title != null && description != null){
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            
            if(helper.insert(title, description, actor, category, language, rating, timeStamp) == 1){
                title = null;
                description = null;
                response = "Film Added.";
                return response;
                
            } else{
                title = null;
                description = null;
                response = "Film Not Added.";
                return response;
            }
        } else {
            response = " ";
            return response;
        }
        
    }
    

    public void setResponse(String response) {
        this.response = response;
    }
    
    
    
}
