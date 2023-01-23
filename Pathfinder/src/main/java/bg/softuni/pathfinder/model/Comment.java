package bg.softuni.pathfinder.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean approved;

    @Column
    private LocalDateTime created;

    @Column(columnDefinition = "TEXT", nullable = false, name = "text_content")
    private String text;

    @ManyToOne//един юзър може да оставя много коментари
    //един коментар може да е написан само от един автор
    private User author;

    @ManyToOne//един тур може да има много коментари
    //но даден коментар е написан за точно определен тур
    private Route route;

    public Comment() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}

