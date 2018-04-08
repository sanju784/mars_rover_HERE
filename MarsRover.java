import java.io.*;
import java.net.URL;

public class MarsRover {
    public static void main(String args[]) throws Exception {

        String file_name = args[0];

        //Reading the test data file which is passed as argument
        URL url = MarsRover.class.getResource(file_name);
        File file = new File(url.getPath());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;

        //Reading first line of input as plateau coordinate
        str = br.readLine();
        int m = str.charAt(0) - '0';
        int n = str.charAt(2) - '0';

        //Reading Rover details
        str = br.readLine();
        int startX = str.charAt(0) - '0';
        int startY = str.charAt(2) - '0';
        char head = str.charAt(4);

        if (startX < 0 || startY < 0 || startX > m || startY > n) {
            System.out.println("Rover is not on Plateau");
            System.exit(0);
        }
        Rover r = new Rover(startX, startY, head);
        String signal = br.readLine();
        for (int j = 0; j < signal.length(); j++) {
            char d = signal.charAt(j);
            if (d == 'L') {
                r.rotateLeft();
            } else if (d == 'R') {
                r.rotateRight();
            } else if (d == 'M') {
                int x = r.getX();
                int y = r.getY();
                char h = r.getHead();
                if ((h == 'E' && x < m) || (h == 'W' && x > 0) || (h == 'N' && y < n) || (h == 'S' && y > 0))
                    r.move();
            }
        }
        System.out.println("Rover current position ");
        System.out.println(r.getX() + " " + r.getY() + " " + r.getHead());
    }
}

class Rover {
    private int x;
    private int y;
    private char head;
    private int iHead;

    public Rover(int x, int y, char head) {
        this.x = x;
        this.y = y;
        this.head = head;
        setIHead();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getHead() {
        return head;
    }

	// Function to convert head from character to integer
	private void setIHead() {
        switch (head) {
            case 'N':
                iHead = 0;
                break;
            case 'W':
                iHead = 1;
                break;
            case 'S':
                iHead = 2;
                break;
            case 'E':
                iHead = 3;
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    // Function to rotate rover to left
    public void rotateLeft() {
        iHead = (iHead + 1) % 4;
        setHead();
    }

    // Function to rotate rover to right
    public void rotateRight() {
        if (iHead == 0) {
            iHead = 3;
        } else {
            iHead = (iHead - 1) % 4;
        }
        setHead();
    }

    // Function to move rover to next grid
    public void move() {
        switch (head) {
            case 'E':
                x++;
                break;
            case 'W':
                x--;
                break;
            case 'N':
                y++;
                break;
            case 'S':
                y--;
                break;
        }
    }

    // Function to convert rover head from integer to character
    private void setHead() {
        switch (iHead) {
            case 0:
                head = 'N';
                break;
            case 1:
                head = 'W';
                break;
            case 2:
                head = 'S';
                break;
            case 3:
                head = 'E';
                break;
        }
    }
}
