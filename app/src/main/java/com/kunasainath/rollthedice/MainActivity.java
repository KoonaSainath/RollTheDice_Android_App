package com.kunasainath.rollthedice;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button; //creating a reference to Button.
    Random random; //creating a reference to a Random object.
    ImageView diceImage1, diceImage2;  //creating references to 2 images, which are visible at the start of app.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.rollingButton); //getting button address using its id
        random = new Random(); //creating Random object
        diceImage1 = findViewById(R.id.img1); //getting first image address using its id
        diceImage2 = findViewById(R.id.img2); //getting second image address using its id

        final MediaPlayer play = MediaPlayer.create(this,R.raw.dice_sound);
        //creating a MediaPlayer object refering to an audio file in my file system.

        final int[] diceImages = {R.drawable.dice_1, R.drawable.dice_2, R.drawable.dice_3, R.drawable.dice_4, R.drawable.dice_5, R.drawable.dice_6};
        //adding addresses of image files in Drawable folder of res. They are stored at positions 0,1,2,3,4,5.

        button.setOnClickListener(new View.OnClickListener() {  //setting an onClickListener for button.
            @Override
            public void onClick(View view){ //overriding onClick() method of View class. onClickListener is a method in View.
                int randomNumber1 = random.nextInt(6); //creating a variable with random number from 0 to 5;
                int randomNumber2 = random.nextInt(6); //creating a variable with random number from 0 to 5;
                diceImage1.setImageResource(diceImages[randomNumber1]);
                //use the first random number as index to extract new image from above array.
                diceImage2.setImageResource(diceImages[randomNumber2]);
                //use the second random number as index to extract new image from above array.


                //these are libraries imported from a github repository of animations.
                //the dependencies are updated in build.gradle(app) file, so that they would work perfectly.
                YoYo.with(Techniques.Shake) //shake is a type of animation available in that repository.
                        .duration(500) //milliseconds
                        .repeat(3)
                        .playOn(diceImage1); //ImageView object of first image.

                YoYo.with(Techniques.Shake)
                        .duration(500)
                        .repeat(3)
                        .playOn(diceImage2); //ImageView object of second image.

                play.start();  //starting the sound when dice is rolled.
            }
        });

    }
}