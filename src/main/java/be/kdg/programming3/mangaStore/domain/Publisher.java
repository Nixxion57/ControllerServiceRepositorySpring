package be.kdg.programming3.mangaStore.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.REMOVE)
    private transient List<Manga> mangas = new ArrayList<>();

    public Publisher(String name, String address, LocalDate startDate) {
        this.name = name;
        this.address = address;
        this.startDate = startDate;
    }

    public Publisher(int id, String name, String address, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.startDate = startDate;
    }

    protected Publisher() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void addManga(Manga manga) {
        this.mangas.add(manga);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", startDate=" + startDate +
                ", mangas=" + mangas +
                '}';
    }
}
