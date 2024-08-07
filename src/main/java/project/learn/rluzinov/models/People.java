package project.learn.rluzinov.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.learn.rluzinov.dao.BookDao;

@Entity
@Table(name = "People")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Имя не должно быть путсым")
    @Size(min = 3, max = 100, message = "Имя должно состовлять от 3 до 100 символов")
    private String name;
    @Column(name = "age", nullable = false)
    @Min(value = 16, message = "Возраст посещения библиотеки - не менее 16 лет")
    @Max(value = 100, message = "Возраст не может привышать 100 лет")
    private int age;
}