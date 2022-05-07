package repositories.implementations;

import dtos.*;
import org.junit.*;
import repositories.RepositoryBaseTest;
import repositories.interfaces.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

public final class TeacherRepositoryTest extends RepositoryBaseTest<TeacherDTO, ITeacherRepository, TeacherRepository> {

    private final IRepository<PersonDTO> peopleRepository;
    private TeacherDTO testTeacher;

    public TeacherRepositoryTest()
            throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(TeacherRepository.class);
        peopleRepository = new PersonRepository(getRepository().getConnection());
    }

    @Test
    public void add(){
        testTeacher = new TeacherDTO(1, "Professor");
        addPersonIfAbsent(testTeacher.getId());
        super.add(testTeacher);
    }

    @Test
    public void update(){
        testTeacher = new TeacherDTO(1, "Associate Professor");
        super.update(testTeacher);
    }

    @Test
    public void delete(){
        testTeacher = new TeacherDTO(1, "Associate Professor");
        super.delete(testTeacher);
        deleteAddedPerson(testTeacher.getId());
    }

    @Test
    public void findById(){
        TeacherDTO teacher = new TeacherDTO(1, "Professor");
        addPersonIfAbsent(teacher.getId());
        super.findById(teacher, teacher.getId());
        deleteAddedPerson(teacher.getId());
    }

    @Test
    public void findWithoutDegree(){
        List<TeacherDTO> teachers = new ArrayList<>();
        List<TeacherDTO> rightAnswersList = new ArrayList<>();
        teachers.add(new TeacherDTO(1, null));
        teachers.add(new TeacherDTO(2, "Associate Professor"));
        teachers.add(new TeacherDTO(3, "Professor"));
        teachers.add(new TeacherDTO(4, null));
        for (TeacherDTO teacher : teachers) {
            addPersonIfAbsent(teacher.getId());
            getRepository().add(teacher);
            if (teacher.getAcademicRank() == null)
                rightAnswersList.add(teacher);
        }
        List<TeacherDTO> resultList = getRepository().findWithoutRank();
        for (TeacherDTO teacher : teachers) {
            getRepository().delete(teacher);
            deleteAddedPerson(teacher.getId());
        }
        Assert.assertEquals(rightAnswersList, resultList);
    }

    private void addPersonIfAbsent(int idTeacher){
        if(peopleRepository.findById(idTeacher) == null)
            peopleRepository.add(new PersonDTO(idTeacher, LocalDate.of(1970, Month.JANUARY,1)));
    }

    private void deleteAddedPerson(int idTeacher){
        peopleRepository.delete(new PersonDTO(idTeacher, LocalDate.of(1970, Month.JANUARY,1)));
    }
}
