public class Server {
    String name;
    int degree;
    public Server(String name, int degree){
        this.name = name;
        this.degree = degree;
    }
    public String getName(){
        return name;
    }
    public int getDegree(){
        return degree;
    }

    public boolean equals(Server s){
        return this.name == s.getName();
    }

    public String toString(){
        return name + " " + degree;
    }
}
