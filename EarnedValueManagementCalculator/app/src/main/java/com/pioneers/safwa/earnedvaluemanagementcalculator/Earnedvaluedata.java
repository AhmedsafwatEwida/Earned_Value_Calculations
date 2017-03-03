package com.pioneers.safwa.earnedvaluemanagementcalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by safwa on 2/7/2017.
 */

public class Earnedvaluedata extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earned_data);
        String rawHTML = "<HTML>"+
                "<body style='color: #1e656d; background-color: #f1f3cf'> </body>"+" %s "+
                "</HTML>";
        String myData = "<h2>Earned Value Management:</h2>\n" +
                "(EVM) helps project managers to measure project performance.\n" +

                "<P> EVM is used on the cost and schedule control and can be very useful in project forecasting.</p>\n" +
                "EVM provides quantitative data for project decision making.\n" +
                "<h3>Primary Data Points:</h3>\n" +
                "<li>Budget At Completion (BAC): Total cost (or hours) of the project.\n" +
                "<li>Budgeted Cost for Work Scheduled (BCWS) OR Planned Value (PV):The amount expressed in Pounds (or hours) of work to be performed as per the schedule plan.</li>\n" +
                "<P>PV = BAC * % of planned work.</p>\n" +
                "<li>Budgeted Cost for Work Performed (BCWP) OR Earned Value (EV):The amount expressed in Pounds (or hours) on the actual worked performed.</li>\n" +
                "<P>EV = BAC * % of Actual work.</p>\n" +
                "<li>Actual Cost of Work Performed (ACWP) OR Actual Cost (AC):The sum of all costs in Pounds (or hours) actually accrued for a task to date.</li>\n" +
                "<h3>Cost Forecasting:</h3>\n" +
                "<li>Estimate At Completion (EAC):The expected TOTAL cost(or hours) required to finish complete work.</li>\n" +
                "<P>EAC = BAC / CPI </p>\n" +
                "<li>Estimate to complete (ETC):The expected cost (or hours) required to finish all the REMAINING work.</li>\n" +
                "<P>ETC  = EAC - AC,= (BAC / CPI) - (EV/CPI),= (BAC - EV) / CPI .</p>\n" +
                "<h3>Variances:</h3>\n" +
                "<li>Cost Variances (CV):How much under or over budget.</li>\n" +
                "<P>CV = EV-AC  NEGATIVE is over budget, POSITIVE is under budget.</p>\n" +
                "<li>Schedule Variances (SV):How much ahead or behind schedule .</li>\n" +
                "<P>SV = EV-PV  NEGATIVE is behind schedule, POSITIVE is ahead of schedule .</p>\n" +
                "<li>Variance At Completion (VAC):Variance of TOTAL cost(or hours) of the work and expected cost .</li>\n" +
                "<P>VAC = BAC - EAC  </p>\n" +
                "<h3>Performance Indices:</h3>\n" +
                "<li>Cost Performance Index:CPI     = EV / AC      Over (< 1) or under (> 1) budget</li>" +
                "<li>Schedule Performance Index:SPI = EV / PV  Ahead (> 1) or behind (< 1) schedule</li>";
        WebView webView = (WebView) findViewById(R.id.webView1);
        webView.loadData(String.format(rawHTML, myData,Color.parseColor("#1e656d")), "text/html", "utf-8");

    }
}
