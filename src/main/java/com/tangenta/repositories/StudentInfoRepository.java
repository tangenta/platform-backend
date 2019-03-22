package com.tangenta.repositories;

import com.tangenta.data.pojo.StudentInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInfoRepository {
    StudentInfo findById(Long studentId);
    void update(StudentInfo studentInfo);
}
