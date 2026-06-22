package de.gothaer.services;

public class MultipliziererImpl implements Multiplizierer {
    @Override
    public long mult( int a,  int b) {
        long result = 0;
        /*if(a > b) {
            var help = a;
            a = b;
            b = help;
        }*/
        for (int i = 0; i < a; i++) {
            result += b;
        }
        return result;
    }
}
