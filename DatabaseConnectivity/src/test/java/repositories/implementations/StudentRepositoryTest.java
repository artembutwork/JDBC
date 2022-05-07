package repositories.implementations;

import dtos.*;
import org.junit.*;
import repositories.RepositoryBaseTest;
import repositories.interfaces.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

public final class StudentRepositoryTest extends RepositoryBaseTest<StudentDTO, IStudentRepository, StudentRepository> {

    private final IRepository<PersonDTO> peopleRepository;
    private StudentDTO testStudent;

    public StudentRepositoryTest()
            throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(StudentRepository.class);
        peopleRepository = new PersonRepository(getRepository().getConnection());

    }

    @Test
    public void add(){
        testStudent = new StudentDTO(1, "s22841", LocalDate.of(2020, Month.APRIL, 1));
        addPersonIfAbsent(testStudent.getId());
        super.add(testStudent);
    }

    @Test
    public void update(){
        LocalDate dateOfEnrollment = LocalDate.of(2021, Month.MAY, 1);
        super.update(new StudentDTO(1, "s22841", dateOfEnrollment));
    }

    @Test
    public void delete(){
        testStudent = new StudentDTO(1, "s22841", LocalDate.of(2021, Month.MAY, 1));
        super.delete(testStudent);
        deleteAddedPerson(testStudent.getId());
    }

    @Test
    public void findById(){
        testStudent = new StudentDTO(1, "s22841", LocalDate.of(2020, Month.APRIL, 1));
        addPersonIfAbsent(testStudent.getId());
        super.findById(testStudent, testStudent.getId());
        deleteAddedPerson(testStudent.getId());
    }

    @Test
    public void findEnrolledEarlierThen(){
        LocalDate date = LocalDate.of(2020, Month.APRIL,1);
        List<StudentDTO> allRooms = new ArrayList<>();
        List<StudentDTO> rightAnswersList = new ArrayList<>();
        allRooms.add(new StudentDTO(1, "s11111", LocalDate.of(2020, Month.MAY,1)));
        allRooms.add(new StudentDTO(2, "s22222", LocalDate.of(2021, Month.APRIL,1)));
        allRooms.add(new StudentDTO(3, "s33333", LocalDate.of(2020, Month.MARCH,1)));
        for (StudentDTO student : allRooms) {
            addPersonIfAbsent(student.getId());
            getRepository().add(student);
            if (student.getDateOfEnrollment().isBefore(date))
                rightAnswersList.add(student);
        }
        List<StudentDTO> resultList = getRepository().findEnrolledEarlierThen(date);
        for (StudentDTO student : allRooms) {
            getRepository().delete(student);
            deleteAddedPerson(student.getId());
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
