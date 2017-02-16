
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
    //this field is needed to get the movie selected in the index.xhtml
    // it maps to the components in the browse.xhtml
    private Film selected;
    //these fields map to the components int he browse.xhtml
    String language;
    String actors;
    String category;

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
    //this method will return true if it makes sense to add the next button
    public boolean isHasNextPage() {
        if (startId + pageSize < recordCount){
            return true;
        }
        return false;
    }
    //this method will return true if it makes sense to add the Previous button
    public boolean isHasPreviousPage(){
        if(startId - pageSize > 0){
            return true;
        }
        return false;
    }

    public Film getSelected() {
        if(selected == null){
            selected = new Film();
        }
        return selected;
    }

    public void setSelected(Film selected) {
        this.selected = selected;
    }

    public String getLanguage() {
        int LangID = selected.getLanguageByLanguageId().getLanguageId().intValue();
        language = helper.getLangByID(LangID);
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getActors() {
        List actors = helper.getActorsByID(selected.getFilmId());
        //convery list to string
        StringBuilder cast = new StringBuilder();
        for(int i = 0; i < actors.size(); i++){
            Actor actor = (Actor) actors.get(i);
            cast.append(actor.getFirstName());
            cast.append(" ");
            cast.append(actor.getLastName());
            cast.append(" ");
        }
        return cast.toString();
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getCategory() {
        Category category = helper.getCategoryID(selected.getFilmId());
        
        return category.getName();
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String prepareView(){
        selected = (Film) getFilmTitles().getRowData();
        return "browse";
    }
    
}
