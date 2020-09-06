package pl.komorowskidev.solutionsswing.businesslogic.problems;


import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;

public interface Problem {

    String getName();

    String getDescription();

    String getExampleData();

    String getSolution(String data) throws DataNotValidException;

}
