package pl.komorowskidev.solutionsswing.businesslogic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;
import pl.komorowskidev.solutionsswing.businesslogic.problems.Problem;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Service
@Slf4j
public class SolutionService {

    private final Map<String, Problem> problemMap;

    public SolutionService() {
        problemMap = new TreeMap<>();
    }

    public void addProblem(Problem problem){
        problemMap.put(problem.getName(), problem);
    }

    public Set<String> getProblemNameSet() {
        return problemMap.keySet();
    }

    public Problem getProblem(String problemName){
        return problemMap.get(problemName);
    }

    public String getSolution(String problemName, String data){
        String result = "";
        Problem problem = problemMap.get(problemName);
        if(problem != null) {
            result = getSolution(problem, data);
        }
        return result;
    }

    private String getSolution(Problem problem, String data){
        String result;
        try {
            result = problem.getSolution(data);
        } catch (DataNotValidException e) {
            result = e.getMessage();
        }
        return result;
    }
}
