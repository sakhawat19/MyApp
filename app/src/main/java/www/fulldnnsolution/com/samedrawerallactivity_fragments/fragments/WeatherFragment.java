package www.fulldnnsolution.com.samedrawerallactivity_fragments.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import www.fulldnnsolution.com.samedrawerallactivity_fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {


    TextView tv_temp, tv_city, tv_desc, tv_date, tv_temp_max, tv_temp_min, tv_pressure, tv_hum;


    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_weather,
                container, false);


        tv_temp =(TextView) view.findViewById(R.id.tv_temp1);
        tv_temp_max =(TextView) view.findViewById(R.id.tv_temp_max);
        tv_temp_min =(TextView) view.findViewById(R.id.tv_temp_min);

        tv_pressure =(TextView) view.findViewById(R.id.tv_pressure);
        tv_hum =(TextView) view.findViewById(R.id.tv_hum);

        tv_city =(TextView) view.findViewById(R.id.tv_city);
        tv_desc =(TextView) view.findViewById(R.id.tv_desc);
        tv_date =(TextView) view.findViewById(R.id.tv_date);

        findweather();

        return view;

    }

    public void findweather()
    {
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=23.71&lon=90.41&appid=d8c41e352db0d57a5fa5e6fbc75fd8c1";
        JsonObjectRequest jot = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String pressure = String.valueOf(main_object.getDouble("pressure"));
                    String humidity = String.valueOf(main_object.getDouble("humidity"));
                    String temp_max = String.valueOf(main_object.getDouble("temp_max"));
                    String temp_min = String.valueOf(main_object.getDouble("temp_min"));

                    String description = object.getString("description");
                    String city = response.getString("name");


                    tv_city.setText(city);
                    tv_desc.setText(description);
                    tv_pressure.setText(pressure);
                    tv_hum.setText(humidity);



                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                    String formetted_date = sdf.format(calendar.getTime());
                    tv_date.setText(formetted_date);

//                    double temp_int = Double.parseDouble(temp);
//                    double centi = (temp_int - 273.15);
//                    centi = Math.round(centi);
//                    int ii =(int)centi;

                    Double tempi = Double.parseDouble(temp);
                    Double tempi_max = Double.parseDouble(temp_max);
                    Double tempi_min = Double.parseDouble(temp_min);



                    tv_temp.setText(String.valueOf(getCelsius(tempi)+" C"));
                    tv_temp_max.setText(String.valueOf(getCelsius(tempi_max)+" C"));
                    tv_temp_min.setText(String.valueOf(getCelsius(tempi_min)+" C"));

                }catch (JSONException e)
                {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(jot);
    }


    // Convert degreesKelvin to Celsius and return the value

    public double getCelsius(double input)
    {
        double c = input - 273.16;
        c = Math.round(c);
        int output = (int)c;
        return output;
    }




}
