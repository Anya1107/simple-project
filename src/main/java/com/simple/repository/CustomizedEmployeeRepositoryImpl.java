package com.simple.repository;

import com.simple.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomizedEmployeeRepositoryImpl{

    @Autowired
    EntityManager entityManager;

    public List<Employee> findByFilter(String idNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employee = query.from(Employee.class);

        Path<String> idNumPath = employee.get("idNumber");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(idNumPath, "%" + idNumber + "%"));

        query.select(employee)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
