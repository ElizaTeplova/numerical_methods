package com.example.sle.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
public class SLEService {

    public static double[] runoutMethod(double[][] a, double[] b) {
        double[] x = new double[a.length];
        int row = 0;
        double[] A = new double[a.length];
        double[] B = new double[a.length];
        double[] e = new double[a.length];

        A[0] = -a[0][1] / a[0][0];
        B[0] = b[0] / a[0][0];


        for (row = 1; row < a.length - 1; row++) {
            e[row] = a[row][row - 1] * A[row - 1] + a[row][row];
            if (e[row] <= 10e-5) {
                log.error("e[" + row + "] <= 10e-5");
            }
            A[row] = -a[row][row + 1] / e[row];
            B[row] = (b[row] - a[row][row - 1] * B[row - 1]) / e[row];
        }

        x[a.length - 1] = (b[row] - a[row][row - 1] * B[row - 1]) / (a[row][row] + a[row][row - 1] * A[row - 1]);
        for (row = a.length - 2; row >= 0 ; row--) {
            x[row] = A[row] * x[row + 1] + B[row];
        }

        return x;
    }

    public static double[] gaussMethod(double[][] a, double[] b) throws Exception {
        int row, column;
        double[] x = new double[a.length];
        double[] temp;
        double tempElement;
        double factor;

        if (a.length != a[0].length) {
            throw new Exception("Matrix isn't a square matrix!");
        }


        for (column = 0; column < a.length - 1; column++) {
            for (row = column + 1; row < a.length; row++) {

                if (a[column][column] <= 10e-5) {
                    for (int k = row; k < a.length; k++) {
                        if (a[k][column] != 0) {
                            temp = a[column];
                            a[column] = a[k];
                            a[k] = temp;
                            tempElement = b[column];
                            b[column] = b[k];
                            b[k] = tempElement;
                        }
                    }
                }

                factor = -a[row][column] / a[column][column];
                a[row] = addRows(a, column, row, factor);
                b[row] += b[column] * factor;

            }
        }
        log.info("b: " + Arrays.toString(b));
        print2DArray(a);

        for (row = a.length - 1; row >= 0; row--) {
            x[row] = b[row];
            for (column = row + 1; column < a.length; column++) {
                x[row] -= a[row][column] * x[column];
            }
            x[row] /= a[row][row];
        }

        return x;
    }

    private static double[] addRows(double[][] a, int sourceRow, int destinationRow, double factor) {
        for (int i = 0; i < a.length; i++) {
            a[destinationRow][i] += a[sourceRow][i] * factor;
        }
        return a[destinationRow];
    }

    private static double A(double ci, double bi) {
        return -ci / bi;
    }

    private static double B(double di, double bi) {
        return di / bi;
    }

    private static void print2DArray(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
