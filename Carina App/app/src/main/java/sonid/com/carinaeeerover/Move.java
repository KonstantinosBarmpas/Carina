package sonid.com.carinaeeerover;

//import libraries

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Konstantinos Barmpas on 10/2/17. https://thesonid.com
 */

public class Move extends Fragment {

    public final static String EXTRA_MESSAGE = "com.example.carina.MESSAGE";

    //declare the class variables.

    WebView mWebview;                                                           //for sending the URL.
    TextToSpeech t1;                                                            //for voice announcements.
    EditText angle,cm;                                                          //to get desired distance/angle.
    String direction,ur1,ur2;                                                   //the announced direction.
    ImageButton btnSpeak;                                                       //button activating voice commands.
    final int REQ_CODE_SPEECH_INPUT = 100;

    //Method to create connect the Java code to our layout.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.move, container, false);


        //declare the layout-java variables.


        Button rb=(Button) rootView.findViewById(R.id.right);
        Button fb=(Button) rootView.findViewById(R.id.forward);
        Button lb=(Button) rootView.findViewById(R.id.left);
        Button br=(Button) rootView.findViewById(R.id.rotation);
        Button turn=(Button)rootView.findViewById(R.id.turn);
        Button turnl=(Button)rootView.findViewById(R.id.turnl);
        Button gof=(Button)rootView.findViewById(R.id.gof);
        Button gob=(Button)rootView.findViewById(R.id.gob);
        angle=(EditText)rootView.findViewById(R.id.angle);
        cm=(EditText)rootView.findViewById(R.id.cm);
        mWebview  = (WebView) rootView.findViewById(R.id.webview);
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.getSettings().setJavaScriptEnabled(true);                  // enable javascript

                                                                            //set the buttons and connect them with the functions.
                                                                           //if they are on touch we send urls based on the action.

        fb.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch (View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                      mWebview.loadUrl("http://192.168.0.16/arduino/web/stop");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/b/ty");
                }

                return true;
            }
        });

        br.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch (View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/stop");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/f/ty");

                }
                return true;
            }
        });

        lb.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch (View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/stop");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/l/ty");
                }
                return true;
            }
        });

        rb.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch (View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/stop");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/r/ty");
                }
                return true;
            }
        });


        turn.setOnClickListener(new Button.OnClickListener(){
            public void onClick  (View view){
                try{
                    AngleTurn(0);
                }
                catch (Exception ex){
                    //nothing
                }
            }
        });

        turnl.setOnClickListener(new Button.OnClickListener(){
            public void onClick  (View view){
                try{
                    AngleTurn(1);
                }
                catch (Exception ex){
                    //nothing
                }
            }
        });

         gof.setOnClickListener(new Button.OnClickListener(){
            public void onClick  (View view){
                try{
                    GoF();
                }
                catch (Exception ex){
                    //nothing
                }
            }
        });

       gob.setOnClickListener(new Button.OnClickListener(){
            public void onClick  (View view){
                try{
                    GoB();
                }
                catch (Exception ex){
                    //nothing
                }
            }
        });


        btnSpeak = (ImageButton) rootView.findViewById(R.id.btnSpeak);   //set the button for voice commands.
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });


        return rootView;
    }

    /*
    * All class functions. There are 2 Categories of functions.
    * */

    /*
    * --------------------------------------------------------------
    * */

    /*
    * In the first category there are the functions which perform some calculations.
    * Based on the number they get from the user they create the corresponding URL to send.
    */

    public void send(String command){
        mWebview.loadUrl(command);
    }

    public void AngleTurn (int l) {                                                  //rotate rover.
        String test = angle.getText().toString().trim();
        if (!test.matches("")) {
            float angle_number = Float.valueOf(angle.getText().toString());     //take the rotation angle from the user.
            String messageAngle;
            if (l == 0) {                                             //if positive we go right.
                String u="http://192.168.0.16/arduino/web/r/tn/"+angle_number;     //create the URL.
                mWebview.loadUrl(u);                                            //send the URL.

            } else if (l == 1) {                                      //if negative we go left.
                String u="http://192.168.0.16/arduino/web/l/tn/"+angle_number;     //create the URL.
                mWebview.loadUrl(u);                                            //send the URL.

            } else {
                messageAngle = "No rotation found";                             //if zero we do not rotate.
            }
        }
    }


    public void GoF () {                                                      //go forward.
        String test = cm.getText().toString().trim();
        if (!test.matches("")) {
            float cm_number = Float.valueOf(cm.getText().toString());        //take the cm for forward from the user.
            String messageCm;
            if (cm_number > 0) {                                            //if positive input is correct.
                String u="http://192.168.0.16/arduino/web/f/tn/"+cm_number;    //create the URL.
                mWebview.loadUrl(u);                                        //send the URL.
            } else {
                messageCm="invalid input";                                 //if zero or negative invalid input.
            }
        }
    }

    public void GoB () {                                                         //go forward.
        String test = cm.getText().toString().trim();
        if (!test.matches("")) {
            float cm_number = Float.valueOf(cm.getText().toString());           //take the cm for back from the user.
            String messageCm;
            if (cm_number > 0) {                                                //if positive input is correct.
                String u="http://192.168.0.16/arduino/web/b/tn/"+cm_number;        //create the URL.
                mWebview.loadUrl(u);                                            //send the URL.
            } else {
                messageCm="invalid input";                                      //if zero or negative invalid input.
            }
        }
    }


    /*
    * --------------------------------------------------------------
    * */

    /*
    * In the second category there are the functions which are related to voice.
    * Either voice announcements or voice commands.
    */


                                                            //this function announces the direction of the rover.

    public void Talk (){
        t1=new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){
                    t1.setLanguage(Locale.ENGLISH);
                    t1.speak(direction,TextToSpeech.QUEUE_ADD,null);
                }
            }
        });
    }

                                                        //this function activates the voice input microphone.

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity().getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

                                                        //this function takes the voice input.

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String voc=result.get(0);
                        if (voc.matches("forward")){                    //if the input is forward we send the corresponding URL.
                           mWebview.loadUrl("http://192.168.0.16/arduino/web/f/tn/3");
                            direction="Rover is going forward 3 cm";
                            Talk ();                                   //announce the direction.

                        }else if (voc.matches("right")){                    //if the input is right we send the corresponding URL.
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/r/tn/90");
                        direction="Rover is going: right 90 degrees";
                        Talk ();                                       //announce the direction.

                        }else if (voc.matches("left")){                      //if the input is left we send the corresponding URL.
                        mWebview.loadUrl("http://192.168.0.16/arduino/web/l/tn/90");
                        direction="Rover is going: left 90 degrees";
                        Talk ();                                      //announce the direction.

                        }else if (voc.matches("back")){                    //if the input is back we send the corresponding URL.
                       mWebview.loadUrl("http://192.168.0.16/arduino/web/b/tn/3");
                        direction="Rover is going: back 3 cm";
                        Talk ();                                      //announce the direction.

                        }else{                                           //if the input is invalid it announces it.
                            direction="no valid input. Please say Front, Right, Left or Back";
                            Talk();
                        }
                }
                break;
            }

        }
    }


}