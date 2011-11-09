package org.um.feri.ears.export.data;

import java.io.Serializable;
import java.util.ArrayList;

public class EDStatP2TaskList  implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 8503879528226167731L;
    public String benchmarkRunArenaID;
    public ArrayList<EDStatPlayer2Task> list;
    public EDStatP2TaskList() {
        this("");
    }
    
    public EDStatP2TaskList(String id) {
        list = new ArrayList<EDStatPlayer2Task>();
        benchmarkRunArenaID = id;
    }

    @Override
    public String toString() {
        return "EDStatP2TaskList [benchmarkRunArenaID=" + benchmarkRunArenaID + ", list=" + list + "]";
    }
}
