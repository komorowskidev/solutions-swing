package pl.komorowskidev.solutionsswing.businesslogic.problems.p40pathsinthegrid;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class PathsInTheGridValidator {

    public void validate(List<String> lines) throws DataNotValidException {
        int lineListSize = lines.size();
        int lastLineIndex = lineListSize - 1;
        if(lineListSize < 2){
            throw new DataNotValidException("At least 2 lines of data required.");
        }
        Set<Integer> columnNumberSet = new HashSet<>();
        for(String line : lines){
            columnNumberSet.add(line.length());
        }
        if(columnNumberSet.size() > 1){
            throw new DataNotValidException("Lines should have the same number of elements");
        }
        if(columnNumberSet.iterator().next() < 2){
            throw new DataNotValidException("At least 2 columns of data required.");
        }
        Pattern firstElementPattern = Pattern.compile("^@.*");
        if(!firstElementPattern.matcher(lines.get(0)).matches()){
            throw new DataNotValidException("first element should be @");
        }
        Pattern lastElementPattern = Pattern.compile(".*\\$$");
        if(!lastElementPattern.matcher(lines.get(lastLineIndex)).matches()){
            throw new DataNotValidException("last element should be $");
        }
        Pattern firstLinePattern = Pattern.compile("@[+X]*");
        if(!firstLinePattern.matcher(lines.get(0)).matches()){
            throw new DataNotValidException("only X and + allowed: " + lines.get(0));
        }
        if(lineListSize > 2){
            Pattern middleLinePattern = Pattern.compile("[+X]*");
            for(int i = 1; i < lines.size() - 1; i++){
                if(!middleLinePattern.matcher(lines.get(i)).matches()){
                    throw new DataNotValidException("only X and + allowed: " + lines.get(i));
                }
            }
        }
        Pattern lastLinePattern = Pattern.compile("[+X]*\\$");
        if(!lastLinePattern.matcher(lines.get(lastLineIndex)).matches()){
            throw new DataNotValidException("only X and + allowed: " + lines.get(lastLineIndex));
        }
    }
}
