package models;

public class BookModel {
    private int age;
    private String name;
    private String author;

    public BookModel(){  }
    public BookModel (int age, String name, String author){
        this.age = age;
        this.name = name;
        this.author = author;
    }
    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName(){
        return name;
     }
     public void setName(){
        this.name = name;
     }
     public String getAuthor(){
        return  author;
     }
     public  void setAuthor(){
        this.author = author;
     }

}
