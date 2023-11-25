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
    private final String tabname;

    public VisitinfoSpecification(visitinfo filter, String tabname) {
        this.filter = filter;
        this.tabname = tabname;
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
        if (filter.getArrivedate() != null) {
            // 假设 filter.getArrivedate() 返回的是 java.util.Date 对象
            predicates.add(cb.equal(root.get("arrivedate"), filter.getArrivedate()));
        }
        if (filter.getArrivetime() != null) {
            predicates.add(cb.like(root.get("arrivetime"), "%" + filter.getArrivetime() + "%"));
        }
        if (filter.getLefttime() != null) {
            predicates.add(cb.like(root.get("lefttime"), "%" + filter.getLefttime() + "%"));
        }
        if ("unreviewed".equals(tabname) && filter.isApplicationStatusIsNull()) {
            predicates.add(cb.isNull(root.get("applicationStatus")));
        } else if ("reviewed".equals(tabname) && (filter.isApplicationStatusIsNull() || filter.getApplicationStatus() == null)) {
            predicates.add(cb.isNotNull(root.get("applicationStatus")));
        } else if ("coming".equals(tabname)) {
            // Add conditions for 'coming' tab
            System.out.println("coming");
            predicates.add(cb.equal(root.get("applicationStatus"), "通过"));
            predicates.add(cb.isNull(root.get("UUID")));
        } else if ("accessing".equals(tabname)) {
            // Add conditions for 'access' tab
            predicates.add(cb.equal(root.get("applicationStatus"), "通过"));
            predicates.add(cb.isNotNull(root.get("UUID")));
        } else if ("history".equals(tabname)) {
            // Add conditions for 'history' tab
            predicates.add(cb.equal(root.get("applicationStatus"), "已访问"));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
