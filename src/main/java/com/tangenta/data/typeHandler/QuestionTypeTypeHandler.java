package com.tangenta.data.typeHandler;

import com.tangenta.data.pojo.QuestionType;
import com.tangenta.utils.Utils;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.tangenta.data.pojo.QuestionType.*;

@Alias("QuestionTypeHandler")
@MappedJdbcTypes(value = JdbcType.NUMERIC)
@MappedTypes(QuestionType.class)
public class QuestionTypeTypeHandler extends BaseTypeHandler<QuestionType> {
    private static Integer[] intTypes = {1, 2, 3, 4};
    private static QuestionType[] enumTypes = {
            SingleChoice,
            MultipleChoice,
            TrueOrFalse,
            BlanksFilling,
    };
    private static int mapToInt(QuestionType type) {
        return Utils.mapToSameIndex(enumTypes, type, intTypes);
    }
    private QuestionType mapToEnum(int type) {
        return Utils.mapToSameIndex(intTypes, type, enumTypes);
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i,
                                    QuestionType type, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, mapToInt(type));
    }


    @Override
    public QuestionType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return mapToEnum(resultSet.getInt(s));
    }

    @Override
    public QuestionType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return mapToEnum(resultSet.getInt(i));
    }

    @Override
    public QuestionType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return mapToEnum(callableStatement.getInt(i));
    }
}
