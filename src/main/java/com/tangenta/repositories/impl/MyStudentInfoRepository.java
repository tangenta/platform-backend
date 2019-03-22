package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.StudentInfoMapper;
import com.tangenta.data.pojo.StudentInfo;
import com.tangenta.repositories.StudentInfoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")
public class MyStudentInfoRepository implements StudentInfoRepository {
    private StudentInfoMapper studentInfoMapper;

    public MyStudentInfoRepository(StudentInfoMapper studentInfoMapper) {
        this.studentInfoMapper = studentInfoMapper;
    }

    @Override
    public StudentInfo findById(Long studentId) {
        StudentInfo result = studentInfoMapper.get(studentId);
        if (result == null) {
            result = new StudentInfo(studentId);
            studentInfoMapper.create(result);
        }
        return result;
    }

    @Override
    public void update(StudentInfo studentInfo) {
        studentInfoMapper.set(studentInfo);
    }
}
