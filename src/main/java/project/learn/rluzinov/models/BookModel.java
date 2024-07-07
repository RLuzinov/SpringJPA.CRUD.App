package project.learn.rluzinov.models;

public class BookModel {
    private int id;
    private int age;
    private String name;
    private String author;

    public BookModel(){  }
    public BookModel (int age, String name, String author, int id){
        this.age = age;
        this.name = name;
        this.author = author;
        this.id = id;
    }
    public int getAge(){
        return age;
    }
    public  int getId(){
        return id;
    }
    public void setId(){
        this.id = id;
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
