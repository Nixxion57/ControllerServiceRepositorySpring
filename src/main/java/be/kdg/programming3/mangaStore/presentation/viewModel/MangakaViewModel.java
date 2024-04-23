package be.kdg.programming3.mangaStore.presentation.viewModel;

import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.context.annotation.Profile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Profile("web")
public class MangakaViewModel {
    private int id;

    @NotBlank(message = "This field is mandatory!")
    @Size(min=3, max=100, message = "Name should have length between 2 and 100")
    private String name;
    private char gender;

    @NotNull(message = "This field is mandatory!")
    @Min(1)
    private int years;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public int getYears() {
        return years;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setYears(int years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "MangakaViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", years=" + years +
                '}';
    }
}
