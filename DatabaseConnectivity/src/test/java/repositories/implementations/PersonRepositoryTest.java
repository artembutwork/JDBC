package repositories.implementations;

import dtos.PersonDTO;
import org.junit.*;
import repositories.RepositoryBaseTest;
import repositories.interfaces.IPersonRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

public final class PersonRepositoryTest extends RepositoryBaseTest<PersonDTO, IPersonRepository, PersonRepository> {

    public PersonRepositoryTest()
            throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(PersonRepository.class);
    }

    @Test
    public void add(){
        LocalDate dateOfBirth = LocalDate.of(2003, Month.JUNE,10);
        super.add(new PersonDTO(1, "Will", "Smith", dateOfBirth));
    }

    @Test
    public void update(){
        LocalDate dateOfBirth = LocalDate.of(2004, Month.FEBRUARY,12);
        super.update(new PersonDTO(1, "John", "Smith", dateOfBirth));
    }

    @Test
    public void delete(){
        LocalDate dateOfBirth = LocalDate.of(2004, Month.FEBRUARY, 12);
        super.delete(new PersonDTO(1, "John", "Smith", dateOfBirth));
    }

    @Test
    public void findById(){
        LocalDate dateOfBirth = LocalDate.of(2003, Month.JUNE,10);
        PersonDTO personDTO = new PersonDTO(1, "Will", "Smith", dateOfBirth);
        super.findById(personDTO, personDTO.getId());
    }

    @Test
    public void findByNameBeginning(){
        String nameBeginning = "Sa";
        List<PersonDTO> allPeople = new ArrayList<>();
        List<PersonDTO> rightAnswersList = new ArrayList<>();
        allPeople.add(new PersonDTO(1, "Santiago", "Johnson", LocalDate.of(1990, Month.DECEMBER, 15)));
        allPeople.add(new PersonDTO(2, "Will", "Smith", LocalDate.of(2004, Month.JUNE, 15)));
        allPeople.add(new PersonDTO(3, "Samuel", "Williams", LocalDate.of(2003, Month.JULY, 1)));
        allPeople.add(new PersonDTO(4, "Ariela", "Miller", LocalDate.of(1997, Month.JUNE, 6)));
        for (PersonDTO person : allPeople) {
            getRepository().add(person);
            if (person.getName().startsWith(nameBeginning))
                rightAnswersList.add(person);
        }
        List<PersonDTO> resultList = getRepository().findByNameBeginning(nameBeginning);
        for (PersonDTO person : allPeople)
            getRepository().delete(person);
        Assert.assertEquals(rightAnswersList, resultList);
    }
}
