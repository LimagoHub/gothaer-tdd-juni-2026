package de.gothaer.services;

public class MultipliziererOptimiererDecorator implements Multiplizierer {

    private final Multiplizierer multiplizierer;

    public MultipliziererOptimiererDecorator(final Multiplizierer multiplizierer) {
        this.multiplizierer = multiplizierer;
    }

    @Override
    public long mult(final int a, final int b) {
        if(a > b) {
            return multiplizierer.mult(b, a);
        } else  {
            return multiplizierer.mult(a, b);
        }
    }
}
