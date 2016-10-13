package com.n26.response.wrapper;

/**
 * This is a response wrapper which is used to render the sum output as:
 *  {"sum": 100.00}
 *
 * @author Biniam Asnake
 */
public class TransactionSum {

    double sum;

    public TransactionSum(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
