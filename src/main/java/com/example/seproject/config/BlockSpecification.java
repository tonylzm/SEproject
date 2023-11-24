package com.example.seproject.config;

import com.example.seproject.entity.block;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class BlockSpecification implements Specification<block> {
    private final block filter;

    public BlockSpecification(block filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<block> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getVisitorName()!= null) {
            predicates.add(cb.like(root.get("visitorName"), "%" + filter.getVisitorName()+ "%"));
        }
        if (filter.getBlockpeople() != null) {
            predicates.add(cb.like(root.get("blockpeople"), "%" + filter.getBlockpeople() + "%"));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
