package repositories;

import dtos.DTOBase;
import org.junit.*;
import repositories.interfaces.IRepository;
import java.lang.reflect.*;
import java.sql.*;

public abstract class RepositoryBaseTest<TDTO extends DTOBase,
                                        TRepository extends IRepository<TDTO>,
                                        TRepositoryImplementation extends TRepository> {

    private static final String CONNECTION_URI = "jdbc:sqlserver://localhost:1434;database=TestDB";
    private static final String CONNECTION_USERNAME = "admin";
    private static final String CONNECTION_PASSWORD = "12345678";
    private final TRepository repository;

    protected RepositoryBaseTest(Class<TRepositoryImplementation> implementationClassType) throws
            NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<TRepositoryImplementation> constructor = implementationClassType.getConstructor(Connection.class);
        constructor.setAccessible(true);
        repository = constructor.newInstance(getConnection());
    }

    /*
     * Is executed before each unit test.
     */
    @Before
    public void beginTransaction(){
        repository.beginTransaction();
    }

    /*
     * Is executed after each unit test.
     */
    @After
    public void endTransaction(){
        repository.commitTransaction();
        //repository.rollbackTransaction();
    }

    /**
     * Verifies if an object representing the record in
     * entity after adding it to the repository is found.
     *
     * Verifies whether the number of records in the
     * repository has increased by 1 after adding.
     *
     * @param dto - record to be added to the repository
     */
    protected void add(TDTO dto) {
        boolean isPresent = false;
        int expectedRowsCount = repository.getRowsCount() + 1;
        repository.add(dto);
        if(repository.findById(dto.getId()) != null)
            isPresent = true;
        int actualRowsCount = repository.getRowsCount();

        Assert.assertTrue(isPresent);
        Assert.assertEquals(actualRowsCount, expectedRowsCount);
    }

    /**
     * Verifies if an object representing the record in
     * entity after updating in the repository is found
     * and not equals to object before updating.
     *
     * Verifies whether the number of records in the
     * repository has not increased after updating.
     *
     * @param dto - record to be updated in the repository
     */
    protected void update(TDTO dto){
        int expectedRowsCount = repository.getRowsCount();
        TDTO beforeUpdateDto = repository.findById(dto.getId());
        repository.update(dto);
        TDTO afterUpdateDto = repository.findById(dto.getId());
        int actualRowsCount = repository.getRowsCount();

        Assert.assertNotEquals(beforeUpdateDto, afterUpdateDto);
        Assert.assertEquals(actualRowsCount, expectedRowsCount);
    }

    /**
     * Verifies if an object representing the record in
     * entity after removing from the repository is not found.
     *
     * Verifies whether the number of records in the
     * repository has decreased by 1 after removing.
     *
     * @param dto - record to be removed from the repository
     */
    protected void delete(TDTO dto){
        boolean isPresent = true;
        int expectedRowsCount = repository.getRowsCount() - 1;
        repository.delete(dto);
        if(repository.findById(dto.getId()) == null)
            isPresent = false;
        int actualRowsCount = repository.getRowsCount();

        Assert.assertFalse(isPresent);
        Assert.assertEquals(actualRowsCount, expectedRowsCount);
    }

    /**
     * Verifies if an object representing the record in
     * entity after adding in the repository is found
     * and equals to object before adding.
     *
     * @param originalDto - record to be added in the repository
     * @param id - id of the record to be found in the repository
     */
    protected void findById(TDTO originalDto, int id){
        repository.add(originalDto);
        TDTO resultDto = repository.findById(id);
        Assert.assertEquals(originalDto, resultDto);
        repository.delete(originalDto);
    }

    public TRepository getRepository() {
        return repository;
    }

    /**
     * Returns an object representing the connection to the current database.
     *
     * @return a new {@link Connection}
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URI, CONNECTION_USERNAME, CONNECTION_PASSWORD);
    }
}
