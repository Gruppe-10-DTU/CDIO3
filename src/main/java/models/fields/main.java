package models.fields;

import controllers.CSVReader;
import controllers.FieldController;

import java.util.ArrayList;

public class main {
    public static void main (String[] args) throws Exception {

        ArrayList<ArrayList<String>> fieldList;

        FieldController fieldController = new FieldController();

        String path = System.getProperty("user.dir") + "/src/main/java/models/Fields.csv";

        CSVReader csvReader = new CSVReader(path,",",true);
         fieldList = csvReader.getDataAsArrList();

         ArrayList<Object> gameFields = fieldController.genarateGameFields(fieldList);


    };

}