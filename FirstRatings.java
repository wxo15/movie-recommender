import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies (String filename){
        ArrayList<Movie> movieData = new ArrayList<Movie> ();
        FileResource fr = new FileResource(filename);
        for (CSVRecord record: fr.getCSVParser()){
            String currentID = record.get(0);
            String currentTitle = record.get(1);
            String currentYear = record.get(2);
            String currentCountry = record.get(3);
            String currentGenre = record.get(4);
            String currentDirector = record.get(5);
            int currentMinutes = Integer.parseInt(record.get(6));
            String currentPoster = record.get(7);
            
            Movie currentMovie = new Movie(currentID, currentTitle, currentYear, currentGenre, currentDirector, 
            currentCountry, currentPoster, currentMinutes);
            
            movieData.add(currentMovie);
        
        
        }
        return movieData;
    }
    
    public void testLoadMovies (){
        ArrayList<Movie> test = loadMovies("data/ratedmoviesfull.csv");
        
        //get comedy
        int numComedyMovie = 0;
        for (Movie movie : test){
            System.out.println(movie.toString());
            if (movie.getGenres().contains("Comedy")){
                numComedyMovie += 1;
            }
            
        }
        System.out.println("Comedy: " + numComedyMovie);
        
        //get >150 minute
        int numGT150Movie = 0;
        for (Movie movie : test){
            if (movie.getMinutes() > 150){
                numGT150Movie += 1;
            }
        }
        System.out.println(numGT150Movie);
        
        
        HashMap<String,Integer> countMoviesByDirector = new HashMap<String,Integer> ();
        for (Movie movie : test) {
            String[] directors = movie.getDirector().split(",");
            for (String director : directors ) {
                director = director.trim();
                if (! countMoviesByDirector.containsKey(director)) {
                    countMoviesByDirector.put(director, 1);
                } else {
                    countMoviesByDirector.put(director, countMoviesByDirector.get(director) + 1);
                }
            }
        }
        
        //count max number of movies by 1 director
        int maxmoviecount = 0;
        String maxdirector = null;
        for (String director : countMoviesByDirector.keySet()) {
            if (countMoviesByDirector.get(director) > maxmoviecount) {
                maxmoviecount = countMoviesByDirector.get(director);
                maxdirector = director;
            }
        }
        System.out.println(maxdirector + " " + maxmoviecount);
        
    }
    
    public ArrayList<Rater> loadRaters (String filename){
        ArrayList<Rater> ratersData = new ArrayList<Rater> ();
        ArrayList<String> IDlist = new ArrayList<String> ();
        FileResource fr = new FileResource(filename);
        for (CSVRecord record: fr.getCSVParser()){
            String currentRaterID = record.get(0);
            String currentMovieID = record.get(1);
            double currentMovieRating = Double.parseDouble(record.get(2));
            
            if (IDlist.indexOf(currentRaterID)==-1) {
                Rater currentRater = new PlainRater(currentRaterID);
                ratersData.add(currentRater);
                currentRater.addRating(currentMovieID, currentMovieRating);
            
            } else {
                for (int k=0; k < ratersData.size(); k++) {
                    if (ratersData.get(k).getID().equals(currentRaterID)) {
                        ratersData.get(k).addRating(currentMovieID, currentMovieRating);
                    }
                }
            }
            IDlist.add(currentRaterID);
        }
        return ratersData;
    }
    
    public void testLoadRates (){
        ArrayList<Rater> test = loadRaters("data/ratings.csv");
        
        //get total
        System.out.println(test.size());
        
        //get rater and number of ratings
        HashMap<String, HashMap<String, Double>> hashmap = new HashMap<String, HashMap<String, Double>> ();
        for (Rater rater : test) {
            HashMap<String, Double> ratings = new HashMap<String, Double> ();
            ArrayList<String> itemsRated = rater.getItemsRated();
            
            for (int i=0; i < itemsRated.size(); i++) {
                String movieID = itemsRated.get(i);
                double movieRating = rater.getRating(movieID);
                
                ratings.put(movieID, movieRating);
            }
            hashmap.put(rater.getID(), ratings);
        }
        
        for (String ID : hashmap.keySet()) {
            System.out.println(ID + " " + hashmap.get(ID).size());
        }
        System.out.println("Number of raters " + hashmap.size());
        String raterID = "193"; //rater_id
        System.out.println("Number of ratings for the rater " + raterID + " : " + hashmap.get(raterID).size());
        
        //get rater with max number of ratings
        int maxNumRating = 0;
        int maxRaterCount = 0;
        String name=null;
        for (String ID : hashmap.keySet()) {
            if (maxNumRating < hashmap.get(ID).size()){
                maxNumRating = hashmap.get(ID).size();
                maxRaterCount = 1;
                name = ID;
            } else{
                if (maxNumRating == hashmap.get(ID).size()){
                    maxRaterCount += 1;
                }
            }  
        }
        System.out.println("Max # = " + maxNumRating + " by " + name + "("+ maxRaterCount + " users)");
        
        String movieID = "1798709";
        int numRatings = 0;
        for (String key : hashmap.keySet()) {
            if(hashmap.get(key).containsKey(movieID)) {
                numRatings +=1;
            }
        }
        System.out.println("Number of ratings movie " + movieID + " has : " + numRatings);
        
        
        ArrayList<String> uniqueMovies = new ArrayList<String> ();
        for (String key : hashmap.keySet()) {
            for (String currMovieID : hashmap.get(key).keySet()) {
                if (! uniqueMovies.contains(currMovieID)) {
                    uniqueMovies.add(currMovieID);
                }
            }
        }
        System.out.println("Total number of movies that were rated : " + uniqueMovies.size());
        
    }
}
