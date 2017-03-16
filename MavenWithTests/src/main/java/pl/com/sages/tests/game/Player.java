package pl.com.sages.tests.game;

public class Player {
	
	private String name;
	private int level;
	
	public Player(){
		
	}
	
	public Player(String name, int level) {
		this.name = name;
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	public boolean play() {
		if(level > 0){
			System.out.println("Playing game");
			return true;
		} 
		
		return true;
	}
	
	

}
