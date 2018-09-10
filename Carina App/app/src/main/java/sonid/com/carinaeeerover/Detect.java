package sonid.com.carinaeeerover;

//import libraries

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


/**
 * Created by Konstantinos Barmpas on 10/2/17. https://thesonid.com
 */

public class Detect extends Fragment {

    //declare the class variables.

    WebView dWebview;                                           //for sending the URL.


    //Method to create connect the Java code to our layout.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.detect, container, false);

        //declare the layout-java variables.

       // Button resultButton=(Button)rootView.findViewById(R.id.detect_button);
        Button radioButton=(Button)rootView.findViewById(R.id.radio_button);

        dWebview  = (WebView) rootView.findViewById(R.id.detect_show);
        dWebview.setWebViewClient(new WebViewClient());
        dWebview.getSettings().setJavaScriptEnabled(true);                  // enable javascript


                                                                            //set the buttons and connect them with the functions.

        /*resultButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick  (View view){
                try{
                    DetectShow();
                }
                catch (Exception ex){
                    //nothing
                }
            }
        }); */
        radioButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick  (View view){
                try{
                    Detect();
                }
                catch (Exception ex){
                    //nothing
                }
            }
        });
        return rootView;
    }

    /*
    * All class functions. Each function send the corresponding URL for sensor detection.
    * */

    /*
    * --------------------------------------------------------------
    * */

   /* public void DetectShow(){
        dWebview.loadUrl("http://192.168.0.16/arduino/web/");
    } */

    public void Detect(){
        dWebview.loadUrl("http://192.168.0.16/arduino/web/detect");
    }

}



