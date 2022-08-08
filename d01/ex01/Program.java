package d01.ex01;

public class Program {

    public static void main(String[] args) {
        System.out.println("Example of creating 3 objects whose id changes to" +
                "1 each time the object is created");
        User userOne = new User("Mike", 10);
        System.out.println("Name: " + userOne.getName() + ", id: " +userOne.getIdentifier());

        User userTwo = new User("John", 45);
        System.out.println("Name: " + userTwo.getName() + ", id: " +userTwo.getIdentifier());

        User userThree = new User("Tom", 110);
        System.out.println("Name: " + userThree.getName() + ", id: " +userThree.getIdentifier());

        System.out.println("-------------------------------------------------");

        System.out.println("\nAn example of how a singleton works. Only one object is created");
        UserIdsGenerator testOne = UserIdsGenerator.getInstance();
        System.out.println("TestOne id: " + testOne);

        UserIdsGenerator testTwo = UserIdsGenerator.getInstance();
        System.out.println("TestTwo id: " + testTwo);
    }
}
