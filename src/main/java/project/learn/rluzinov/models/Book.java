package project.learn.rluzinov.models;

public class Book {
    private int id;
    private int age;
    private String name;
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
