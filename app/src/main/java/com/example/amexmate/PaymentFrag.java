package com.example.amexmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentFrag extends Fragment  {

    public PaymentFrag() {
        // Required empty public constructor
    }

    List<String>prediction=new ArrayList<>();
    List<List<String>>details=new ArrayList<>();
    ProgressDialog progress;




    private static Retrofit retrofit = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_payment, container, false);

         progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setCancelable(false);
        progress.show();

       GetData data=RetroFitInstance.getRetrofitInstance().create(GetData.class);
       Call<Model> call=data.getAllPhotos();
       call.enqueue(new Callback<Model>() {
           @Override
           public void onResponse(Call<Model> call, Response<Model> response) {
               Log.e("yes",call.toString());
               prediction=response.body().getPrediction();
               int count=0;
               HashMap<String,Integer>valuesMap=new HashMap<>();
               for(int i=0;i<prediction.size();i++)
               {
                   Log.e("namee",prediction.get(i));

                   if (valuesMap.containsKey(prediction.get(i).toLowerCase())) {
                       //   temp=valuesMap.get(nums[i]);
                       Log.e("exis",prediction.get(i).toLowerCase());
                       valuesMap.put(prediction.get(i).toLowerCase(), valuesMap.get(prediction.get(i).toLowerCase())+1);

                   } else {
                       valuesMap.put(prediction.get(i).toLowerCase(), 1);
                   }
               }
               List<Float>values=new ArrayList<>();
               List<String>type=new ArrayList<>();
               for(String name:valuesMap.keySet())
               {
                   Integer val=valuesMap.get(name);
                   Log.e("vall",String.valueOf(val));
                   values.add((float) ((val*100)/prediction.size()));
                   type.add(name);
                   Log.e("name",name);
               }

               for(int i=0;i<values.size();i++)
               {
                   Log.e("valuess",String.valueOf(values.get(i)));
               }
               List<PieEntry>piechart=new ArrayList<>();
               for(int i=0;i<values.size();i++)
               {
                   piechart.add(new PieEntry(values.get(i),type.get(i)));
               }



               PieDataSet dataSet=new PieDataSet(piechart,"Transaction Status");
               dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
               PieData data1=new PieData(dataSet);

               ArrayList<String>transfer=new ArrayList<>();

               ArrayList<String>transfern=new ArrayList<>();

               PieChart chart=view.findViewById(R.id.chart);

               details=response.body().getTest();
              chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                  @Override
                  public void onValueSelected(Entry e, Highlight h) {

                      PieEntry pe = (PieEntry) e;
                      Log.e("LABEL",pe.getLabel());
                      transfer.clear();
                      transfern.clear();
                      for(int i=0;i<prediction.size();i++)
                      {
                          Log.e("compare",prediction.get(i)+pe.getLabel());
                          if(prediction.get(i).toLowerCase().equals(pe.getLabel().toLowerCase())){
                              transfer.add(details.get(i).get(0));
                              transfern.add(details.get(i).get(1));

                          }
                      }

                      Intent in=new Intent(getActivity(),DetailActivity.class);
                      in.putExtra("label",pe.getLabel());
                      in.putStringArrayListExtra("test", (ArrayList<String>) transfer);
                      in.putStringArrayListExtra("testn", (ArrayList<String>) transfern);
                      Log.e("siizer",String.valueOf(transfer.size()));
                      startActivity(in);


                  }

                  @Override
                  public void onNothingSelected() {

                  }
              });
               chart.setData(data1);
               chart.animateY(100);
               chart.invalidate();

               progress.dismiss();





           }

           @Override
           public void onFailure(Call<Model> call, Throwable t) {

           }
       });
        return view;
    }


}