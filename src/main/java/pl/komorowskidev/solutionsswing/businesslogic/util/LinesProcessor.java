package pl.komorowskidev.solutionsswing.businesslogic.util;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinesProcessor {

    public List<String> createLines(String data) throws DataNotValidException {
        List<String> result = new ArrayList<>();
        if(data == null || data.isEmpty()) {
            throw new DataNotValidException("There is no data.");
        } else {
            String[] lines = data.trim().split("\n");
            for (String line : lines) {
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
        }
        if(result.isEmpty()){
            throw new DataNotValidException("There is no data");
        }
        return result;
    }

    public String createString(List<String> lines){
        return String.join("\n", lines);
    }

    public List<String> removeSpaces(List<String> lines) {
        List<String> result = new ArrayList<>();
        for(String line : lines){
            result.add(line.replaceAll(" ", ""));
        }
        return result;
    }

    public char[][] createCharArray(List<String> lines) {
        int rows = lines.size();
        int columns = lines.get(0).length();
        char[][] result = new char[rows][columns];
        for(int rowIndex = 0; rowIndex < rows; rowIndex++){
            result[rowIndex] = getRowChars(columns, lines.get(rowIndex));
        }
        return result;
    }

    private char[] getRowChars(int columns, String line) {
        char[] row = new char[columns];
        for(int columnIndex = 0; columnIndex < columns; columnIndex++){
            if(columnIndex < line.length()){
                row[columnIndex] = line.charAt(columnIndex);
            } else {
                row[columnIndex] = ' ';
            }
        }
        return row;
    }

    public int[][] createIntArray(List<String> lines, int columns, String delimiter) throws DataNotValidException {
        if(lines == null || lines.isEmpty()){
            throw new DataNotValidException("There is no data.");
        }
        if(delimiter == null || delimiter.isEmpty()){
            throw new DataNotValidException("There is no delimiter.");
        }
        int rows = lines.size();
        int[][] result = new int[rows][columns];
        for(int rowIndex = 0; rowIndex < rows; rowIndex++){
            result[rowIndex] = getRowInt(lines.get(rowIndex), columns, delimiter);
        }
        return result;
    }

    private int[] getRowInt(String line, int columns, String delimiter) throws DataNotValidException {
        int[] row = new int[columns];
        String[] elements = line.split(delimiter);
        if(elements.length == columns){
            int columnIndex = 0;
            while(columnIndex < columns && columnIndex < elements.length){
                try{
                    row[columnIndex] = Integer.parseInt(elements[columnIndex]);
                    columnIndex++;
                }catch (NumberFormatException e){
                    throw new DataNotValidException("Cannot parse to int: " + elements[columnIndex]);
                }
            }
        } else {
            throw new DataNotValidException("Error in line: " + line + "\n Should be " + columns + " columns");
        }

        return row;
    }

    public List<Double> createListOfDouble(String line, String delimiter) throws DataNotValidException{
        if(line == null || line.isEmpty()){
            throw new DataNotValidException("Line is empty");
        }
        String[] elements = line.split(delimiter);
        List<Double> result = new ArrayList<>();
        for(String element : elements){
            try{
                result.add(Double.parseDouble(element));
            } catch(NullPointerException | NumberFormatException e){
                throw new DataNotValidException("Not a number: " + element + " in line: " + line);
            }
        }
        return result;
    }
}
