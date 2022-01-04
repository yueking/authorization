package com.yueking.security.core.specification;

import com.yueking.security.core.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserSpecification implements Specification {
    private User user;

    public UserSpecification(User user) {
        this.user = user;

    }
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        Path<String> usernameExp = root.get("username");
        Path<Boolean> delExp = root.get("del");

        Predicate predicateUsername = criteriaBuilder.like(usernameExp, "%" + user.getUsername() + "%");
        Predicate predicateDel = criteriaBuilder.equal(delExp, user.isDel());

        predicateList.add(predicateUsername);
        predicateList.add(predicateDel);

        if (user.getStartDate() != null) {
            Path<Date> createdDateExp = root.get("createdDate");
            Predicate predicateCreate = criteriaBuilder.between(createdDateExp, user.getStartDate(),new Date(user.getEndDate().getTime()+24*3600*1000));
            predicateList.add(predicateCreate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        return criteriaQuery.where(predicates).getRestriction();
        // return criteriaBuilder.and(predicates);
    }
}
