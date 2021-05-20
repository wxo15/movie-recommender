
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings () {
        FourthRatings fourthRatings = new FourthRatings ("data\ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minNumOfRatings = 35; // variable
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatings(minNumOfRatings);
        System.out.println("There are " + averageRatings.size() + " movies with >= " +
        minNumOfRatings + " ratings");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre () {
        FourthRatings fourthRatings = new FourthRatings ("data\ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int year = 1990; // variable
        
        String genre = "Drama"; // variable
        
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter (year));
        af.addFilter(new GenreFilter (genre));
        
        int minNumOfRatings = 8; // variable
        ArrayList<Rating> avgRatings = fourthRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("There are " + avgRatings.size() + " " + genre + " movies after "
        + year + " with >=" + minNumOfRatings + " ratings");
        
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatings () {
        FourthRatings fourthRatings = new FourthRatings ("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        String id = "337"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 3; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        System.out.println("There are " + similarRatings.size() + " movies recommended for rater ID " + id + " with >="
        + minimalRaters + " ratings. " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre () {
        FourthRatings fourthRatings = new FourthRatings ("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Mystery"; // variable
        GenreFilter gf = new GenreFilter(genre);
         
        String id = "964"; // variable
        int numSimilarRaters = 20; // variable
        int minimalRaters = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, gf);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), in \"" + genre + "\" genre. " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector () {
        FourthRatings fourthRatings = new FourthRatings ("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; // variable
        DirectorsFilter df = new DirectorsFilter(directors);
         
        String id = "120"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 2; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, df);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), that was(were) directed by either of the following directors: "
        + directors + ". " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Directed by : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings ("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);
        
        int minMin = 80; // variable
        int maxMin = 160; // variable
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);
        
        String id = "168"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 3; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), in \"" + genre + "\" genre, that is(are) between " + minMin
        + " and " + maxMin + " minutes in length. " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
            + " Time: " + MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings ("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 1975; // variable
        
        int minMin = 70; // variable
        int maxMin = 200; // variable

        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter (year));
        af.addFilter(new MinutesFilter (minMin, maxMin));
        
        String id = "314"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), that is(are) between " + minMin + " and " + maxMin 
        + " minutes in length and released after year " + year + ". " + numSimilarRaters 
        + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
            + " Year: " + MovieDatabase.getYear(rating.getItem()) + " Time: " 
            + MovieDatabase.getMinutes(rating.getItem()));
        }
    }

}
