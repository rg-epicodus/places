package models;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class placesYouveBeen {

    private String places;
    private static ArrayList<placesYouveBeen> allPlaces = new ArrayList<placesYouveBeen>();
    private boolean published;
    private LocalDateTime createdAt;
    private int id;


    public  placesYouveBeen (String places) {
        this.places = places;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        allPlaces.add(this);
        this.id = allPlaces.size();
    }

    public String getPlaces() {
        return places;
    }

    public static ArrayList<placesYouveBeen> getAllPlaces() {
        return allPlaces;
    }

    public static void clearAllPlaces(){
        allPlaces.clear();
    }

    public boolean getPublished() {
        return this.published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public static placesYouveBeen findById(int id){
        return allPlaces.get(id-1);
    }

    public void update(String places) {
        this.places = places;
    }
    public void deletePost(){
        allPlaces.remove(id-1);
    }

}
