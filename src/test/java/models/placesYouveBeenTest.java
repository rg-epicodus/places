package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class placesYouveBeenTest {

    @After
    public void tearDown() throws Exception {
        placesYouveBeen.clearAllPlaces();
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void placesYouveBeen_instantiatesCorrectly() throws Exception{
        placesYouveBeen places = new placesYouveBeen("Canada");
        assertEquals(true, places instanceof placesYouveBeen);
    }

    @Test
    public void placesYouveBeen_entryInstantiatesWithContent_Canada() throws Exception {
        placesYouveBeen places = new placesYouveBeen("Canada");
        assertEquals("Canada", places.getPlaces());
    }

    @Test
    public void placesYouveBeen_EntryReturnsContent_1() {
        placesYouveBeen places = new placesYouveBeen("Canada");
        assertEquals(1, places.getAllPlaces().size());
    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        placesYouveBeen places = new placesYouveBeen("Canada");
        assertEquals(false, places.getPublished()); //should never start as published
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception {
        placesYouveBeen myPlaces = newPlaces();
        assertEquals(LocalDateTime.now().getDayOfWeek(), myPlaces.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void getId_placesInstantiateWithAnID_1() throws Exception{
        placesYouveBeen.clearAllPlaces();  // Remember, the test will fail without this line! We need to empty leftover Posts from previous tests!
        placesYouveBeen myPlace = new placesYouveBeen("Canada");
        assertEquals(1, myPlace.getId());
    }

    @Test
    public void findReturnsCorrectPlace() throws Exception {
        placesYouveBeen post = newPlaces();
        assertEquals(1, placesYouveBeen.findById(post.getId()).getId());
    }

    @Test
    public void findReturnsCorrectPlaceWhenMoreThanOnePlaceExists() throws Exception {
        placesYouveBeen places = newPlaces();
        placesYouveBeen otherPlace = new placesYouveBeen("Mexico");
        assertEquals(2, placesYouveBeen.findById(otherPlace.getId()).getId());
    }

    @Test
    public void updateChangesPostContent() throws Exception {
        placesYouveBeen places = newPlaces();
        String formerContent = places.getPlaces();
        LocalDateTime formerDate = places.getCreatedAt();
        int formerId = places.getId();

        places.update("Android: Day 40");

        assertEquals(formerId, places.getId());
        assertEquals(formerDate, places.getCreatedAt());
        assertNotEquals(formerContent, places.getPlaces());
    }
    @Test
    public void deleteDeletesASpecificPost() throws Exception {
        placesYouveBeen places = newPlaces();
        placesYouveBeen otherPost = new placesYouveBeen("How to pair successfully");
        places.deletePost();
        assertEquals(1, placesYouveBeen.getAllPlaces().size()); //one is left
        assertEquals(placesYouveBeen.getAllPlaces().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
    }
    @Test
    public void deleteAllPostsDeletesAllPosts() throws Exception {
        placesYouveBeen post = newPlaces();;
        placesYouveBeen otherPost = newPlaces();

        placesYouveBeen.clearAllPlaces();
        assertEquals(0, placesYouveBeen.getAllPlaces().size());
    }

    // Helpers
    public placesYouveBeen newPlaces() {
        return new placesYouveBeen("Mexico");
    }
}