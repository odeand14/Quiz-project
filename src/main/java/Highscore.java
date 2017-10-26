import com.j256.ormlite.field.DatabaseField;

// Created by Andreas Odegaard on 07.12.2016.

/**
 * Class for ORMlite usage with the Highscore table
 */
public class Highscore implements Comparable<Highscore>{

    @DatabaseField
    String username;
    @DatabaseField
    int score;

    public Highscore() {
    }


    public Highscore(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "username: " + username + "\tscore: " + score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Highscore highscore = (Highscore) o;

        if (score != highscore.score) return false;
        return username.equals(highscore.username);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + score;
        return result;
    }

    /**
     * compareTo method for easy sorting of Highscorelist.
     * @param object other Highscore object with which to compare
     * @return int based on compare
     */
    public int compareTo(Highscore object) {
        return this.score > object.getScore() ? -1 : this.score < object.getScore() ? 1 : 0;
    }
}
