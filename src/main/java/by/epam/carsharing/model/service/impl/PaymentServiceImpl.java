package by.epam.carsharing.model.service.impl;

import by.epam.carsharing.model.dao.DaoHelper;
import by.epam.carsharing.model.dao.PaymentDao;
import by.epam.carsharing.model.dao.exception.DaoException;
import by.epam.carsharing.model.entity.Payment;
import by.epam.carsharing.model.service.PaymentService;
import by.epam.carsharing.model.service.exception.ServiceException;

public class PaymentServiceImpl implements PaymentService {

    private static final PaymentDao paymentDao = DaoHelper.getInstance().getPaymentDao();

    @Override
    public void add(Payment entity) throws ServiceException {
        try {
            paymentDao.add(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
