package com.tangenta.data.typeHandler;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.utils.Utils;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.tangenta.data.pojo.QuestionClassification.*;

@Alias("QuestionClassHandler")
@MappedJdbcTypes(value = JdbcType.NUMERIC)
@MappedTypes(QuestionClassification.class)
public class QuestionClassificationTypeHandler extends BaseTypeHandler<QuestionClassification> {
    private static Integer[] intTypes = {1, 2, 3, 4, 5};
    private static QuestionClassification[] enumTypes = {
            Lilunjichu, Jilvxing, Jiazhiguan, Daodepingjia, Sixiangxianjinxing
    };

    private static int mapToInt(QuestionClassification type) {
        return Utils.mapToSameIndex(enumTypes, type, intTypes);
    }

    private QuestionClassification mapToEnum(int type) {
        return Utils.mapToSameIndex(intTypes, type, enumTypes);
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i,
                                    QuestionClassification type, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, mapToInt(type));
    }

    @Override
    public QuestionClassification getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return mapToEnum(resultSet.getInt(s));
    }

    @Override
    public QuestionClassification getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return mapToEnum(resultSet.getInt(i));
    }

    @Override
    public QuestionClassification getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return mapToEnum(callableStatement.getInt(i));
    }
}
