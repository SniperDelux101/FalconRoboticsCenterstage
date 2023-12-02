package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.Utilities.Configuration;

public enum PixelBoxArmPosition {
    PrepExchange,
    Exchange,
    Extake;

    public double getValue(){
        switch (this) {
            case PrepExchange:
                return Configuration.PIXELBOXARM_PREPEXCHANGE;
            case Exchange:
                return Configuration.PIXELBOXARM_EXCHANGE;
            case Extake:
                return Configuration.PIXELBOXARM_EXTAKE;
        }
        return 0;
    }
}
