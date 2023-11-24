package com.example.seproject.config;

import com.example.seproject.entity.Internal_staff;
import com.example.seproject.entity.block;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StaffSpecification implements Specification<Internal_staff> {
    private final Internal_staff filter;

    public StaffSpecification(Internal_staff filter) {
        this.filter = filter;
    }
    @Override
    public Predicate toPredicate(Root<Internal_staff> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getStaffIdcard()!= null) {
            predicates.add(cb.like(root.get("staffIdCard"), "%" + filter.getStaffIdcard() + "%"));
        }
        if (filter.getStaffPosition() != null) {
            predicates.add(cb.like(root.get("staffPosition"), "%" + filter.getStaffPosition() + "%"));
        }
        if (filter.getStaffAffiliatedUnit() != null) {
            predicates.add(cb.like(root.get("staffAffiliatedUnit"), "%" + filter.getStaffAffiliatedUnit() + "%"));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }

}
