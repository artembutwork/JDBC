/*
 * Represents a record an entity in a scheme called Person
 */

package dtos;

import java.time.LocalDate;

public final class PersonDTO extends DTOBase{

    private final String name;
    private final String surname;
    private final LocalDate dateOfBirth;

    public PersonDTO(int id, String name, String surname, LocalDate dateOfBirth) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public PersonDTO(int id, LocalDate dateOfBirth){
        this(id, "","", dateOfBirth);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersonDTO person = (PersonDTO) obj;
        return getId() == person.getId() &&
                name.equals(person.name) &&
                surname.equals(person.surname) &&
                dateOfBirth.equals(person.dateOfBirth);
    }
}
