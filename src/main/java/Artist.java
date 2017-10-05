
import com.j256.ormlite.field.DatabaseField;

// Created by Andreas on 14.11.2016.

/**
 * Class for ORMlite usage with the Artist table
 */
public class Artist {

    @DatabaseField
    String name;
    @DatabaseField
    String song;
    @DatabaseField
    String album;
    @DatabaseField
    String nationality;
    @DatabaseField
    int year;


    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", song='" + song + '\'' +
                ", album='" + album + '\'' +
                ", nationality='" + nationality + '\'' +
                ", year=" + year +
                '}';
    }

    public Artist() {
    }

    public Artist(String name, String song, String album, String nationality, int year) {
        this.name = name;
        this.song = song;
        this.album = album;
        this.nationality = nationality;
        this.year = year;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;

        Artist artist = (Artist) o;

        if (year != artist.year) return false;
        if (name != null ? !name.equals(artist.name) : artist.name != null) return false;
        if (song != null ? !song.equals(artist.song) : artist.song != null) return false;
        if (album != null ? !album.equals(artist.album) : artist.album != null) return false;
        return nationality != null ? nationality.equals(artist.nationality) : artist.nationality == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (song != null ? song.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }
}
