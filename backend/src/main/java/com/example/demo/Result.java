package com.example.demo;

public class Result {
    public int[][] result;
    public double serialTime;
    public double parallelTime;

    public Result(int[][] result, double serialTime, double parallelTime) {
        this.result = result;
        this.serialTime = serialTime;
        this.parallelTime = parallelTime;
    }
}
