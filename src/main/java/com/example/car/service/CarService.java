package com.example.car.service;

import com.example.car.model.Car;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.ReplicationMode;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor

public class CarService {
    SessionFactory sessionFactory;

@Transactional
    public Car createCar(Car car) {

        sessionFactory.getCurrentSession().persist(car);
        return car;
    }
@Transactional
    public List<Car> getAllCars() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Car", Car.class)
                .getResultList();
    }
    @Transactional
    public Car getCarById(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Car c WHERE c.id = :id", Car.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    @Transactional
    public void deleteCarById(Long id) {
        Car selectedCar = getCarById(id);
        sessionFactory.getCurrentSession()
                .remove(selectedCar);
        sessionFactory.getCurrentSession().replicate(selectedCar, ReplicationMode.LATEST_VERSION);
    }
    @Transactional
    public void updateCar(Car carNew) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(carNew);
    }
    @Transactional
        public List<Car> listHigherThan(Long price) {
            return sessionFactory.getCurrentSession()
                    .createNativeQuery("SELECT * FROM car WHERE price > :price", Car.class)
                    .setParameter("price", price)
                    .getResultList();
    }
}
