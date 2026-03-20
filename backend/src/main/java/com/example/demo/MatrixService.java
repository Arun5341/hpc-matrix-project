package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class MatrixService {

    public Result multiply(int[][] A, int[][] B) {
        int n = A.length;

        // SERIAL
        long startSerial = System.nanoTime();
        int[][] serialResult = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    serialResult[i][j] += A[i][k] * B[k][j];

        long endSerial = System.nanoTime();

        // PARALLEL
        long startParallel = System.nanoTime();
        int[][] parallelResult = new int[n][n];

        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            final int row = i;

            threads[i] = new Thread(() -> {
                for (int j = 0; j < n; j++)
                    for (int k = 0; k < n; k++)
                        parallelResult[row][j] += A[row][k] * B[k][j];
            });

            threads[i].start();
        }

        // wait for threads
        for (int i = 0; i < n; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endParallel = System.nanoTime();

        double serialTime = (endSerial - startSerial) / 1_000_000.0;
        double parallelTime = (endParallel - startParallel) / 1_000_000.0;

        return new Result(parallelResult, serialTime, parallelTime);
    }
}