package Worlds_Collide.Maps;

public enum Level {
    level_1,level_2,level_3;


    public static Level getLvl(int LVL){
        switch (LVL){
            case 0: return level_1;
            case 1: return level_2;
            case 2:return level_3;
            default: return null;
        }
    }
}
