class Chasse {
	
	public Mange chasser() {
		
	}
}

class Mange extends Chasse{
	
	public Selles manger() {
		
	}
}

class Selles extends Mange{
	
	public Defection seLacher(Papier p) {
		
	}
}

class Papier {
	
	public Papier (Lotus lot) {
		
	}
}

class Lotus {

}

class Cycle {
	
	Chasse a = new Chasse ();

	void process () { 
		
		a.chasser().manger().seLacher(new Papier(new Lotus()));
		
	}
	
}