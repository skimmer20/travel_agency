package abstr;

import java.util.List;

/**
 * @author yuriismac on 2/23/21.
 * @project travel_agency
 */
public interface AbstractCrudOperation <T> {

    T create (T t);

    T read (Integer id);

    T update (T t);

    void delete (Integer id);

    List<T> getAll();

}
