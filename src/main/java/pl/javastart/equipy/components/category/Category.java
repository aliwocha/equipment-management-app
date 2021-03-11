package pl.javastart.equipy.components.category;

import pl.javastart.equipy.components.asset.Asset;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String name;
    @Column(length = 1024)
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Asset> assets = new ArrayList<>();

    public Category() {}

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

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "Category [" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", assets=" + assets +
                ']';
    }
}
