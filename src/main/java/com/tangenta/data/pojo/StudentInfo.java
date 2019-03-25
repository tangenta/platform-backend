package com.tangenta.data.pojo;

import java.time.LocalDate;

public class StudentInfo {
    private Long studentId;
    private String studentName;
    private Gender gender;
    private Long pictureId;
    private String partyBranch;
    private LocalDate birthday;
    private String nation;
    private String nativePlace;
    private String politicalLandscape;
    private String college;
    private String major;
    private String currentClass;
    private String lengthOfSchooling;
    private String state;
    private String professionalDirection;
    private LocalDate admissionDate;
    private String dormitoryNumber;
    private String phone;
    private String mailCode;
    private String idNumber;
    private String academicLevel;
    private LocalDate joinLeagueDate;
    private String englishLevel;
    private String graduateSchool;
    private String familyCode;
    private String familyPhone;
    private String fatherName;
    private String fatherWorkPlace;
    private String fatherPhone;
    private String motherName;
    private String motherWorkPlace;
    private String motherPhone;
    private String familyAddress;
    private String HMTCode;

    public StudentInfo() {
    }

    public StudentInfo(Long studentId) {
        this.studentId = studentId;
    }

    public StudentInfo(String studentName, String partyBranch) {
        this.studentName = studentName;
        this.partyBranch = partyBranch;
    }

    public StudentInfo(Long studentId, String studentName, String partyBranch) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.partyBranch = partyBranch;
    }

    public StudentInfo(Long studentId, String studentName, Gender gender, Long picture, String partyBranch, LocalDate birthday,
                       String nation, String nativePlace, String politicalLandscape, String college, String major,
                       String currentClass, String lengthOfSchooling, String state, String professionalDirection,
                       LocalDate admissionDate, String dormitoryNumber, String phone, String mailCode, String idNumber,
                       String academicLevel, LocalDate joinLeagueDate, String englishLevel, String graduateSchool,
                       String familyCode, String familyPhone, String fatherName, String fatherWorkPlace,
                       String fatherPhone, String motherName, String motherWorkPlace, String motherPhone,
                       String familyAddress, String hmtCode) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.pictureId = picture;
        this.partyBranch = partyBranch;
        this.birthday = birthday;
        this.nation = nation;
        this.nativePlace = nativePlace;
        this.politicalLandscape = politicalLandscape;
        this.college = college;
        this.major = major;
        this.currentClass = currentClass;
        this.lengthOfSchooling = lengthOfSchooling;
        this.state = state;
        this.professionalDirection = professionalDirection;
        this.admissionDate = admissionDate;
        this.dormitoryNumber = dormitoryNumber;
        this.phone = phone;
        this.mailCode = mailCode;
        this.idNumber = idNumber;
        this.academicLevel = academicLevel;
        this.joinLeagueDate = joinLeagueDate;
        this.englishLevel = englishLevel;
        this.graduateSchool = graduateSchool;
        this.familyCode = familyCode;
        this.familyPhone = familyPhone;
        this.fatherName = fatherName;
        this.fatherWorkPlace = fatherWorkPlace;
        this.fatherPhone = fatherPhone;
        this.motherName = motherName;
        this.motherWorkPlace = motherWorkPlace;
        this.motherPhone = motherPhone;
        this.familyAddress = familyAddress;
        HMTCode = hmtCode;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Long getPicture() {
        return pictureId;
    }

    public String getPartyBranch() {
        return partyBranch;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getNation() {
        return nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public String getPoliticalLandscape() {
        return politicalLandscape;
    }

    public String getCollege() {
        return college;
    }

    public String getMajor() {
        return major;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public String getLengthOfSchooling() {
        return lengthOfSchooling;
    }

    public String getState() {
        return state;
    }

    public String getProfessionalDirection() {
        return professionalDirection;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public String getDormitoryNumber() {
        return dormitoryNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getMailCode() {
        return mailCode;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public LocalDate getJoinLeagueDate() {
        return joinLeagueDate;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getFatherWorkPlace() {
        return fatherWorkPlace;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getMotherWorkPlace() {
        return motherWorkPlace;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public String getHMTCode() {
        return HMTCode;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setPicture(Long picture) {
        this.pictureId = picture;
    }

    public void setPartyBranch(String partyBranch) {
        this.partyBranch = partyBranch;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void setPoliticalLandscape(String politicalLandscape) {
        this.politicalLandscape = politicalLandscape;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public void setLengthOfSchooling(String lengthOfSchooling) {
        this.lengthOfSchooling = lengthOfSchooling;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setProfessionalDirection(String professionalDirection) {
        this.professionalDirection = professionalDirection;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setDormitoryNumber(String dormitoryNumber) {
        this.dormitoryNumber = dormitoryNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public void setJoinLeagueDate(LocalDate joinLeagueDate) {
        this.joinLeagueDate = joinLeagueDate;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setFatherWorkPlace(String fatherWorkPlace) {
        this.fatherWorkPlace = fatherWorkPlace;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setMotherWorkPlace(String motherWorkPlace) {
        this.motherWorkPlace = motherWorkPlace;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public void setHMTCode(String HMTCode) {
        this.HMTCode = HMTCode;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
