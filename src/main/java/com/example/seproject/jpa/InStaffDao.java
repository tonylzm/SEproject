package com.example.seproject.jpa;

import com.example.seproject.config.StaffSpecification;
import com.example.seproject.entity.Internal_staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InStaffDao extends JpaRepository<Internal_staff,Integer> {
    //员工Id和密码匹配
    Internal_staff findByStaffIdcardAndStaffPassword(String StaffIdcard, String StaffPassword);
    //员工Id匹配
    Internal_staff findByStaffIdcard(String StaffIdcard);

    Page<Internal_staff> findAll(Specification<Internal_staff> spec, Pageable pageable);

    @Modifying
    @Query("UPDATE Internal_staff i SET i.UUID = :uuid WHERE i.staffIdcard = :StaffIdcard")
    void updateUUIDByStaffIdcard( String StaffIdcard,  String uuid);


    void deleteByStaffIdcard(String staffIdcard);
}
