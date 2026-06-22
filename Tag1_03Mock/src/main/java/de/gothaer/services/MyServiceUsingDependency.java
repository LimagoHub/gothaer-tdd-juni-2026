package de.gothaer.services;

public class MyServiceUsingDependency {

    private Dependeny dependeny;

    public MyServiceUsingDependency(final Dependeny dependeny) {
        this.dependeny = dependeny;
    }
    /*
     public void foo(final String s) {
        System.out.println(s);
    }

     */
    public void eins(String value) {
            var result = value.toUpperCase();
            dependeny.foo(result);
            dependeny.foo(result);
    }

    /*
     public int bar() {
        return 42;
    }
     */
    public int zwei() {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            result += dependeny.bar();
        }
        return result;
    }
    /*

         public int foobar(final String s) {
        return s.length();
    }
     */

    public int drei(){
        return dependeny.foobar("Hallo Welt") * dependeny.foobar("Hallo Welt");
    }

}
