/*
  Describes the basic functions that each repository should perform
 */

package repositories.interfaces;

import dtos.DTOBase;
import java.sql.Connection;

public interface IRepository<TDTO extends DTOBase> {

    /**
     * Returns an object representing the connection to the current database.
     *
     * @return a new {@link Connection}
     */
    Connection getConnection();

    /**
     * Adds a record to the repository.
     *
     * @param dto - record to be added to the repository
     */
    void add(TDTO dto);

    /**
     * Updates a record in the repository.
     *
     * @param dto - record to be updated in the repository. If the record
     *              with the id specified in the dto object is not found,
     *              the method throws an CustomException
     */
    void update(TDTO dto);

    /**
     * Removes a record from the repository.
     *
     * @param dto - record to be removed from the repository. If the record
     *              with the id specified in the dto object is not found,
     *              the method throws an CustomException. If the record with
     *              the specified id is found, but the value of the fields
     *              of the dto object does not match the data found, the record
     *              will not be deleted
     */
    void delete(TDTO dto);

    /**
     * Returns an object representing the record of the matching entity.
     *
     * @param id - the value of the primary key to retrieve a record from
     *             the relevant repository
     *
     * @return a <TDTO extends {@link DTOBase}> object representing the record of the
     *         matching entity resulting from the query, or null if no record was found
     */
    TDTO findById(int id);

    /**
     * Returns the number of records in the repository.
     *
     * @return an int number of records in the repository
     */
    int getRowsCount();

    /**
     * Deactivates this connection's auto-commit mode. All its SQL statements
     * are grouped into transactions that are terminated by a call to either
     * the method <code>commit</code> or the method <code>rollback</code>.
     */
    void beginTransaction();

    /**
     * Makes all changes made since the previous commit/rollback permanent and
     * releases any database locks currently held by this <code>Connection</code> object.
     */
    void commitTransaction();

    /**
     * Undoes all changes made in the current transaction and releases any
     * database locks currently held by this <code>Connection</code> object.
     */
    void rollbackTransaction();
}
