package org.um.feri.ears.export.data;

import java.util.ArrayList;

public class StatP2TaskList {
    public String benchmarkRunArenaID;
    public ArrayList<StatPlayer2Task> list;
    public StatP2TaskList() {
        this("");
    }
    
    public StatP2TaskList(String id) {
        list = new ArrayList<StatPlayer2Task>();
        benchmarkRunArenaID = id;
    }
}
