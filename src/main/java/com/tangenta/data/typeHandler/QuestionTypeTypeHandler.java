package com.tangenta.data.typeHandler;

import com.tangenta.data.pojo.QuestionType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.tangenta.data.pojo.QuestionType.*;

@MappedJdbcTypes(value = JdbcType.NUMERIC)
@MappedTypes(QuestionType.class)
public class QuestionTypeTypeHandler extends BaseTypeHandler<QuestionType> {
    private static Integer[] intTypes = {1, 2, 3, 4, 5};
    private static QuestionType[] enumTypes = {
            Lilunjichu, Jilvxing, Jiazhiguan, Daodepingjia, Sixiangxianjinxing
    };

    private <T, U> T mapToSameIndex(U[] src, U obj, T[] dest) {
        assert src.length == dest.length;
        for (int i = 0; i != src.length; ++i) {
            if (src[i].equals(obj)) {
                return dest[i];
            }
        }
        throw new RuntimeException("impossible to reach here");
    }

    private int mapToInt(QuestionType type) {
        return mapToSameIndex(enumTypes, type, intTypes);
    }

    private QuestionType mapToEnum(int type) {
        return mapToSameIndex(intTypes, type, enumTypes);
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
