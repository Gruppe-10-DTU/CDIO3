package ui;

import controllers.FieldController;
import controllers.GUIConverter;
import controllers.PlayerController;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import models.Player;
import models.fields.Field;
import models.fields.Property;

import java.util.ArrayList;
import java.util.Arrays;

public class GUIController {
    private GUI gui;
    private GUI_Player[] gui_players;
    public GUIController(){
    }

    /**
     * Create a gui with custom fields
     * @param fieldList List of fields
     */
    public GUIController(ArrayList<Field> fieldList){
        gui = new GUI(GUIConverter.fieldListToGUI(fieldList));

    }

    /**
     * Ask the user the amount of players in this game session. Min 2, max 4
     * @param playerAmountText Text to ask players how many are player
     * @return PlayerAmount :  How many players are going to play the game
     */
    public int playerAmount(String playerAmountText){
        return gui.getUserInteger(playerAmountText,2, 4);
    }


    /**
     * @param selectCharacterText Text to display asking user to choose their character
     * @param choices Character choices available to them
     * @return The character in string format
     */
    public String selectCharacter(String selectCharacterText, String choices){
        return gui.getUserSelection(selectCharacterText, choices.split(","));
    }

    /**
     * Insert the players into the GUI
     * @param players Players in the game
     */
    public void setPlayers(Player[] players){
        gui_players = GUIConverter.playerToGUI(players);
        for (GUI_Player player : gui_players) {
            gui.addPlayer(player);
        }
    }

    /**
     * Display a message and get the okay from the player
     * @param msg Message to display
     */
    public void displayMsg(String msg){
        gui.getUserButtonPressed(msg, "ok");
    }
    public String getName(String getNameText){
        return gui.getUserString(getNameText);
    }

    /**
     * Update a player on the board
     * @param player Player to be updated
     */
    public void updatePlayer(Player player){
        gui_players[player.getID()].setBalance(player.getBalance());
        gui_players[player.getID()].getCar().setPosition(gui.getFields()[player.getLocation()]);
    }

    /**
     * Update the player to move to a new field
     * @param player Player to be moved
     */
    public void movePlayer(Player player){
        gui_players[player.getID()].getCar().setPosition(gui.getFields()[player.getLocation()]);
    }
    


    /**
     * @param rolls Array of ints. Can show up to two dice
     */
    public void displayDice(int[] rolls) {
        if(rolls.length == 1){
            gui.setDice(rolls[0],0);
        }else{
            gui.setDice(rolls[0],rolls[1]);
        }
    }

    /**
     * Set the owner on the property
     * @param property Property to be changed
     */
    public void updateField(Property property){
        GUI_Street street = (GUI_Street) gui.getFields()[property.getID()];
   //     street.setOwnableLabel(property.getOwner().getIdentifier());
    //    street.setOwnerName(property.getOwner().getIdentifier());
        street.setHouses(1);
        street.setSubText(property.getOwner().getIdentifier());
    }

    public void displayMsgNoBtn(String msg){
        gui.showMessage(msg);
    }

    public void showChanceCard(String message){
        gui.displayChanceCard(message);
    }
    public String showChanceCardChoice(String message, String option1, String option2){
        return gui.getUserSelection(message, option1,option2);
    }
    public int getXStepsToMove(String message, int MinVal, int MaxVal){
        return gui.getUserInteger(message,MinVal,MaxVal);
    }

    public void endGame(){gui.close();}

    public void getRoll(String rollText, String rollButton) {
        gui.getUserButtonPressed(rollText, rollButton);
    }

    /**
     * @param emtpyFieldChoice Text to display
     * @param properties The choices you have
     */
    public int getPropertyChoice(String emtpyFieldChoice, Property[] properties) {
        String choice = gui.getUserSelection(emtpyFieldChoice, Arrays.stream(properties).map(Field::getName).toArray(String[]::new));
        return Arrays.stream(properties).filter(field->field.getName()==choice).findFirst().get().getID();
    }
    public void showRoll(int roll){
        gui.setDie(roll);
    }

    /**
     * Iterates thrhrough every player and every field and updates the gui
     * @param playerController provides the players
     * @param fieldController provides the fields
     */
    public void updateBoard(PlayerController playerController, FieldController fieldController){
        for (Player player : playerController.getPlayers()) {
            updatePlayer(player);
        }
        for (Field field: fieldController.getFieldList()) {
            if ( field instanceof Property && ((Property) field).getOwner() != null){
                updateField((Property) field);
            }
        }
    }

}


