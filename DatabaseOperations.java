package database;

import exceptions.DatabaseException;
import exceptions.ValidationException;
import models.Applicant;

import java.util.List;

public interface DatabaseOperations {
    void createApplicant(Applicant applicant) throws DatabaseException, ValidationException;

    Applicant readApplicant(String applicationId) throws DatabaseException, ValidationException;

    void updateApplicant(Applicant applicant) throws DatabaseException, ValidationException;

    void deleteApplicant(String applicationId) throws DatabaseException, ValidationException;

    List<Applicant> getAllApplicants() throws DatabaseException; // Metode baru
}
