package pl.javastart.equipy.components.asset;

import pl.javastart.equipy.components.category.Category;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Column(length = 1024)
    private String description;
    @NotEmpty
    @Column(unique = true)
    private String serialNumber;
    @NotEmpty
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    public Asset() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Asset [" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", serialNumber=" + serialNumber +
                ", category=" + category +
                ']';
    }
}
