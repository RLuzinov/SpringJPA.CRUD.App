package project.learn.rluzinov.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "age", nullable = false)
    @Min(value = 1500, message = "Старше книг не бывает")
    private int age;
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Навзвание не должно быть пустым")
    @Size(min = 3, max = 100, message = "Навзание книги не должно быть меньше 3 и больше 100 символов")
    private String name;
    @Column(name = "author", nullable = false)
    @NotEmpty(message = "Имя автора не должно быть пустым")
    @Size(min = 3,max = 100,message = "Имя автора не должно быть меньше 3 и больше 100 символов")
    private String author;

    @ManyToOne
    @JoinColumn(name="people_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "bookAp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookAp;

    @Transient
    private boolean expired;
}
