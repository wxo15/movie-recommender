
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings () {
        SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("Movie number: " + secondRatings.getMovieSize());
        System.out.println("Rater number: " + secondRatings.getRaterSize());
        
        int MinNumOfRatings = 12; // variable
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(MinNumOfRatings);
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }
        System.out.println(averageRatings.size() + " movies with >=" + MinNumOfRatings + " ratings");
        
    }
    public void printAverageRatingsOneMovie () {
        SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("Movie number: " + secondRatings.getMovieSize());
        System.out.println("Rater number: " + secondRatings.getRaterSize());
        
        String movieName = "Vacation"; // variable
        int MinNumOfRatings = 3; // variable
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(MinNumOfRatings);
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            if (rating.getItem().equals(secondRatings.getID(movieName))){
                System.out.println(secondRatings.getTitle(rating.getItem()) + " has " + rating.getValue());
            }
        }
    }
}
