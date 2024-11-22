public class Main {
    
    public static void main(String[] args) {
        // create the View (UI)
        View view = new View();
        
        // create the Controller and link it to the View
        new Controller(view);
    }
}