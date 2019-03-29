package com.tangenta.utils;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import java.util.Arrays;
import java.util.List;


public class Critic {

    //标准差
    static double[] GetVariance(Matrix matrix, double[][] itemArray) {

        List<Matrix> columnList = matrix.getRowList();

        double[] average = new double[(int) matrix.getRowCount()];

        double[] variance = new double[(int) matrix.getRowCount()];
        //System.out.println("标准差");
        for (int i = 0; i < matrix.getRowCount(); i++) {
            average[i] = columnList.get(i).getMeanValue();
            double sum = 0;
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                sum = sum + Math.pow((itemArray[i][j] - average[i]), 2);
            }
            variance[i] = Math.sqrt(sum / (matrix.getRowCount() - 1));
            //System.out.print(variance[i] + "  ");
        }
        //System.out.println();
        return variance;
    }

    static double[] GetAverage(Matrix matrix) {
        List<Matrix> columnList = matrix.getRowList();

        double[] average = new double[(int) matrix.getRowCount()];
        for (int i = 0; i < matrix.getRowCount(); i++) {
            average[i] = columnList.get(i).getMeanValue();
        }
        return average;
    }


    //去除奇异点
    static Matrix RemovalSingularity(Matrix item) {

        double[][] itemArray = item.toDoubleArray();
        double[] average = GetAverage(item);


        double[] variance = GetVariance(item, itemArray);
        //System.out.println("去除 奇异点");
        for (int i = 0; i < item.getRowCount(); i++) {
            for (int j = 0; j < item.getColumnCount(); j++) {
                if (Math.abs(itemArray[i][j] - average[i]) > 3 * variance[i]) {
                    System.out.print(Math.abs(itemArray[i][j] - average[i]) + " // " + 3 * variance[i]);
                    itemArray[i][j] = 0;
                    //System.out.print(itemArray[i][j] + "  ");
                }
            }
            //System.out.println();
        }

        item = DenseMatrix.Factory.importFromArray(itemArray);

        return item;
    }

    //无量纲化
    static Matrix GetNormalize(Matrix item, boolean maxIsBetter[]) {

        List<Matrix> columnList = item.getRowList();

        double[] max = new double[(int) item.getRowCount()];
        double[] min = new double[(int) item.getRowCount()];
        //System.out.println(max.length);
        for (int i = 0; i < item.getRowCount(); i++) {
            max[i] = columnList.get(i).getMaxValue();
            min[i] = columnList.get(i).getMinValue();
        }

        double[][] itemArray = item.toDoubleArray();
        //System.out.println("无量纲化");
        for (int i = 0; i < item.getRowCount(); i++) {
            for (int j = 0; j < item.getColumnCount(); j++) {
                double tem = max[i] - min[i];
                if(max[i] - min[i] == 0)
                    tem+=1;

                if (maxIsBetter[i])
                    itemArray[i][j] = (itemArray[i][j] - min[i]) / tem;
                else
                    itemArray[i][j] = (max[i] - itemArray[i][j]) / tem;
                //System.out.print(itemArray[i][j] + "  ");
            }
            //System.out.println();
        }

        Matrix normalizeMatrix = Matrix.Factory.importFromArray(itemArray);

        return normalizeMatrix;
    }

    //建立相关矩阵
    static Matrix RelationshipMatrix(Matrix NM, boolean maxIsBetter[]) {


        Matrix normalizeMatrix = GetNormalize(NM, maxIsBetter);

        List<Matrix> normalizeList = normalizeMatrix.getRowList();
        double[][] normalizeArray = normalizeMatrix.toDoubleArray();

        double[] average = new double[(int) normalizeMatrix.getRowCount()];

        for (int i = 0; i < normalizeMatrix.getRowCount(); i++) {
            average[i] = normalizeList.get(i).getMeanValue();
        }

        double[][] sum1 = new double[(int) NM.getRowCount()][(int) NM.getRowCount()];
        double[][] sum2 = new double[(int) NM.getRowCount()][(int) NM.getRowCount()];
        double[][] sum3 = new double[(int) NM.getRowCount()][(int) NM.getRowCount()];

        for (int p = 0; p < (int) NM.getRowCount(); p++) {
            for (int q = 0; q < (int) NM.getRowCount(); q++) {
                for (int i = 0; i < NM.getColumnCount(); i++) {
                    sum1[p][q] = sum1[p][q] + (normalizeArray[p][i] - average[p]) * (normalizeArray[q][i] - average[q]);
                    sum2[p][q] = sum2[p][q] + Math.pow((normalizeArray[p][i] - average[p]), 2);
                    sum3[p][q] = sum3[p][q] + Math.pow((normalizeArray[q][i] - average[q]), 2);
                }
            }
        }

        double[][] relationshipArray = new double[(int) NM.getRowCount()][(int) NM.getRowCount()];

        //System.out.println("相关系数矩阵");
        for (int i = 0; i < NM.getRowCount(); i++) {
            for (int j = 0; j < NM.getRowCount(); j++) {
                if (i == j)
                    relationshipArray[i][j] = 1;
                else
                    relationshipArray[i][j] = sum1[i][j] / Math.sqrt(sum2[i][j] * sum3[i][j]);
                //System.out.print(relationshipArray[i][j] + "  ");
            }
            //System.out.println();
        }


        Matrix relationshipMatrix = Matrix.Factory.importFromArray(relationshipArray);
        return relationshipMatrix;
    }


    //CRITIC赋值
    static public double[] CRITIC(double[][] matrix) {

        Matrix itemMatrix = Matrix.Factory.importFromArray(matrix);
//        itemMatrix = itemMatrix.transpose();

        boolean[] maxIsBetter = new boolean[10];
        Arrays.fill(maxIsBetter, true);

        double resultWeight[] = new double[(int) itemMatrix.getRowCount()];
        itemMatrix = RemovalSingularity(itemMatrix);
        double[][] itemArray = itemMatrix.toDoubleArray();

        double[] variance = GetVariance(itemMatrix, itemArray);
        Matrix relationshipMatrix = RelationshipMatrix(itemMatrix, maxIsBetter);
        double[][] relationshipArray = relationshipMatrix.toDoubleArray();
        double[] C = new double[(int) itemMatrix.getRowCount()];

        for (int i = 0; i < (int) itemMatrix.getRowCount(); i++) {
            double sum = 0;
            for (int j = 0; j < (int) itemMatrix.getRowCount(); j++)
                sum = sum + 1 - relationshipArray[i][j];
            C[i] = variance[i] * sum;
        }
        for (int i = 0; i < itemMatrix.getRowCount(); i++) {
            double sum = 0;
            for (int j = 0; j < itemMatrix.getRowCount(); j++)
                sum = sum + C[j];
            resultWeight[i] = C[i] / sum;
        }
        return resultWeight;
    }
}