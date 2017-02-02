/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Matt
 */
@Named(value = "actorController")
@SessionScoped
public class ActorController implements Serializable {
    
    //these fields map directly to components in the actory.xhtml
    String firstName;
    String lastName;
    String response;
    
    //the class that uses hibernate to query the actory table
    ActorHelper helper;
    
    //creates a new instance of the ActorController
    Actor actor;

    /**
     * Creates a new instance of ActorController
     */
    public ActorController() {
        helper = new ActorHelper();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResponse() {
        
        if(firstName != null && lastName !=null){
            //getting the vurrent date in sql format
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            
            //initializing an actor
            actor = new Actor(firstName, lastName, timeStamp);
            
            //calling helper method that inserts a row into the actory table
            if(helper.insertActor(actor) == 1){
                //insert was successful
                firstName = null;
                lastName = null;
                response = "Actor Added";
                return response;
            } else {
                //insert failed
                firstName = null;
                lastName = null;
                response = "Actor Not Added.";
                return response;
            }
        } else {
            // dont display message wen user has not input first and last name
            response = " ";
            return response;
        }
        
    }

    public void setResponse(String resonse) {
        this.response = resonse;
    }
    
    
    
}
