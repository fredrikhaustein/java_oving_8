package patterns.observable;

public class HighscoreListProgram implements ObservableListListener{

	ObservableHighscoreList highscoreList;
	
	public void init() {
		
		highscoreList = new ObservableHighscoreList(8);
		highscoreList.addObservableListListener(this);
	}
	
	public void run(int number) {
		highscoreList.addResult(number);
	}


	@Override
	public void listChanged(ObservableHighscoreList list, int index) {
		System.out.println(list);
		System.out.println(index);
		System.out.println(this);
	}
	
}
