import java.util.ArrayList;
import java.util.List;

public class Ring {
    boolean debug = false;
    List<Server> sortedList;
    List<Request> requests;
    public Ring(){
        this.sortedList = new ArrayList<Server>();
        this.requests = new ArrayList<Request>();
    }

    public void setDebug(boolean debug){
        this.debug = debug;
    }

    public int addServer(String name, int degree){
        Server server = new Server(name, degree);
        if(debug)
            System.out.println("ADD "+ server);
        for(int i = 0; i < sortedList.size(); i++){
            Server serverInList = sortedList.get(i);
            if(degree <= serverInList.getDegree()){
                int count = 0;
                sortedList.add(i, server);
                if(degree != serverInList.getDegree()){
                    count = updateReassignedRequests(server, "Added");
                }
                return count;
            }
        }
        sortedList.add(server);
        return updateReassignedRequests(server, "Added");
    }

    int updateReassignedRequests(Server server, String action){
        int count = 0;
        for(int i = 0; i < requests.size(); i++){
            Request r = requests.get(i);
            Server s = r.getAssignedServer();
            Server newServer = findServerForRequest(r);
            if(action.equals("Removed")){
                if(!s.equals(server))
                    continue;
            } else if(action.equals("Added")){
                if(!newServer.equals(server))
                    continue;
                if(newServer.getDegree() == s.getDegree())
                    continue;

            }

            if(!s.equals(newServer)){
                count++;
                r.setAssignedServer(newServer);
                if(debug)
                    System.out.println(r + " Reassigned from " + s + " to "+ newServer);
            }
        }
        return count;
    }

    public int removeServer(String name){
        for(int i = 0; i < sortedList.size(); i++){
            Server server = sortedList.get(i);
            if(name.equals(server.getName())){
                if(debug)
                    System.out.println("REMOVE "+ name+ " "+server.getDegree());
                sortedList.remove(i);
                return updateReassignedRequests(server, "Removed");
            }
        }
        if(debug)
            System.out.println(name + " server not found on ring");
        return 0;
    }

    public boolean isEmpty(){
        return sortedList.isEmpty();
    }

    public Server findServerForRequest(Request request){
        for(int i = 0; i < sortedList.size(); i++){
            if(request.getDegree() <= sortedList.get(i).getDegree()){
                return sortedList.get(i);
            }
        }
        return sortedList.get(0);
    }

    public void addRequest(Request request){
        requests.add(request);
    }
}
