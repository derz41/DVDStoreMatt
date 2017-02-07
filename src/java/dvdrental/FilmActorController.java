
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
    
    
    
}
