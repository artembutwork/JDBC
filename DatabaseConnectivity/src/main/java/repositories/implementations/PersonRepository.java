/*
 * Represents an entity in a scheme called Person
 */

package repositories.implementations;

import dtos.PersonDTO;
import exception.CustomException;
import repositories.RepositoryBase;
import repositories.interfaces.IPersonRepository;
import java.time.LocalDate;
import java.sql.*;
import java.util.*;

public final class PersonRepository extends RepositoryBase<PersonDTO> implements IPersonRepository {

    private static final String TABLE_NAME = "Person";

    private static final String PERSON_ID = "IdPerson";
    private static final String PERSON_NAME = "Name";
    private static final String PERSON_SURNAME = "Surname";
    private static final String PERSON_DATE_OF_BIRTH = "DateOfBirth";

    public PersonRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<Object> getParameters(PersonDTO person) {
        return Arrays.asList(person.getName(), person.getSurname(), person.getDateOfBirth());
    }

    @Override
    protected List<String> getColumnsName() {
        return Arrays.asList(PERSON_ID, PERSON_NAME, PERSON_SURNAME, PERSON_DATE_OF_BIRTH);
    }

    @Override
    protected PersonDTO getDTO(ResultSet resultSet) throws SQLException {
        int personId = resultSet.getInt(1);
        String personName = resultSet.getString(2);
        String personSurname = resultSet.getString(3);
        LocalDate personDateOfBirth = resultSet.getDate(4).toLocalDate();
        return new PersonDTO(personId, personName, personSurname, personDateOfBirth);
    }

    @Override
    public List<PersonDTO> findByNameBeginning(String startsWith) {
        List<PersonDTO> people = new ArrayList<>();
        try {
            Connection connection = getConnection();
            final String findCommand = "SELECT " + PERSON_ID + ", " + PERSON_NAME +
                    ", " + PERSON_SURNAME + ", " + PERSON_DATE_OF_BIRTH +
                    " FROM " + TABLE_NAME +
                    " WHERE " + PERSON_NAME + " LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(findCommand);
            preparedStatement.setString(1, startsWith + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() && resultSet.getRow() != 0)
                people.add(getDTO(resultSet));
        } catch (Throwable exception){
            String message = exception.getMessage();
            throw new CustomException(message, exception);
        }
        return people;
    }
}
