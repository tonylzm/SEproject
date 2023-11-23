package com.example.seproject.config;

import com.example.seproject.entity.visitinfo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class VisitinfoSpecification implements Specification<visitinfo> {
    private final visitinfo filter;

    public VisitinfoSpecification(visitinfo filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<visitinfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getVisitorPhone() != null) {
            predicates.add(cb.like(root.get("visitorPhone"), "%" + filter.getVisitorPhone() + "%"));
        }
        // 添加其他条件
        if (filter.getVisitorName() != null) {
            predicates.add(cb.like(root.get("visitorName"), "%" + filter.getVisitorName() + "%"));
        }
        if (filter.getVisitreason() != null) {
            predicates.add(cb.like(root.get("visitreason"), "%" + filter.getVisitreason() + "%"));
        }
        if (filter.getVisitAreas() != null) {
            predicates.add(cb.like(root.get("visitAreas"), "%" + filter.getVisitAreas() + "%"));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
