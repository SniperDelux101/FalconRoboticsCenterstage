package com.example.meepmeeptesting.Paths.V1;


import com.example.meepmeeptesting.Paths.Configuration;

public class CommonPathSettings {
    public static double Tile_Width = 23;
    public static double TURN_RIGHT = Math.toRadians(-90);
    public static double TURN_LEFT = Math.toRadians(90);
    public static double Center_SpikeMark_Distance = (Tile_Width * 2) - Configuration.WHEELBASE;
    public static double Near_Park_Distance = 35;

    //common values for setting pixel on left & right spike marks
    public static double Spike_Offset = 3;
    public static double Distance_To_SpikeMark = (Tile_Width/2) + (Configuration.WHEELBASE/2);
    public static double Park_Offset = 5;

    //Far or Audience Settings
    public static double Far_Center_Offset = Configuration.WHEELBASE/2;
    public static double Far_Park_Distance = Tile_Width * 4;


}
