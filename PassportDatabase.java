package database;

import exceptions.DatabaseException;
import exceptions.ValidationException;
import models.Applicant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassportDatabase implements DatabaseOperations {
    private Connection connection;

    public PassportDatabase(String dbURL, String user, String password) throws DatabaseException {
        try {
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException e) {
            throw new DatabaseException("Error connecting to database: " + e.getMessage());
        }
    }

    @Override
    public void createApplicant(Applicant applicant) throws DatabaseException, ValidationException {
        String sql = "INSERT INTO applicants (application_id, name, address, phone_number, age, creation_date, passport_fee) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, applicant.getApplicationId());
            stmt.setString(2, applicant.getName());
            stmt.setString(3, applicant.getAddress());
            stmt.setString(4, applicant.getPhoneNumber());
            stmt.setInt(5, applicant.getAge());
            stmt.setDate(6, new java.sql.Date(applicant.getCreationDate().getTime()));
            stmt.setInt(7, applicant.getPassportFee());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error while creating applicant: " + e.getMessage());
        }
    }

    @Override
    public Applicant readApplicant(String applicationId) throws DatabaseException, ValidationException {
        String sql = "SELECT * FROM applicants WHERE application_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, applicationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Applicant(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        applicationId,
                        rs.getInt("age"),
                        rs.getDate("creation_date"),
                        rs.getInt("passport_fee")
                );
            } else {
                throw new DatabaseException("Applicant not found with ID: " + applicationId);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while reading applicant: " + e.getMessage());
        }
    }

    @Override
    public void updateApplicant(Applicant applicant) throws DatabaseException {
        String sql = "UPDATE applicants SET name = ?, address = ?, phone_number = ?, age = ?, passport_fee = ? WHERE application_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, applicant.getName());
            stmt.setString(2, applicant.getAddress());
            stmt.setString(3, applicant.getPhoneNumber());
            stmt.setInt(4, applicant.getAge());
            stmt.setInt(5, applicant.getPassportFee());
            stmt.setString(6, applicant.getApplicationId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error while updating applicant: " + e.getMessage());
        }
    }

    @Override
    public void deleteApplicant(String applicationId) throws DatabaseException {
        String sql = "DELETE FROM applicants WHERE application_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, applicationId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error while deleting applicant: " + e.getMessage());
        }
    }

    @Override
    public List<Applicant> getAllApplicants() throws DatabaseException {
        String sql = "SELECT * FROM applicants";
        List<Applicant> applicants = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                applicants.add(new Applicant(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("application_id"),
                        rs.getInt("age"),
                        rs.getDate("creation_date"),
                        rs.getInt("passport_fee")
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while retrieving applicants: " + e.getMessage());
        } catch (ValidationException ve) {
            throw new DatabaseException("Validation error: " + ve.getMessage());
        }

        return applicants;
    }
}
