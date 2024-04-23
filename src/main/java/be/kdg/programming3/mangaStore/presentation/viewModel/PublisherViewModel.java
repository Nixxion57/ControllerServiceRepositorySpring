package be.kdg.programming3.mangaStore.presentation.viewModel;

import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Profile("web")
public class PublisherViewModel {
    private int id;

    @NotBlank(message = "This field is mandatory!")
    @Size(min=3, max=40, message = "Name is mandatory!")
    private String name;
    private String address;
    private @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate;

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

    @Override
    public String toString() {
        return "PublisherViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
