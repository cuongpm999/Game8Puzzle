/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Cuong Pham
 */
public class Matrix {

    private int[][] matrix;

    public Matrix() {
        createMatrix();
        showMatrix();
    }
    
    public void showMatrix() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void createMatrix() {
        matrix = new int[3][3];
        Random rand = new Random();
        List<Point> listPoint = new ArrayList<Point>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                listPoint.add(new Point(i, j));
            }
        }

        for (int i = 1; i <= 9; i++) {
            int size = listPoint.size();
            int pointIndex = rand.nextInt(size);
            matrix[listPoint.get(pointIndex).x][listPoint.get(pointIndex).y] = i;
            listPoint.remove(pointIndex);
        }

    }
    
    public EmptyCellPoint getPointXY(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               if(matrix[i][j]==9) return new EmptyCellPoint(i, j);
            }
        }
        return null;
    }
    
    public boolean checkWin() {
        int k=1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(matrix[i][j]!=k) return false;
                else k++;
            }
            System.out.println();
        }
        return true;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

}
