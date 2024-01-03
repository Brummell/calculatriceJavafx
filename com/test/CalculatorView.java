package com.test;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CalculatorView extends GridPane {
    private CalculatorModel model;
    private TextField display;
    private List<Button> digitButtons;
    private Button clearButton;
    private Button equalButton;
    private Button plusButton;
    private Button minusButton;
    private Button multiplyButton;
    private Button divideButton;

    public CalculatorView(CalculatorModel model, Stage primaryStage) {
        this.model = model;
        createUI(primaryStage);
    }

    private void createUI(Stage primaryStage) {
        display = new TextField();
        display.setMinHeight(50);
        display.setMinWidth(400);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setEditable(false);
        display.setStyle("-fx-font-size: 20px;");
        add(display, 0, 0, 4, 1);

        // Initialize digit buttons list
        digitButtons = new ArrayList<>();
        int row = 1;
        int col = 0;

        for (int i = 1; i <= 9; i++) {
            Button digitButton = createDigitButton(Integer.toString(i));
            digitButton.setStyle("-fx-font-size: 20px;");
            digitButtons.add(digitButton);
            add(digitButton, col, row);

            col = (col + 1) % 3;
            if (col == 0) {
                row++;
            }
        }

       
        Button zeroButton = createDigitButton("0");
        zeroButton.setStyle("-fx-font-size: 20px;");
        digitButtons.add(zeroButton);
        add(zeroButton, 0, 4);

        
        clearButton = createButton("C", Color.DEEPSKYBLUE);
        clearButton.setStyle("-fx-font-size: 20px;");
        clearButton.setOnAction(e -> {
        	model.clear();
        	updateDisplay();
        });
        add(clearButton, 1, 4, 2, 1);

        
        equalButton = createButton("=", Color.INDIANRED);
        equalButton.setStyle("-fx-font-size: 20px;");
        add(equalButton, 2, 4);

       
        plusButton = createButton("+", null);
        plusButton.setStyle("-fx-font-size: 20px;");
        plusButton.setOnAction(e -> {
        	model.appendOperator("+");
            updateDisplay();
        });
        add(plusButton, 3, 1);

        minusButton = createButton("-", null);
        minusButton.setStyle("-fx-font-size: 20px;");
        minusButton.setOnAction(e -> {
        	model.appendOperator("-");
            updateDisplay();
        });
        add(minusButton, 3, 2);

        multiplyButton = createButton("*", null);
        multiplyButton.setStyle("-fx-font-size: 20px;");
        multiplyButton.setOnAction(e -> {
        	model.appendOperator("*");
            updateDisplay();
        });
        add(multiplyButton, 3, 3);

        divideButton = createButton("/", null);
        divideButton.setStyle("-fx-font-size: 20px;");
        divideButton.setOnAction(e -> {
        	model.appendOperator("/");
            updateDisplay();
        });
        add(divideButton, 3, 4);

        primaryStage.setWidth(416);
        primaryStage.setHeight(489);
        primaryStage.setResizable(false);
    }

    public TextField getDisplay() {
        return display;
    }

    public List<Button> getDigitButtons() {
        return digitButtons;
    }

    public Button getClearButton() {
        return clearButton;
    }
    
 
    public void updateDisplay() {
    	this.getDisplay().setText(model.getCurrentInput());
    }

    public Button getEqualButton() {
        return equalButton;
    }

    public Button getPlusButton() {
        return plusButton;
    }

    public Button getMinusButton() {
        return minusButton;
    }

    public Button getMultiplyButton() {
        return multiplyButton;
    }

    public Button getDivideButton() {
        return divideButton;
    }

    private Button createDigitButton(String label) {
        Button digitButton = createButton(label, null);
        digitButton.setOnAction(e -> model.appendDigit(Integer.parseInt(label)));
        return digitButton;
    }

    private Button createButton(String label, Color backgroundColor) {
        Button button = new Button(label);
        button.setMinSize(100, 100);
        if (backgroundColor != null) {
            button.setBackground(Background.fill(backgroundColor));
        }
        return button;
    }
}
