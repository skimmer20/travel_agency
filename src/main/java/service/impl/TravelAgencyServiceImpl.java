package service.impl;

import dao.TravelAgencyDao;
import dao.impl.TravelAgencyDaoImpl;
import entity.TravelAgency;
import service.TravelAgencyService;

import java.util.List;

/**
 * @author yuriismac on 3/6/21.
 * @project travel_agency
 */
public class TravelAgencyServiceImpl implements TravelAgencyService {

    private TravelAgencyDao travelAgencyDao;
    private static TravelAgencyService travelAgencyServiceImpl;

    public static TravelAgencyService travelAgencyService() {
        if (travelAgencyServiceImpl == null) {
            travelAgencyServiceImpl = new TravelAgencyServiceImpl();
        }
        return travelAgencyServiceImpl;
    }

    private TravelAgencyServiceImpl() {
        travelAgencyDao = new TravelAgencyDaoImpl();
    }

    @Override
    public TravelAgency create(TravelAgency travelAgency) {
        return this.travelAgencyDao.create(travelAgency);
    }

    @Override
    public TravelAgency read(Integer id) {
        return this.travelAgencyDao.read(id);
    }

    @Override
    public TravelAgency update(TravelAgency travelAgency) {
        return this.travelAgencyDao.update(travelAgency);
    }

    @Override
    public void delete(Integer id) {
        this.travelAgencyDao.delete(id);
    }

    @Override
    public List<TravelAgency> getAll() {
        return travelAgencyDao.getAll();
    }
}
