package com.tangenta.service;

import com.tangenta.data.pojo.Gender;
import com.tangenta.data.pojo.StudentInfo;
import com.tangenta.repositories.StudentInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentInfoService {
    private StudentInfoRepository studentInfoRepository;

    public StudentInfoService(StudentInfoRepository studentInfoRepository) {
        this.studentInfoRepository = studentInfoRepository;
    }

    public StudentInfo get(Long studentId) {
        return studentInfoRepository.findById(studentId);
    }

    public void update(Long studentId, String studentName, Gender gender, Long picture, String partyBranch, LocalDate birthday,
                       String nation, String nativePlace, String politicalLandscape, String college, String major,
                       String currentClass, String lengthOfSchooling, String state, String professionalDirection,
                       LocalDate admissionDate, String dormitoryNumber, String phone, String mailCode, String idNumber,
                       String academicLevel, LocalDate joinLeagueDate, String englishLevel, String graduateSchool,
                       String familyCode, String familyPhone, String fatherName, String fatherWorkPlace,
                       String fatherPhone, String motherName, String motherWorkPlace, String motherPhone,
                       String familyAddress, String hmtCode) {
        StudentInfo oldStudentInfo = get(studentId);
        if (studentName != null) oldStudentInfo.setStudentName(studentName);
        if (gender != null) oldStudentInfo.setGender(gender);
        if (picture != null) oldStudentInfo.setPicture(picture);
        if (partyBranch != null) oldStudentInfo.setPartyBranch(partyBranch);
        if (birthday != null) oldStudentInfo.setBirthday(birthday);
        if (nation != null) oldStudentInfo.setNation(nation);
        if (nativePlace != null) oldStudentInfo.setNativePlace(nativePlace);
        if (politicalLandscape != null) oldStudentInfo.setPoliticalLandscape(politicalLandscape);
        if (college != null) oldStudentInfo.setCollege(college);
        if (major != null) oldStudentInfo.setMajor(major);
        if (currentClass != null) oldStudentInfo.setCurrentClass(currentClass);
        if (lengthOfSchooling != null) oldStudentInfo.setLengthOfSchooling(lengthOfSchooling);
        if (state != null) oldStudentInfo.setState(state);
        if (professionalDirection != null) oldStudentInfo.setProfessionalDirection(professionalDirection);
        if (admissionDate != null) oldStudentInfo.setAdmissionDate(admissionDate);
        if (dormitoryNumber != null) oldStudentInfo.setDormitoryNumber(dormitoryNumber);
        if (phone != null) oldStudentInfo.setPhone(phone);
        if (mailCode != null) oldStudentInfo.setMailCode(mailCode);
        if (idNumber != null) oldStudentInfo.setIdNumber(idNumber);
        if (academicLevel != null) oldStudentInfo.setAcademicLevel(academicLevel);
        if (joinLeagueDate != null) oldStudentInfo.setJoinLeagueDate(joinLeagueDate);
        if (englishLevel != null) oldStudentInfo.setEnglishLevel(englishLevel);
        if (graduateSchool != null) oldStudentInfo.setGraduateSchool(graduateSchool);
        if (familyCode != null) oldStudentInfo.setFamilyCode(familyCode);
        if (familyPhone != null) oldStudentInfo.setFamilyPhone(familyPhone);
        if (fatherName != null) oldStudentInfo.setFatherName(fatherName);
        if (fatherWorkPlace != null) oldStudentInfo.setFatherWorkPlace(fatherWorkPlace);
        if (fatherPhone != null) oldStudentInfo.setFatherPhone(fatherPhone);
        if (motherName != null) oldStudentInfo.setMotherName(motherName);
        if (motherWorkPlace != null) oldStudentInfo.setMotherWorkPlace(motherWorkPlace);
        if (motherPhone != null) oldStudentInfo.setMotherPhone(motherPhone);
        if (familyAddress != null) oldStudentInfo.setFamilyAddress(familyAddress);
        if (hmtCode != null) oldStudentInfo.setHMTCode(hmtCode);


        studentInfoRepository.update(oldStudentInfo);
    }
}
