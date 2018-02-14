
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Piano extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AudioClip[] a = new AudioClip[8];
        for (int i = 1; i <= 8; i++) {
            a[i - 1] = new AudioClip(Piano.class.getResource("music/Note" + i + ".wav").toString());
        }

        int gap = 20;
        int sourcex = 0;
        int sourcey = 10;

        Cell[] cell = new Cell[8];
        Stick[] stick = new Stick[8];
        for (int i = 0; i < 8; i++) {
            cell[i] = new Cell(sourcex + i * (gap + 50), sourcey, i + 1);
            stick[i] = new Stick(sourcex + i * (gap + 50) + 20, sourcey + 100);
        }

        Group root = new Group();
        for (int i = 0; i < 8; i++) {
            root.getChildren().add(cell[i]);
            root.getChildren().add(stick[i]);
        }

        Scene cen = new Scene(root);
        cen.setOnKeyPressed(e -> {
            String cmd = e.getCode().toString();
            int index = 0;
            if (cmd.length() == 6) {
                index = cmd.charAt(5) - '0';
            }
            if (index > 0 && index <= 8) {
                cell[index - 1].setFill(Color.WHITE);
                a[index - 1].play();
            }
        });
        cen.setOnKeyReleased(e -> {
            String cmd = e.getCode().toString();
            int index = 0;
            if (cmd.length() == 6) {
                index = cmd.charAt(5) - '0';
            }
            if (index > 0 && index <= 8) {
                index--;
                cell[index].setFill(Color.BLACK);
            }
        });

        primaryStage.setTitle("Piano");
        primaryStage.getIcons().add(new Image("Logo.PNG"));
        primaryStage.setResizable(false);
        primaryStage.setScene(cen);
        primaryStage.show();
    }
}

class Cell extends Rectangle {

    public Cell(int x, int y, int index) {
        setX(x);
        setY(y);
        setWidth(50);
        setHeight(100);
        setArcWidth(30);
        setArcHeight(30);
        setStroke(Color.CHOCOLATE);
        setStrokeWidth(5);
        setFill(Color.BLACK);
        setOnMouseEntered(val -> {
            setFill(Color.RED);
        });
        setOnMouseExited(val -> {
            setFill(Color.BLACK);
        });
    }
}

class Stick extends Rectangle {

    public Stick(int x, int y) {
        setX(x);
        setY(y);
        setWidth(10);
        setHeight(50);
        setFill(Color.GRAY);
        setOnMouseEntered(val -> {
            setFill(Color.BISQUE);
        });
        setOnMouseExited(val -> {
            setFill(Color.GRAY);
        });
    }
}
