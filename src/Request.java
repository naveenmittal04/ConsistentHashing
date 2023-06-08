public class Request {
    String name;
    int degree;
    Server assignedServer;
    public Request(String name, int degree){
        this.name = name;
        this.degree = degree;
    }
    public String getName(){
        return name;
    }
    public int getDegree(){
        return degree;
    }
    public Server getAssignedServer(){
        return assignedServer;
    }
    public void setAssignedServer(Server server){
        this.assignedServer = server;
    }

    public String toString(){
        return name + " " + degree;
    }
}
