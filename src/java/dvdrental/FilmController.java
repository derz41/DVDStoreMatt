
package dvdrental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Matt
 */
@Named(value = "filmController")
@SessionScoped
public class FilmController implements Serializable {
    
    int startId;
    
    DataModel filmTitles;
    
    FilmHelper helper;
    
    private int recordCount;
    
    private int pageSize = 10;

    /**
     * Creates a new instance of FilmController
     */
    public FilmController() {
        
        helper = new FilmHelper();
        startId = 0;
        
        recordCount = helper.getNumberFilms();
        
    }

    public DataModel getFilmTitles() {
        if(filmTitles == null){
            filmTitles = new ListDataModel(helper.getFilmTitles(startId));
        }
        return filmTitles;
    }

    public void setFilmTitles(DataModel filmTitles) {
        this.filmTitles = filmTitles;
    }
    //this methos sets filmTitles to null
    //if this feild is null when the index.xhtml page reloads, then more
    //films will be loaded
    private void recreateModel() {
        filmTitles = null;
        recordCount = helper.getNumberFilms();
    }
    //this method is called when the next hyperlink is pushed
    //it increments the startId by the page size and then forces the page to reload 
    // more films
    public String next(){
        startId = startId +(pageSize + 1);
        recreateModel();
        return "index";
    }
    //this method is called when the previous hyperlink is pushed
    //it decrements the startId by the page size and then forces the page to reload 
    // more films
    public String previous(){
        startId = startId -(pageSize + 1);
        recreateModel();
        return "index";
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    
    
}
