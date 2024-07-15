package project.learn.rluzinov.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import project.learn.rluzinov.dao.BookDao;

public class People {

    private int id;
    @NotEmpty(message = "Ощибка имени")
    @Size(min = 3, max = 100, message = "Имя должно состовлять от 3 до 100 символов")
    private String name;
    @Min(value = 16, message = "Возраст посещения библиотеки - не менее 16 лет")
    @Max(value = 100, message = "Возраст не может привышать 100 лет")
    private int age;
    private int id_book;
    public  People(){}

    public People(int id, String name, int age, int id_book) {
        this.id = id;

        this.name = name;
        this.age = age;
        this.id_book = id_book;
    }

    public People(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
}
