/*Assignment 5
 * By: Adam Wilkinson
 * netID: 15ajw2
 * Due: Friday April 6th, 2018
 * Description: the purpose of this class is to create the functionality of the GUI. 
 */
package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

	private String sizeChoice = "Small";
	private String cheeseChoice = "Single";
	private String pepperoniChoice = "Single";
	private String mushroomChoice = "None";
	private int numPizzas;
	private ArrayList<LineItem> order = new ArrayList<LineItem>();
	private Pizza za;
	
	@FXML
	private RadioButton mushSingle;
	
	@FXML
	private RadioButton mushDouble;
	
	@FXML
	private RadioButton mushNone;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup size;

    @FXML
    private ToggleGroup cheese;

    @FXML
    private RadioButton NoPep;
    
    @FXML
    private ToggleGroup pepperoni;

    @FXML
    private ToggleGroup mushroom;

    @FXML
    private TextField numPizzaTextField;
    
    @FXML
    private TextArea orderText;

    @FXML
    private TextArea errorMessageText;
    
    @FXML
    private TextField NumDisplay;
    
    @FXML
    private TextField CostDisplay;
    
    @FXML 
    private TextField SinglePizzaCost;
    
    @FXML
    void initialize() {
        assert size != null : "fx:id=\"size\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
        assert cheese != null : "fx:id=\"cheese\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
        assert pepperoni != null : "fx:id=\"pepperoni\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
        assert mushroom != null : "fx:id=\"mushroom\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
        
        size.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	sizeChoice = ((RadioButton)new_toggle).getText(); //get the text beside the radio button when toggled
		});
        
        cheese.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	cheeseChoice = ((RadioButton)new_toggle).getText(); //get the text beside the radio button when toggled
		});
        
        pepperoni.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	pepperoniChoice = ((RadioButton)new_toggle).getText(); //get the text beside the radio button when toggled
		});
        
        mushroom.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	mushroomChoice = ((RadioButton)new_toggle).getText(); //get the text beside the radio button when toggled
		});
        
    }
    
    //method to disable the mushroom toggle group when no pepperoni is selected
    @FXML
    void mushroomsNone(ActionEvent event) throws IllegalPizza {
    		mushSingle.setDisable(true);
    		mushDouble.setDisable(true);
    		mushroom.selectToggle(mushNone);
    		currentPizzaCost(event);
    }
    
    //method to enable the mushroom options when pepperoni is selected
    @FXML
    void mushroomsOn(ActionEvent event) throws IllegalPizza {
    		mushSingle.setDisable(false);
		mushDouble.setDisable(false);
		currentPizzaCost(event);

	}
    
    //updates anytime a radiobutton is pressed and gives the cost of the current pizza.
    @FXML
    void currentPizzaCost(ActionEvent event) throws IllegalPizza {
    		za = new Pizza(sizeChoice, cheeseChoice, mushroomChoice, pepperoniChoice);
    		SinglePizzaCost.setText("$" + za.getCost());
    }
    
    //when the enter button is pressed it displays the number of pizzas and the cosst of the line item.
    @FXML
    void numPizzaEntered(ActionEvent event) throws IllegalPizza {
    		errorMessageText.clear();
    		try {
				numPizzas = Integer.parseInt(numPizzaTextField.getText());
			} catch (NumberFormatException e) {
				errorMessageText.setText("Invalid entry!");
				return;
			}
    		za = new Pizza(sizeChoice, cheeseChoice, mushroomChoice, pepperoniChoice);
    		LineItem item;
			try {
				item = new LineItem(numPizzas, za);
				if(numPizzas == 1)
	    				NumDisplay.setText(numPizzas + " Pizza Selected");
	    			else
	    				NumDisplay.setText(numPizzas + " Pizza's Selected");
	    			numPizzaTextField.clear();
				CostDisplay.setText("Total Cost: $" + item.getCost());
			} catch (Exception e) {
				errorMessageText.setText("Invalid entry!");
			}
    }
    
    //creates a lineitem and adds it the the arraylist of lineitems, then calls the next 
    //method to display the toString.
    @FXML
    void addToLineItem(ActionEvent event) throws IllegalPizza {
			try {
				za = new Pizza(sizeChoice, cheeseChoice, mushroomChoice, pepperoniChoice);
				LineItem item = new LineItem(numPizzas, za);
				errorMessageText.clear();
				order.add(item);
				printLine(order);
			} catch (IllegalPizza e) {
				String[] error = e.toString().split(":");
				errorMessageText.appendText(error[1] + '\n');
			}
    }
   
    //prints the order to the textfield
    void printLine(ArrayList<LineItem> list) {
    		int counter = 0;
    		double totalCost = 0;
    		orderText.clear();
    		orderText.setText("Pizza Order:\n");
    		for(LineItem item : list) {
    			counter++;
    			orderText.appendText(counter + ".	" + item.toString() +'\n');
    			totalCost += item.getCost();
    		}
    		orderText.appendText("Total Cost of Order: $" + Math.round(totalCost*100.0)/100.0);
    }
}

     

