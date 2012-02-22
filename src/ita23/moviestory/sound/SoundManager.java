package ita23.moviestory.sound;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * @author Fabian Bottler
 * @version 1.0
 */
public enum SoundManager  {

    /** The instance to use */
    INSTANCE;

  //  private AudioClip buttonClip;
   // private  File buttonUrl = new File("out/production/MovieStory/res/sound/buttonClick.wav");
   // private AudioClip jobFinishClip;
    private AudioClip backGroundClip;
    private   File backGroundSoundFile= new File("out/production/MovieStory/res/sound/backgroundSound.wav");

    private boolean isPlaySound = true;


    private SoundManager() {
            //MockTest mockTest = new MockTest();


            try {
               // URI uri =backGroundSoundFile.toURI();
                URL url = backGroundSoundFile.toURI().toURL();
                System.out.println(url);
                backGroundClip = Applet.newAudioClip(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

           // doBackGroundSound();

    }

    public void doBackGroundSound() {
        System.out.println("DO Play");
        if(backGroundClip==null){
            System.out.println("Null");
        }else{
            System.out.println("Not Null");
        backGroundClip.play();
        }
           //backGroundClip.loop();
    }

    public void stopBackGroundSound(){
   //     backGroundClip.stop();
    }


    public void mute(){
        stopBackGroundSound();
        isPlaySound=false;
    }
    public void unMute(){
        doBackGroundSound();
        isPlaySound=true;
    }


    public void doSound(Sound sound){

        if(isPlaySound){


            switch (sound) {
                case BUTTON:
           //         buttonClip.play();
                    break;

                case JOBFINISH:
            //        jobFinishClip.play();
                    break;
            }
        }

    }

    public enum Sound {
        BUTTON,JOBFINISH
    }

}
