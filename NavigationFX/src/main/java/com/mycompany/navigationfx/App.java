package com.mycompany.navigationfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    Vehicle vehicle;
    
    final static String LOCATIONLBL_PREFIX = "Currently at ";
    final static String CURRENTFUEL_PREFIX = "The vehicle currently has ";
    final static String CURRENTFUEL_SUFFIX = " gallons of fuel remaining.";   

    @Override
    public void start(Stage stage) {
        AnchorPane vehiclePane = new AnchorPane();
        Scene vehicleCreation = new Scene(vehiclePane, 800, 600);
        vehicleCreation.getStylesheets().add("style.css");
        
        AnchorPane navPane = new AnchorPane();
        Scene navigation = new Scene(navPane, 800, 600);
        navigation.getStylesheets().add("style.css");
        
        AnchorPane locationPane = new AnchorPane();
        Scene locationAddition = new Scene(locationPane, 800, 600);
        locationAddition.getStylesheets().add("style.css");
        
        //build the naviagtion scene
        ListView<Location> locationsLV = new ListView();
        locationsLV.getItems().add(new Location("Cleveland", 0, 0, true));
        locationsLV.getItems().add(new Location("North Pole", 500, -100, true));
        locationsLV.getItems().add(new Location("Campbell Hill", -50, -50, false));
        locationsLV.getItems().add(new Location("Tokyo", -100, 1000, true));
        locationsLV.getItems().add(new Location("Chicago", -200, 75, true));
        locationsLV.getItems().add(new Location("Erie, PA", 150, 0, true));
        locationsLV.getItems().add(new Location("Allegheny National Forest", 300, -75, false));
        
        Label currentLocationLbl = new Label("");
        Label currentFuelLbl = new Label("");
        Label statusLbl = new Label("");
        Label locationInfoLbl = new Label("");
        
        Button infoBtn = new Button("Location info");
        infoBtn.setOnAction(ActionEvent -> {
            locationInfoLbl.setText(locationsLV.getSelectionModel().getSelectedItem().getInfo());
        });
        
        Button refuelBtn = new Button("Refuel");
        refuelBtn.setOnAction(ActionEvent -> {
            vehicle.refuel();
            currentFuelLbl.setText(String.format(CURRENTFUEL_PREFIX + "%f" + CURRENTFUEL_SUFFIX, vehicle.getCurrentFuel()));
        });
        
        refuelBtn.setTooltip(new Tooltip("adfasf"));
        
        Button removeBtn = new Button("Remove Location");
        removeBtn.setOnAction(ActionEvent -> {
            locationsLV.getItems().remove(locationsLV.getSelectionModel().getSelectedItem());
        });
        
        Button addBtn = new Button("Add Location");
        addBtn.setOnAction(ActionEvent -> {
            stage.setTitle("Adding Location");
            stage.setScene(locationAddition);
        });
        
        Button travelBtn = new Button("Travel to Location");
        travelBtn.setOnAction(ActionEvent -> {
            Vehicle.travelOutcome tOutcome = vehicle.travel(locationsLV.getSelectionModel().getSelectedItem());
            
            if (tOutcome == Vehicle.travelOutcome.alreadyThere){
                statusLbl.setText("You're already there!");
            } else if (tOutcome == Vehicle.travelOutcome.notEnoughFuel){
                statusLbl.setText("Not enough fuel to travel there.");
            } else{
                statusLbl.setText("Successfuly traveled.");
            }
            
            currentLocationLbl.setText(LOCATIONLBL_PREFIX + vehicle.getLocation() + ".");
            currentFuelLbl.setText(String.format(CURRENTFUEL_PREFIX + "%f" + CURRENTFUEL_SUFFIX, vehicle.getCurrentFuel()));
            refuelBtn.setDisable(!vehicle.getLocation().hasGas());
        });
        
        AnchorPane.setTopAnchor(currentLocationLbl, 20.0);
        AnchorPane.setLeftAnchor(currentLocationLbl, 20.0);
        
        AnchorPane.setTopAnchor(currentFuelLbl, 50.0);
        AnchorPane.setLeftAnchor(currentFuelLbl, 20.0);
        
        AnchorPane.setTopAnchor(locationInfoLbl, 80.0);
        AnchorPane.setLeftAnchor(locationInfoLbl, 20.0);
        
        AnchorPane.setTopAnchor(statusLbl, 110.0);
        AnchorPane.setLeftAnchor(statusLbl, 20.0);
        
        AnchorPane.setTopAnchor(locationsLV, 25.0);
        AnchorPane.setBottomAnchor(locationsLV, 75.0);
        AnchorPane.setRightAnchor(locationsLV, 20.0);
        
        AnchorPane.setBottomAnchor(infoBtn, 140.0);
        AnchorPane.setRightAnchor(infoBtn, 280.0);
        
        AnchorPane.setBottomAnchor(refuelBtn, 80.0);
        AnchorPane.setRightAnchor(refuelBtn, 315.0);
        
        AnchorPane.setBottomAnchor(addBtn, 20.0);
        AnchorPane.setRightAnchor(addBtn, 280.0);
        
        AnchorPane.setBottomAnchor(removeBtn, 20.0);
        AnchorPane.setRightAnchor(removeBtn, 150.0);
        
        AnchorPane.setBottomAnchor(travelBtn, 20.0);
        AnchorPane.setRightAnchor(travelBtn, 20.0);
        
        navPane.getChildren().addAll(currentLocationLbl, currentFuelLbl, statusLbl, locationInfoLbl, infoBtn, refuelBtn, removeBtn, addBtn, travelBtn, locationsLV);
        
        //build the vehicle creation scene
        Label infoLbl = new Label("Enter vehicle parameters:");
        Label maxFuelLbl = new Label("Maximum fuel in gallons:");
        Label gasMilageLbl = new Label("Gas milage:");
        Label vehErrLbl = new Label("");
        
        TextField maxFuelTF = new TextField();
        TextField gasMilageTF = new TextField();
        
        Button createVehicle = new Button("Create vehicle");
        createVehicle.setOnAction(ActionEvent ->{
            try{
                double maxFuel = Double.parseDouble(maxFuelTF.getText());
                double gasMilage = Double.parseDouble(maxFuelTF.getText());
                
                vehicle = new Vehicle(locationsLV.getItems().get(0), maxFuel, gasMilage);
                
                if (maxFuel <= 0 || gasMilage <= 0){
                    throw new NumberFormatException();
                }
                
                currentLocationLbl.setText(LOCATIONLBL_PREFIX + vehicle.getLocation() + ".");
                currentFuelLbl.setText(String.format(CURRENTFUEL_PREFIX + "%f" + CURRENTFUEL_SUFFIX, vehicle.getCurrentFuel()));
                stage.setTitle("Navigation");
                stage.setScene(navigation);
            }
            catch(NumberFormatException e){
                vehErrLbl.setText("Please input positive numbers for the fields!");
            }
        });
        
        AnchorPane.setTopAnchor(infoLbl, 20.0);
        AnchorPane.setLeftAnchor(infoLbl, 20.0);
        
        AnchorPane.setTopAnchor(maxFuelLbl, 60.0);
        AnchorPane.setLeftAnchor(maxFuelLbl, 20.0);
        
        AnchorPane.setTopAnchor(gasMilageLbl, 100.0);
        AnchorPane.setLeftAnchor(gasMilageLbl, 20.0);
        
        AnchorPane.setBottomAnchor(vehErrLbl, 100.0);
        AnchorPane.setLeftAnchor(vehErrLbl, 20.0);

        AnchorPane.setTopAnchor(maxFuelTF, 60.0);
        AnchorPane.setLeftAnchor(maxFuelTF, 160.0);
        
        AnchorPane.setTopAnchor(gasMilageTF, 100.0);
        AnchorPane.setLeftAnchor(gasMilageTF, 160.0);
        
        AnchorPane.setBottomAnchor(createVehicle, 50.0);
        AnchorPane.setRightAnchor(createVehicle, 20.0);
        
        vehiclePane.getChildren().addAll(infoLbl, maxFuelLbl, gasMilageLbl, vehErrLbl, maxFuelTF, gasMilageTF, createVehicle);
        
        //build the location addition scene
        Label nameLbl = new Label("Location Name:");
        Label xLbl = new Label("Location x-coordinate:");
        Label yLbl = new Label("Location y-coordinate:");
        Label locAddErrLbl = new Label("");
        
        TextField nameTF = new TextField();
        TextField xTF = new TextField();
        TextField yTF = new TextField();
        
        RadioButton hasGasRB = new RadioButton("Has Gas");
        RadioButton noGasRB = new RadioButton("Does Not Have Gas");
        
        ToggleGroup hasGasGroup = new ToggleGroup();
        
        hasGasRB.setToggleGroup(hasGasGroup);
        noGasRB.setToggleGroup(hasGasGroup);
        
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(ActionEvent -> {
            stage.setTitle("Navigation");
            stage.setScene(navigation);
        });
        
        Button createLocBtn = new Button("Add Location");
        createLocBtn.setOnAction(ActionEvent -> {
            try{
                boolean hasGas;
            
                RadioButton gasSelectionButton = (RadioButton) hasGasGroup.getSelectedToggle();
                String gasSelectionText = gasSelectionButton.getText();
            
                hasGas = gasSelectionText.equals("Has Gas");
                
                locationsLV.getItems().add(new Location(nameTF.getText(), Double.parseDouble(xTF.getText()), Double.parseDouble(yTF.getText()), hasGas));
                nameTF.clear();
                xTF.clear();
                yTF.clear();
                hasGasRB.setSelected(false);
                noGasRB.setSelected(false);
                stage.setTitle("Navigation");
                stage.setScene(navigation);
            }
            catch(NumberFormatException e){
                locAddErrLbl.setText("Please input numbers for the coordinates!");
            }
            catch(NullPointerException e){
                locAddErrLbl.setText("Please choose whether or not the location has gas!");
            }
        });
        
        AnchorPane.setTopAnchor(nameLbl, 20.0);
        AnchorPane.setLeftAnchor(nameLbl, 20.0);
        
        AnchorPane.setTopAnchor(xLbl, 60.0);
        AnchorPane.setLeftAnchor(xLbl, 20.0);
        
        AnchorPane.setTopAnchor(yLbl, 100.0);
        AnchorPane.setLeftAnchor(yLbl, 20.0);
        
        AnchorPane.setBottomAnchor(locAddErrLbl, 100.0);
        AnchorPane.setLeftAnchor(locAddErrLbl, 20.0);
        
        AnchorPane.setTopAnchor(nameTF, 20.0);
        AnchorPane.setLeftAnchor(nameTF, 150.0);
        
        AnchorPane.setTopAnchor(xTF, 60.0);
        AnchorPane.setLeftAnchor(xTF, 150.0);
        
        AnchorPane.setTopAnchor(yTF, 100.0);
        AnchorPane.setLeftAnchor(yTF, 150.0);
        
        AnchorPane.setTopAnchor(hasGasRB, 140.0);
        AnchorPane.setLeftAnchor(hasGasRB, 150.0);
        
        AnchorPane.setTopAnchor(noGasRB, 140.0);
        AnchorPane.setLeftAnchor(noGasRB, 230.0);
        
        AnchorPane.setBottomAnchor(cancelBtn, 50.0);
        AnchorPane.setRightAnchor(cancelBtn, 20.0);
        
        AnchorPane.setBottomAnchor(createLocBtn, 50.0);
        AnchorPane.setRightAnchor(createLocBtn, 120.0);
        
        locationPane.getChildren().addAll(nameLbl, xLbl, yLbl, locAddErrLbl, nameTF, xTF, yTF, hasGasRB, noGasRB, cancelBtn, createLocBtn);
        
        //program start
        stage.setTitle("Creating Vehicle");
        stage.setScene(vehicleCreation);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}