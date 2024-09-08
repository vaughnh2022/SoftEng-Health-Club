class Calandar {
    //month, day, and year ints
    int month;
    int day;
    int year;

    public Calandar(int month,int day,int year){ // constructor class
        this.month=month;
        this.day=day;
        this.year=year;
    }
    public boolean inbetween(Calandar first, Calandar second){ //given two calandar dates see if the current calandar is within the two
        boolean firstBoolean = first.year<=this.year&&first.month<=this.month&&first.day<=this.day;
        boolean secondBoolean = second.year>=this.year&&second.month>=this.month&&second.day>=this.day;
        return firstBoolean&&secondBoolean;
    }
}
