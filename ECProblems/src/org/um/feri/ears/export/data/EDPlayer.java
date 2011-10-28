package org.um.feri.ears.export.data;
public class EDPlayer {
    public String acronym; // acronym 
    public String id_version; // if you made some semantic changes compared
    public String info;
    public EDRating newRating;
    public EDRating oldRating;
    public EDWinnLossDraw stat;
    @Override
    public String toString() {
        return "EDPlayer [acronym=" + acronym + ", id_version=" + id_version + ", info=" + info + ", newRating=" + newRating + ", oldRating=" + oldRating
                + ", stat=" + stat + "]";
    }
}
