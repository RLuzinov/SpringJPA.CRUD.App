package project.learn.rluzinov.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @Min(value = 1500, message = "Старше книг не бывает")
    private int age;
    @NotEmpty(message = "Навзвание не должно быть пустым")
    @Size(min = 3, max = 100, message = "Навзание книги не должно быть меньше 3 и больше 100 символов")
    private String name;
    @NotEmpty(message = "Имя автора не должно быть пустым")
    @Size(min = 3,max = 100,message = "Имя автора не должно быть меньше 3 и больше 100 символов")
    private String author;

    public Book() {
    }

    public Book(int id, int age, String name, String author) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
