import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.*;

import java.util.Arrays;
import java.util.List;


public class Main extends Application {

    private String filename = "";
    private boolean isInputFileEncoded = false;
    private String outputFileExtension = "";
    private boolean isOutputFileEncoded = false;
    private boolean isOutputFileArchived = false;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean getIsInputFileEncoded() {
        return isInputFileEncoded;
    }

    public void setInputFileEncoded(boolean inputFileEncoded) {
        isInputFileEncoded = inputFileEncoded;
    }

    public String getOutputFileExtension() {
        return outputFileExtension;
    }

    public void setOutputFileExtension(String outputFileExtension) {
        this.outputFileExtension = outputFileExtension;
    }

    public boolean getIsOutputFileEncoded() {
        return isOutputFileEncoded;
    }

    public void setOutputFileEncoded(boolean outputFileEncoded) {
        isOutputFileEncoded = outputFileEncoded;
    }

    public boolean getIsOutputFileArchived() {
        return isOutputFileArchived;
    }

    public void setOutputFileArchived(boolean outputFileArchived) {
        isOutputFileArchived = outputFileArchived;
    }

    private String getExtension(String fileName){
        int i = fileName.lastIndexOf('.');
        if (i > 0 && i < fileName.length() - 1)
            return fileName.substring(i + 1, fileName.length() - 1).toLowerCase();

        return "";
    }

    private void processingUnit(String finName, boolean isFinEncoded, String foutExtension, boolean isFoutEncoded, boolean isFoutArchived){
        String mainString = "";

        if(finName.endsWith(".zip"))
            mainString = IUnarchive.unarchive(finName);
        else
            mainString = IReader.read(finName);

        if (isFinEncoded)
            mainString = IDecoder.decode(mainString);

        mainString = IProcess.process(mainString);


        if (isFoutEncoded)
            mainString = IEncoder.encode(mainString);

        if(isFoutArchived)
            IArchive.archive(foutExtension, mainString);
        else
            IWriter.write(foutExtension, mainString);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("ReadProcessWrite");
        stage.setMinHeight(435);
        stage.setMinWidth(615);
        stage.setMaxHeight(435);
        stage.setMaxWidth(615);

        List<String> validExtensions = Arrays.asList("txt", "xml", "json", "zip");

        final Text dropHereText = new Text(95, 55, "Drop your file here:");
        dropHereText.setFill(Color.DARKCYAN);
        dropHereText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        dropHereText.setScaleX(2.0);
        dropHereText.setScaleY(2.0);

        final Text foutInfoText = new Text(175, 30, "Your output file information:");
        foutInfoText.setFill(Color.DARKCYAN);
        foutInfoText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        foutInfoText.setScaleX(2.0);
        foutInfoText.setScaleY(2.0);

        final Text foutExtensionText = new Text(60, 75, "Chose the extension of your output file:");
        foutExtensionText.setFill(Color.DARKCYAN);
        foutExtensionText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        foutExtensionText.setScaleX(1.25);
        foutExtensionText.setScaleY(1.25);

        final Text endText = new Text(200, 150, "Success!\nCheck out your new file!");
        endText.setFill(Color.DARKCYAN);
        endText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        endText.setTextAlignment(TextAlignment.CENTER);
        endText.setScaleX(2.0);
        endText.setScaleY(2.0);

        final Label validationLabel = new Label("Invalid file format!\nCheck it out!");
        validationLabel.setTextFill(Color.RED);
        validationLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        validationLabel.setTextAlignment(TextAlignment.CENTER);
        validationLabel.setLayoutX(95);
        validationLabel.setLayoutY(200);
        validationLabel.setVisible(false);

        Rectangle rectangle = new Rectangle(20, 100, 300, 250);
        rectangle.setFill(Color.MEDIUMAQUAMARINE);

        ImageView imageView = new ImageView(new Image("tick.png", 70, 70, true, true));
        imageView.setLayoutX(130);
        imageView.setLayoutY(185);
        imageView.setVisible(false);

        CheckBox isFinEncodedCheckBox = new CheckBox("Is your data\nencoded?");
        isFinEncodedCheckBox.setScaleX(2.0);
        isFinEncodedCheckBox.setScaleY(2.0);
        isFinEncodedCheckBox.setLayoutX(420);
        isFinEncodedCheckBox.setLayoutY(170);
        isFinEncodedCheckBox.setStyle("-fx-border-color: darkcyan; " + "-fx-font-weight: bold;" + "-fx-text-fill: darkcyan;" + "-fx-border-insets: -5; " + "-fx-border-radius: 5;" + "-fx-border-style: dotted;" + "-fx-border-width: 2;");

        CheckBox isFoutEncodedCheckBox = new CheckBox("Do you want your data to be encoded?");
        isFoutEncodedCheckBox.setScaleX(1.5);
        isFoutEncodedCheckBox.setScaleY(1.5);
        isFoutEncodedCheckBox.setLayoutX(85);
        isFoutEncodedCheckBox.setLayoutY(220);
        isFoutEncodedCheckBox.setStyle("-fx-border-color: darkcyan; " + "-fx-font-weight: bold;" + "-fx-text-fill: darkcyan;" + "-fx-border-insets: -5; " + "-fx-border-radius: 5;" + "-fx-border-style: dotted;" + "-fx-border-width: 2;");

        CheckBox isFoutArchivedCheckBox = new CheckBox("Do you want your file to be archived?  ");
        isFoutArchivedCheckBox.setScaleX(1.5);
        isFoutArchivedCheckBox.setScaleY(1.5);
        isFoutArchivedCheckBox.setLayoutX(85);
        isFoutArchivedCheckBox.setLayoutY(270);
        isFoutArchivedCheckBox.setStyle("-fx-border-color: darkcyan; " + "-fx-font-weight: bold;" + "-fx-text-fill: darkcyan;" + "-fx-border-insets: -5; " + "-fx-border-radius: 5;" + "-fx-border-style: dotted;" + "-fx-border-width: 2;");

        ToggleGroup btnGroup = new ToggleGroup();

        RadioButton txtBtn = new RadioButton("Txt ");
        txtBtn.setToggleGroup(btnGroup);
        txtBtn.setUserData("txt");
        txtBtn.setScaleX(1.5);
        txtBtn.setScaleY(1.5);
        txtBtn.setStyle("-fx-text-fill: darkcyan;" + "-fx-font-weight: bold;");
        txtBtn.setLayoutX(35);
        txtBtn.setLayoutY(90);

        RadioButton xmlBtn = new RadioButton("Xml ");
        xmlBtn.setToggleGroup(btnGroup);
        xmlBtn.setUserData("xml");
        xmlBtn.setScaleX(1.5);
        xmlBtn.setScaleY(1.5);
        xmlBtn.setStyle("-fx-text-fill: darkcyan;" + "-fx-font-weight: bold;");
        xmlBtn.setLayoutX(35);
        xmlBtn.setLayoutY(120);

        RadioButton jsonBtn = new RadioButton("Json");
        jsonBtn.setToggleGroup(btnGroup);
        jsonBtn.setUserData("json");
        jsonBtn.setScaleX(1.5);
        jsonBtn.setScaleY(1.5);
        jsonBtn.setStyle("-fx-text-fill: darkcyan;" + "-fx-font-weight: bold;");
        jsonBtn.setLayoutX(35);
        jsonBtn.setLayoutY(150);

        Button nextButton = new Button("Next ->");
        nextButton.setLayoutX(530);
        nextButton.setLayoutY(360);

        Button processButton = new Button("Process ->");
        processButton.setLayoutX(510);
        processButton.setLayoutY(360);

        Group primaryRoot = new Group();
        primaryRoot.getChildren().addAll(dropHereText, rectangle, isFinEncodedCheckBox, imageView, nextButton, validationLabel);
        Scene primaryScene = new Scene(primaryRoot, 600, 400, Color.LIGHTGREEN);

        Group afterReceivedRoot = new Group();
        afterReceivedRoot.getChildren().addAll(foutInfoText, foutExtensionText, processButton, txtBtn, xmlBtn, jsonBtn, isFoutEncodedCheckBox, isFoutArchivedCheckBox);
        Scene afterReceivedScene = new Scene(afterReceivedRoot, 600, 400, Color.LIGHTGREEN);

        Group endRoot = new Group();
        endRoot.getChildren().add(endText);
        Scene endScene = new Scene(endRoot, 600, 400, Color.LIGHTGREEN);


        isFinEncodedCheckBox.setOnAction(event -> {
            if (isFinEncodedCheckBox.isSelected())
                setInputFileEncoded(true);
            else
                setInputFileEncoded(false);
        });

        isFoutEncodedCheckBox.setOnAction(event -> {
            if (isFoutEncodedCheckBox.isSelected())
                setOutputFileEncoded(true);
            else
                setOutputFileEncoded(false);
        });

        isFoutArchivedCheckBox.setOnAction(event -> {
            if (isFoutArchivedCheckBox.isSelected())
                setOutputFileArchived(true);
            else
                setOutputFileArchived(false);
        });

        btnGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (btnGroup.getSelectedToggle() != null)
                setOutputFileExtension(btnGroup.getSelectedToggle().getUserData().toString());
        });

        nextButton.setOnAction(event -> {
            if(!getFilename().equals(""))
                stage.setScene(afterReceivedScene);
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("You haven't dropped your file!");
                alert.showAndWait();
            }
        });

        processButton.setOnAction(event -> {
            if(!getOutputFileExtension().equals("")){
                processingUnit(getFilename(), getIsInputFileEncoded(), getOutputFileExtension(),getIsOutputFileEncoded(), getIsOutputFileArchived());
                stage.setScene(endScene);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("You haven't entered output file information!");
                alert.showAndWait();
            }
        });

        rectangle.setOnDragOver(event -> {
            if (event.getGestureSource() != rectangle &&
                    event.getDragboard().hasFiles() &&
                    validExtensions.contains(getExtension(event.getDragboard().getFiles().toString()))) {
                event.acceptTransferModes(TransferMode.COPY);
            }

            event.consume();
        });

        rectangle.setOnDragEntered(event -> {
            if (event.getGestureSource() != rectangle &&
                    event.getDragboard().hasFiles() &&
                    validExtensions.contains(getExtension(event.getDragboard().getFiles().toString()))) {
                rectangle.setStroke(Color.LIMEGREEN);
                rectangle.setStrokeWidth(10.0);
            }
            else
                validationLabel.setVisible(true);

            event.consume();
        });

        rectangle.setOnDragExited(event -> {
            rectangle.setStroke(Color.MEDIUMAQUAMARINE);
            validationLabel.setVisible(false);
            event.consume();
        });

        rectangle.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles() &&
                    validExtensions.contains(getExtension(db.getFiles().toString()))) {
                setFilename(db.getFiles().toString().substring(1, db.getFiles().toString().length() - 1));
                imageView.setVisible(true);
                rectangle.setVisible(false);
                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });

        stage.setScene(primaryScene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}