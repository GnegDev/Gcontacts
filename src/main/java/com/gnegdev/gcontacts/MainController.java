package com.gnegdev.gcontacts;

import com.gnegdev.gcontacts.utils.Validator;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainController {
    @FXML
    private VBox contactsBox;
    @FXML
    private TextField searchField;
    @FXML
    private TextField phoneInput;
    @FXML
    private TextField nameInput;

    @FXML
    protected void onNewContactButtonClick() {
        String phone = phoneInput.getText();
        if(phone.isEmpty() || !Validator.isPhoneNumberValid(phone)) {
            phoneInput.requestFocus();
            return;
        }
        String name = nameInput.getText();
        if(name.isEmpty()) {
            nameInput.requestFocus();
            return;
        }

        BorderPane newContact = new BorderPane();

        VBox contactInfo = new VBox();
        contactInfo.setSpacing(5);

        HBox nameSlot = new HBox();
        nameSlot.setSpacing(5);
        nameSlot.setAlignment(Pos.CENTER);
        HBox phoneSlot = new HBox();
        phoneSlot.setSpacing(5);
        phoneSlot.setAlignment(Pos.CENTER);

        newContact.setPadding(new Insets(5, 5, 5, 5));
        newContact.setBorder(new Border(new BorderStroke(
                Color.GRAY,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Button deleteButton = new Button("X");
        deleteButton.setOnAction(event -> {
            contactsBox.getChildren().remove(newContact);
        });

        TextField phoneField = new TextField(phone);
        TextField nameField = new TextField(name);
        phoneField.setEditable(false);
        nameField.setEditable(false);

        nameSlot.getChildren().addAll(new Label("☺"), nameField);
        phoneSlot.getChildren().addAll(new Label("☎"), phoneField);
        contactInfo.getChildren().addAll(nameSlot, phoneSlot);
        newContact.setLeft(contactInfo);
        newContact.setRight(deleteButton);

        contactsBox.getChildren().add(newContact);
    }

    @FXML
    protected void onSearchEntered() {
        if(searchField.getText().isEmpty()) {
            for(Node node : contactsBox.getChildren()) {
                node.setVisible(true);
                node.setManaged(true);
            }
            return;
        }
        for(Node node : contactsBox.getChildren()) {
            BorderPane contactBox = (BorderPane) node;
            VBox contactInfo = (VBox) contactBox.getLeft();

            HBox nameSlot = (HBox) contactInfo.getChildren().get(0);
            HBox phoneSlot = (HBox) contactInfo.getChildren().get(1);

            TextField nameField = (TextField) nameSlot.getChildren().get(1);
            TextField phoneField = (TextField) phoneSlot.getChildren().get(1);

            if(!nameField.getText().contains(searchField.getText()) && !phoneField.getText().contains(searchField.getText())) {
                node.setVisible(false);
                node.setManaged(false);
            } else {
                node.setVisible(true);
                node.setManaged(true);
            }
        }
    }

    @FXML
    protected void showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gcontacts");
        alert.setHeaderText("Gcontacts v1.0.0");
        alert.setContentText("By Ivan Valtuille\n\n2024 GnegDev");

        alert.showAndWait();
    }
}