package be.kdg.programming3.mangaStore.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Mangaka")
public class Mangaka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private char gender;
    private int years;

    @ManyToMany(mappedBy = "mangaka")
    private transient List<Manga> mangas = new ArrayList<>();

    public Mangaka(int id, String name, char gender, int years) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.years = years;
    }

    public Mangaka( String name, char gender, int years) {
        this.name = name;
        this.gender = gender;
        this.years = years;
    }

    protected Mangaka() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public List<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(List<Manga> mangas) {
        this.mangas = mangas;
    }

    public void addManga(Manga manga) {
        if (mangas==null) this.mangas = new ArrayList<>();
        this.mangas.add(manga);
    }

    @Override
    public String toString() {
        return "Mangaka{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", years=" + years +
                ", mangas=" + mangas +
                '}';
    }
}
