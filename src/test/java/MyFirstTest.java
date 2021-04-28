import org.junit.jupiter.api.Test;

public class MyFirstTest {

    @Test
    public void firstTest() {
        System.out.println("Hello, World!");

        int primitive = 0;
        Integer nonPrimitive = null;

        System.out.println(primitive);
        System.out.println(nonPrimitive);

        String firstName = "Dmitro";
        String lastName = "Coronovich";
        String email = "dmitro@colona.lv";
        int age = 35;

        System.out.println("First name: " + firstName + " Last name: " + lastName + " ");

        int commentCount = 36;
        int newComments = 22;

        String stringCommentCount = "36";
        String stringNewComment = "22";

        System.out.println(commentCount + newComments);
        System.out.println(stringCommentCount + stringNewComment);

        printSum("15","3");
        printSum("268", "523");
    };

        private void printSum (String a, String b) {
            int first = Integer.parseInt(a);
            int second = Integer.parseInt(b);

            System.out.println("summa " + (first + second));

        };
}
