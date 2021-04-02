package by.epam.carsharing.model.service;

import by.epam.carsharing.model.entity.Payment;
import by.epam.carsharing.model.service.exception.ServiceException;

public interface PaymentService {
    void add(Payment entity) throws ServiceException;
}
