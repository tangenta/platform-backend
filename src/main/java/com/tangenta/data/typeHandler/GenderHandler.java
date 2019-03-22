package com.tangenta.data.typeHandler;

import com.tangenta.data.pojo.Gender;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Alias("GenderHandler")
@MappedJdbcTypes(value = JdbcType.CHAR)
@MappedTypes(Gender.class)
public class GenderHandler extends BaseTypeHandler<Gender> {
    private static String[] strings = {"M", "F"};
    private static Gender[] genders = {Gender.MALE, Gender.FEMALE};

    private static String mapGenderToChar(Gender gender) {
        int index = 0;
        for (Gender g : genders) {
            if (g.equals(gender)) break;
            index++;
        }
        return strings[index];
    }

    private static Gender mapStringToGender(String x) {
        int index = 0;
        for (String s : strings) {
            if (s.equals(x)) break;
            index++;
        }
        if (index == strings.length) throw new RuntimeException("unrecognized char: " + x);
        return genders[index];
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Gender parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, mapGenderToChar(parameter));
    }

    @Override
    public Gender getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return mapStringToGender(rs.getString(columnName));
    }

    @Override
    public Gender getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return mapStringToGender(rs.getString(columnIndex));
    }

    @Override
    public Gender getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return mapStringToGender(cs.getString(columnIndex));
    }
}
