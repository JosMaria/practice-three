package org.genesiscode.practicethree.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.genesiscode.practicethree.view.Row;

import java.util.ArrayList;
import java.util.List;

public class Additive {

    private List<Integer> sequence = new ArrayList<>();
    private int countOfNumberToGenerate;
    private int module;
    private String messageToNotification;
    private ObservableList<Row> rows = FXCollections.observableArrayList();

    public void addNumber(Integer number) {
        sequence.add(number);
    }

    public void clear() {
        sequence.clear();
    }

    public void setCountOfNumberToGenerate(int countOfNumberToGenerate) throws IllegalArgumentException {
        if (countOfNumberToGenerate <= 0) {
            messageToNotification = "La cantidad de numeros generados tiene que ser mayor que 0";
            throw new IllegalArgumentException(messageToNotification);
        }
        this.countOfNumberToGenerate = countOfNumberToGenerate;
    }

    public void setModule(int module) throws IllegalArgumentException {
        if (module <= 0) {
            messageToNotification = "El modulo tiene que ser mayor que 0";
            throw new IllegalArgumentException(messageToNotification);
        }
        this.module = module;
    }

    public ObservableList<Row> getAllRows() {
        rows.clear();
        int n = sequence.size();
        for (int i = 0; i < countOfNumberToGenerate; i++) {
            rows.add(getRow(n));
        }
        sequence.clear();
        return rows;
    }

    public Row getRow(int n) {
        int sizeSequence = sequence.size();
        int var1 = sizeSequence - 1;
        int var2 = sizeSequence - n;

        int columnOne = sequence.get(var1);
        int columnTwo = sequence.get(var2);
        int columnThree = columnOne + columnTwo;
        int columnFour = columnThree % module;
        Row row = new Row(sizeSequence + 1, String.valueOf(columnOne), String.valueOf(columnTwo),
                String.valueOf(columnThree), String.valueOf(columnFour), String.format("%s/%s", columnFour, module - 1));
        sequence.add(columnFour);
        return row;
    }
}
