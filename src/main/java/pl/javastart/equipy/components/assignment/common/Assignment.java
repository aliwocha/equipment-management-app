package pl.javastart.equipy.components.assignment.common;

import pl.javastart.equipy.components.asset.Asset;
import pl.javastart.equipy.components.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "asset_id")
    private Asset asset;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    public Assignment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Assignment [" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", asset=" + asset.getName() +
                ", user=" + user.getId() +
                ']';
    }
}
