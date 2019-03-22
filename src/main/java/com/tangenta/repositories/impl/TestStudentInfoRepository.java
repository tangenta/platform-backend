package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.StudentInfo;
import com.tangenta.repositories.StudentInfoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestStudentInfoRepository implements StudentInfoRepository {
    private static List<StudentInfo> allStudentInfo = new ArrayList<StudentInfo>(){{
        add(new StudentInfo(1L));
        add(new StudentInfo(2L));
        add(new StudentInfo(3L));
    }};

    @Override
    public StudentInfo findById(Long studentId) {
        return allStudentInfo.stream()
                .filter(si -> si.getStudentId()
                        .equals(studentId))
                .findFirst()
                .orElse(new StudentInfo(studentId));
    }

    @Override
    public void update(StudentInfo studentInfo) {
        Iterator<StudentInfo> iter = allStudentInfo.iterator();
        while (iter.hasNext()) {
            StudentInfo sInfo = iter.next();
            if (sInfo.getStudentId().equals(studentInfo.getStudentId())) {
                iter.remove();
                break;
            }
        }
        allStudentInfo.add(studentInfo);
    }
}
