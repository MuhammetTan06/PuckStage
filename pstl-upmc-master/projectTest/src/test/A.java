package test;
import java.util.ArrayList;

enum Couleur {
  RED,
  BLUE
}
class B extends A{
	
}

class A {
	
	B bb = new B();
  enum t {
    rr,
    cc
  }

  private ArrayList<Couleur> couleurs;

  private Couleur r = Couleur.RED;

  Couleur mom (Couleur c) {
    return r;
  }
}