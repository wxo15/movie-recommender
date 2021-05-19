
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings () {
        ThirdRatings thirdRatings = new ThirdRatings ("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minNumOfRatings = 35; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(minNumOfRatings);
        System.out.println("There are " + averageRatings.size() + " movies with >=" +
        minNumOfRatings + " ratings");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfter () {
        ThirdRatings thirdRatings = new ThirdRatings ("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int year = 2000; // variable
        
        int minNumOfRatings = 20; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, new YearAfterFilter (year));
        System.out.println("There are " + averageRatings.size() + " movies after " + year + " with >=" + minNumOfRatings + " ratings");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre () {
        ThirdRatings thirdRatings = new ThirdRatings ("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Comedy"; // variable
        GenreFilter gf = new GenreFilter (genre);
        
        int minNumOfRatings = 20; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, gf);
        System.out.println("There are " + averageRatings.size() + " " + genre + " movies with >=" + minNumOfRatings + "  ratings");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("   " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes () {
        ThirdRatings thirdRatings = new ThirdRatings ("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int minMinutes = 105; // variable
        int maxMinutes = 135; // variable
        
        int minNumOfRatings = 5; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, new MinutesFilter (minMinutes, maxMinutes));
        System.out.println("There are " + averageRatings.size() + " movies that are between " 
        + minMinutes + " and " + maxMinutes + " length with >=" + minNumOfRatings + "more ratings");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors () {
        ThirdRatings thirdRatings = new ThirdRatings ("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String directorsList = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"; // variable
        DirectorsFilter df = new DirectorsFilter (directorsList);
        
        int minNumOfRatings = 4; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, df);
        System.out.println("There are " + averageRatings.size() + " movies that were directed by:"
        +  directorsList + ", with >=" + minNumOfRatings + " ratings");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("   " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre () {
        ThirdRatings thirdRatings = new ThirdRatings ("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 1990; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);
        
        String genre = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        
        int minNumOfRatings = 8; // variable
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("There are " + avgRatings.size() + " " + genre + " movies after "
        + year + " with >=" + minNumOfRatings + " ratings");
        
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            //System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            //+ " " + MovieDatabase.getTitle(rating.getItem()));
            //System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes () {
        ThirdRatings thirdRatings = new ThirdRatings ("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String directorsList = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"; // variable
        DirectorsFilter df = new DirectorsFilter (directorsList);
        
        int minMinutes = 90; // variable
        int maxMinutes = 180; // variable
        MinutesFilter mf = new MinutesFilter (minMinutes, maxMinutes);
        
        AllFilters af = new AllFilters();
        af.addFilter(df);
        af.addFilter(mf);
        
        int minNumOfRatings = 3; // variable
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("There are " + avgRatings.size() + " movies by either : "
        + directorsList + ", between " + minMinutes + " and " + maxMinutes + " min with >= " + minNumOfRatings + "ratings");
        
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
}
