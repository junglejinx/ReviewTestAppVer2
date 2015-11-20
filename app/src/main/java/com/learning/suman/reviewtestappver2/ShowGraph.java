package com.learning.suman.reviewtestappver2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.BubbleChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ShowGraph extends ActionBarActivity {

    ReadBooksDBHandler displayDataHandler;

    Integer bookCount=0;
    String dbString="";
    String language_dbString="";
    //String genre_dbString="";
    List<String> authorNames=new ArrayList<String>();
    List authorBookCount=new ArrayList();
    List<String> languages=new ArrayList<String>();
    List languageCount=new ArrayList();
    int i=0;
    int li=0;
    int flag=0;
    int lflag=0;
    String record="";
    String lrecord="";
    int index=0;
    int lindex=0;
    String prevRecord="";
    String languagePreviousRecord="";
    LinearLayout graphLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_graph);


        graphLinearLayout=(LinearLayout)findViewById(R.id.graphLinearLayout);
        displayDataHandler = new ReadBooksDBHandler(this, null, null, 1);

        //get the value of bookCount from ReadBooksDataHandler
        bookCount=Integer.parseInt(displayDataHandler.totalBookCount());
        //get the data of number of different authors books read from ReadBooksDataHandler
        dbString = displayDataHandler.authorCountGraphDatabaseToString();
        //get the data of number of different languages books read from ReadBooksDataHandler
        language_dbString=displayDataHandler.languageCountGraphDatabaseToString();

        if(dbString.equals("")||language_dbString.equals("")){
            Toast.makeText(this,"No data to show Graph", Toast.LENGTH_LONG).show();
        }




        //make separate lists of author names and corresponding number of times the book is read by that author.
        if (dbString == null) {
            Toast.makeText(getApplicationContext(), "no books to display", Toast.LENGTH_LONG).show();
            return;
        } else {
            //dbString=ank|ank|chi|chi|chi|yas|yas
            while (i < dbString.length()) {


                if (dbString.charAt(i) == '|') {


                    if (authorNames.contains(record)) {
                        //return;
                    }
                    else {
                        authorNames.add(record);
                        authorBookCount.add(flag);

                        if(prevRecord!=record){
                            flag=0;
                        }
                    }
                    flag++;
                    prevRecord=record;
                    record="";
                }
                else {

                    record = record + dbString.charAt(i);

                }
                i++;
            }

            authorBookCount.add(flag);
            authorBookCount.remove(0);


        }
        //replace all ==null to .isempty()
        if (language_dbString == null) {
            Toast.makeText(getApplicationContext(), "no books to display", Toast.LENGTH_LONG).show();
            return;
        } else {
            //dbString=ank|ank|chi|chi|chi|yas|yas
            while (li < language_dbString.length()) {


                if (language_dbString.charAt(li) == '|') {


                    if (languages.contains(lrecord)) {
                        //return;
                    }
                    else {
                        languages.add(lrecord);
                        languageCount.add(lflag);

                        if(languagePreviousRecord!=lrecord){
                            lflag=0;
                        }
                    }
                    lflag++;
                    languagePreviousRecord=lrecord;
                    lrecord="";
                }
                else {

                    lrecord = lrecord + language_dbString.charAt(li);

                }
                li++;
            }

            languageCount.add(lflag);
            languageCount.remove(0);


        }
        //Toast.makeText(this, languages.toString(), Toast.LENGTH_LONG).show();
        //Toast.makeText(this, languageCount.toString(), Toast.LENGTH_LONG).show();

        //call the plotGraph method
        plotGraph(authorNames,authorBookCount,languages,languageCount);

    }

   public void plotGraph(List authorNames,List<Integer> authorBookCount,List languages,List<Integer> languageCount){
       //Type of graphs to be plotted
       String[] graphTypes=new String[]{BarChart.TYPE,LineChart.TYPE};

       //get the xAxis values of number of authors count for plotting Bar graph
       String j="";
       int k=0;

       Integer [] xValues = new Integer[authorBookCount.size()];
       for(int i = 0;i < xValues.length;i++) {
           xValues[i] = authorBookCount.get(i);
       }

       //get the xAxis values of number of authors count for plotting Line graph
       String lj="";
       int lk=0;

       Integer[] languagexValues = new Integer[languageCount.size()];
       for(int li = 0;li < languagexValues.length;li++) {
           languagexValues[li] = languageCount.get(li);
       }


       //Add the values to the Bar graph dataseries
       XYSeries dataSeries=new XYSeries("Books read by particular author");
       for(int t=0;t<xValues.length;t++){
           dataSeries.add(k,xValues[t]);
           dataSeries.addAnnotation(authorNames.get(t).toString(),k,xValues[t]);

           k=k+2;
       }

       //Toast.makeText(this,xValues[0].toString(),Toast.LENGTH_LONG).show();


       //Add the values to the Linegrapgh dataseries
       XYSeries languageDataSeries=new XYSeries("Languages read ");
       for(int lt=0;lt<languagexValues.length;lt++){
           languageDataSeries.add(lk,languagexValues[lt]);
           languageDataSeries.addAnnotation(languages.get(lt).toString(),lk,languagexValues[lt]);

           lk=lk+2;

       }



       //set Bar graph display properties for author
       XYSeriesRenderer rend=new XYSeriesRenderer();
       rend.setLineWidth(1);
       //rend.setColor(Color.MAGENTA);
       rend.setDisplayChartValues(true);

       rend.setPointStyle(PointStyle.DIAMOND);
       rend.setChartValuesSpacing(1);
        rend.setGradientEnabled(true);
       rend.setGradientStart(0,Color.YELLOW);
       rend.setGradientStop(0.5,Color.GREEN);



       //set Line graph display properties for languages
       XYSeriesRenderer languageRend=new XYSeriesRenderer();
       languageRend.setLineWidth(2);
       languageRend.setColor(Color.RED);
       languageRend.setDisplayChartValues(true);
       languageRend.setPointStyle(PointStyle.CIRCLE);
       languageRend.setFillPoints(true);
       languageRend.setChartValuesSpacing(2);
       //languageRend.setFillBelowLine(true);
       //languageRend.setFillBelowLineColor(Color.RED);




       XYMultipleSeriesDataset multiDataSeries=new XYMultipleSeriesDataset();
       multiDataSeries.addSeries(dataSeries);
       multiDataSeries.addSeries(languageDataSeries);

       XYMultipleSeriesRenderer multirend=new XYMultipleSeriesRenderer();
       multirend.addSeriesRenderer(rend);
       multirend.addSeriesRenderer(languageRend);

       multirend.setXLabelsAlign(Paint.Align.CENTER);

       multirend.setLabelsColor(Color.BLUE);
       multirend.setXLabels(1);
       multirend.setChartTitle("statistics of Languages and Authors read");
       multirend.setChartTitleTextSize(20);
       multirend.setAxisTitleTextSize(16);
       multirend.setPanEnabled(false,false);
       multirend.setYAxisMax(bookCount);
       multirend.setXAxisMax(bookCount);
       multirend.setXAxisMin(0);
       multirend.setYAxisMin(0);
       multirend.setYAxisMin(0);
       multirend.setShowGrid(true);
       multirend.setZoomButtonsVisible(true);
       multirend.setBarSpacing(1);
       multirend.setMarginsColor(Color.WHITE);
       multirend.setAxesColor(Color.BLUE);
       multirend.setLabelsTextSize(15);
       multirend.setLegendTextSize(15);
       multirend.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);

       //GraphicalView chartView= ChartFactory.getLineChartView(this, multiDataSeries, multirend);
       //GraphicalView chartView= ChartFactory.getBarChartView(this, multiDataSeries, multirend, BarChart.Type.DEFAULT);
       GraphicalView chartView= ChartFactory.getCombinedXYChartView(this, multiDataSeries, multirend, graphTypes);

       graphLinearLayout.addView(chartView);

       for(int i = 0;i < xValues.length;i++) {
           xValues[i] = null;
       }
       for(int i = 0;i < languagexValues.length;i++) {
           languagexValues[i] = null;
       }

   }

    public void homeScreen(View view){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
