package repositories.implementations;

import dtos.SubjectDTO;
import org.junit.*;
import repositories.RepositoryBaseTest;
import repositories.interfaces.ISubjectRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public final class SubjectRepositoryTest extends RepositoryBaseTest<SubjectDTO, ISubjectRepository, SubjectRepository> {

    private SubjectDTO testSubject;

    public SubjectRepositoryTest()
            throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(SubjectRepository.class);
    }

    @Test
    public void add(){
        testSubject = new SubjectDTO(1, "Artificial Intelligence", "AI");
        super.add(testSubject);
    }

    @Test
    public void update(){
        testSubject = new SubjectDTO(1, "Multimedia", "MUL");
        super.update(testSubject);
    }

    @Test
    public void delete(){
        testSubject = new SubjectDTO(1, "Multimedia", "MUL");
        super.delete(testSubject);
    }

    @Test
    public void findById(){
        testSubject = new SubjectDTO(1, "Artificial Intelligence", "AI");
        super.findById(testSubject, testSubject.getId());
    }

    @Test
    public void findThreeCharactersAcronym(){
        List<SubjectDTO> allSubjects = new ArrayList<>();
        List<SubjectDTO> rightAnswersList = new ArrayList<>();
        allSubjects.add(new SubjectDTO(1, "Artificial Intelligence", "AI"));
        allSubjects.add(new SubjectDTO(2, "Multimedia", "MUL"));
        allSubjects.add(new SubjectDTO(3, "Accounting", "ACCT"));
        allSubjects.add(new SubjectDTO(4, "Java programming basics", "JPB"));
        for (SubjectDTO subject : allSubjects) {
            getRepository().add(subject);
            if (subject.getAcronym().length() == 3)
                rightAnswersList.add(subject);
        }
        List<SubjectDTO> resultList = getRepository().findThreeCharactersAcronym();
        for (SubjectDTO subject : allSubjects)
            getRepository().delete(subject);
        Assert.assertEquals(rightAnswersList, resultList);
    }
}