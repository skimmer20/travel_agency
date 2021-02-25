package dao.impl;

import connect.ConnectionManager;
import dao.TravelAgencyDao;
import entity.TravelAgency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuriismac on 2/24/21.
 * @project travel_agency
 */
public class TravelAgencyDaoImpl implements TravelAgencyDao {

    private static final String CREATE = "insert into agency(`name`,`address`, `email`) values(?, ?, ?)";
    private static final String READ_BY_ID = "select * from agency where id = ?";
    private static final String READ_ALL = "select * from agency";
    private static final String UPDATE_BY_ID = "update agency set name = ?, address = ?, email = ? where id = ?";
    private static final String DELETE_BY_ID = "delete from agency where id = ?";

    @Override
    public TravelAgency create(TravelAgency travelAgency) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, travelAgency.getName());
            preparedStatement.setString(2, travelAgency.getAddress());
            preparedStatement.setString(3, travelAgency.getEmail());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    travelAgency.setId(resultSet.getLong(1));
                }
                ConnectionManager.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travelAgency;
    }

    @Override
    public TravelAgency read(Long id) {
        TravelAgency travelAgency = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long agencyId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                travelAgency = new TravelAgency(agencyId, name, address, email);
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travelAgency;
    }

    @Override
    public TravelAgency update(TravelAgency travelAgency) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, travelAgency.getName());
            preparedStatement.setString(2, travelAgency.getAddress());
            preparedStatement.setString(3, travelAgency.getEmail());
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travelAgency;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TravelAgency> getAll() {
        List<TravelAgency> travelAgencyList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                travelAgencyList.add(new TravelAgency(id, name, address, email));
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travelAgencyList;
    }
}
