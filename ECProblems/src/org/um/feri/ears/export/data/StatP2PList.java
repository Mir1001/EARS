package org.um.feri.ears.export.data;

import java.util.ArrayList;

public class StatP2PList {
    public String benchmarkRunArenaID;
    public ArrayList<StatPlayer2Player> list;
    public StatP2PList() {
        this("");
    }
    public StatP2PList(String id) {
        list = new ArrayList<StatPlayer2Player>();
        benchmarkRunArenaID = id;
    }
}
