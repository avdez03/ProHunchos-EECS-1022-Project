package com.example.prohunchosleeecs1022;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

/*
 * Author : Alex Valdez, Huy Anh Vu Tran , Khandker Hasan
 * Team : ProHunchos
 * LE EECS 1022 Project = "What number am I thinking of"?
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declare music variable for the theme music
        MediaPlayer themeMP = MediaPlayer.create(this, R.raw.theme);
        CheckBox musicPlayer = (CheckBox) findViewById(R.id.musicPlayer);
        musicPlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            //Will play background music when the checkbox is checked
            //The music will stop when it is unchecked
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //will play theme music
                    themeMP.start();
                }//end if statement

                if (!isChecked) {
                    //will pause the theme music
                    themeMP.stop();
                }//end if statement

            }//end onCheckedChanged()
        });
    }//end onCreate()

    //Display an alert informing the user how to play
    public void rules(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        //Set Alert Title
        builder.setTitle("How to play");

        //Set the message show for the Alert time
        builder.setMessage(R.string.howToPlay);

        //Set Cancelable false for when the user clicks on
        //The outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        //Set the button on the dialog box with text close that will close the box
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // If user clicks close then dialog box is canceled.
                dialog.cancel();
            }//end onClick()
        });
        //Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        //Show the Alert Dialog box
        alertDialog.show();
    }//end rules

    //Global variable that will be changed to the random value in startGame()
    //So that check knows what the random value is
    double random = 0;

    //Global variable that will be changed to the amount of guesses the user has in startGame()
    //So that check knows what the amount of guesses the user has is
    double counter = 0;

    //Receive the upper limit from the user and the amount of guesses
    //Then use getRandom from ProjectModel.java to generate a random number from one and the user's upper limit
    public void startGame(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Will receive the upper limit from the user
        alert.setTitle("Upper limit?");
        alert.setMessage("What's the upper limit?");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                random = ProjectModel.getRandom(ProjectModel.toDouble(value));
                TextView randomText = (findViewById(R.id.random));
                randomText.setText(ProjectModel.format(random));
            }//end onClick()
        });
        alert.show();

        //Will receive the amount of guesses from user
        alert.setTitle("How many guesses?");
        alert.setMessage("Input the amount of guesses you have");

        // Set an EditText view to get user input
        final EditText guess = new EditText(this);
        alert.setView(guess);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = guess.getText().toString();
                counter = ProjectModel.toDouble(value);
                TextView guessCounter = (findViewById(R.id.counter));
                guessCounter.setText(ProjectModel.format(counter));
            }//end onClick()
        });
        alert.show();
    }//end startGame()

    //Check to see if the user has guessed correctly
    //Also checks if the user has run out of guesses
    public void check(View v) {
        EditText g = findViewById(R.id.guessBox);

        String gS = g.getText().toString();

        double guess = ProjectModel.toDouble(gS);

        TextView answer = (findViewById(R.id.answer));

        TextView guessCounter = (findViewById(R.id.counter));

        //To keep track if the user has guessed correctly
        boolean win = false;

        //Will check if the user has guessed correctly
        //As long as they have guesses remaining
        if (counter != 0) {
            if (guess == random) {
                answer.setText(R.string.win);
                win = true;
                //Will receive the user's name
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Congratulations");
                alert.setMessage("You have won, please insert your name below?");

                // Set an EditText view to get user input
                final EditText userName = new EditText(this);
                alert.setView(userName);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = userName.getText().toString();
                        TextView name = (findViewById(R.id.name));
                        name.setText(value);
                    }//end onClick()
                });
                alert.show();
            } else if (guess != random) {
                answer.setText(R.string.lose);
                counter--;
                guessCounter.setText(ProjectModel.format(counter));
            }//end if statement
        }//end if statement

        //Once the user runs out of guesses display it in an alert to inform the user
        if (!win && counter == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Tuff luck :(");
            builder.setMessage(R.string.end);

            // Set Cancelable false for when the user clicks on
            // the outside the Dialog Box then it will remain show
            builder.setCancelable(false);

            //Set the button on the dialog box with text close that will close the box
            builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // If user clicks close then dialog box is canceled.
                    dialog.cancel();
                }//end onClick()
            });
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();

            // Show the Alert Dialog box
            alertDialog.show();
        }//end if statement
    }//end check()

    /*
    //https://www.youtube.com/watch?v=aQAIMY-HzL8&list=LL&index=3
    //Use this link to create a list of winners names and their amount of guesses
    //So that when a user wins their name can be inputted into this list
    //Then can be accessed through the click of a button
    public void leaderboard(View v) {

    }//end leaderboard
    **/

}//end class