package org.um.feri.ears.export.data;

import java.util.ArrayList;

public class EDStatP2PList {
    public String benchmarkRunArenaID;
    public ArrayList<EDStatPlayer2Player> list;
    public EDStatP2PList() {
        this("");
    }
    public EDStatP2PList(String id) {
        list = new ArrayList<EDStatPlayer2Player>();
        benchmarkRunArenaID = id;
    }
}
