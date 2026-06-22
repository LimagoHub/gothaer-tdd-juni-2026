package de.gothaer.services;

public class DependencyImpl implements Dependeny {

    @Override
    public void foo(final String s) {
        System.out.println(s);
    }

    @Override
    public int bar() {
        return 42;
    }

    @Override
    public int foobar(final String s) {
        return s.length();
    }
}
