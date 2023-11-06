package com.example.seproject.jpa;

import com.example.seproject.entity.Internal_staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InStaffDao extends JpaRepository<Internal_staff,Integer> {
    //员工Id和密码匹配
    Internal_staff findByStaffIdcardAndStaffPassword(String StaffIdcard, String StaffPassword);
    //员工Id匹配
    Internal_staff findByStaffIdcard(String StaffIdcard);

    @Modifying
    @Query("UPDATE Internal_staff i SET i.UUID = :uuid WHERE i.staffIdcard = :StaffIdcard")
    void updateUUIDByStaffIdcard( String StaffIdcard,  String uuid);
}
