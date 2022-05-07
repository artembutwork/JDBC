package repositories.implementations;

import dtos.GradeDTO;
import org.junit.*;
import repositories.RepositoryBaseTest;
import repositories.interfaces.IGradeRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public final class GradeRepositoryTest extends RepositoryBaseTest<GradeDTO, IGradeRepository, GradeRepository> {

    private GradeDTO testGrade;

    public GradeRepositoryTest()
            throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(GradeRepository.class);
    }

    @Test
    public void add(){
        testGrade = new GradeDTO(1, 5, "five");
        super.add(testGrade);
    }

    @Test
    public void update(){
        testGrade = new GradeDTO(1, 4, "four");
        super.update(testGrade);
    }

    @Test
    public void delete(){
        testGrade = new GradeDTO(1, 4, "four");
        super.delete(testGrade);
    }

    @Test
    public void findById(){
        testGrade = new GradeDTO(1, 5, "five");
        super.findById(testGrade, testGrade.getId());
    }

    @Test
    public void findGreaterThen(){
        int gradeValue = 3;
        List<GradeDTO> allGrades = new ArrayList<>();
        List<GradeDTO> rightAnswersList = new ArrayList<>();
        allGrades.add(new GradeDTO(1, 2, "two"));
        allGrades.add(new GradeDTO(2, 3, "three"));
        allGrades.add(new GradeDTO(3, 4, "four"));
        allGrades.add(new GradeDTO(4, 5, "five"));
        for (GradeDTO grade : allGrades) {
            getRepository().add(grade);
            if (grade.getValue() > gradeValue)
                rightAnswersList.add(grade);
        }
        List<GradeDTO> resultList = getRepository().findGreaterThen(gradeValue);
        for (GradeDTO grade : allGrades)
            getRepository().delete(grade);
        Assert.assertEquals(rightAnswersList, resultList);
    }
}
