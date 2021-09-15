package patterns.observable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObservableHighscoreList {

	int maxSize;
	List<Integer> result = new ArrayList<>();
	Collection<ObservableListListener> highscoreListListeners = new ArrayList<>();

	ObservableHighscoreList(int maxSize){
		this.maxSize = maxSize;
	}

	public int size() {
		return result.size();
	}

	public int getElement(int numb) {
		return result.get(numb);
	}

	public void addResult(int newResult) {
		System.out.println(newResult);
		List<Integer> oldResultList = result;
		result.add(newResult);
		List<Integer> result = this.result.stream().sorted((o1,o2)->o1.compareTo(o2)).collect(Collectors.toList());
		this.result = result;
		if(result.size() > maxSize) {
			List<Integer> sortedList = result.stream()
					.sorted((o1,o2)-> o1.compareTo(o2))
					.collect(Collectors.toList());
			List<Integer> oldResult = new ArrayList<>();
			oldResult.addAll(sortedList.subList(0, maxSize));
			result.clear();
			result.addAll(oldResult);
		}
		boolean equals = oldResultList.equals(result);
		if (!equals) {
			int index = result.indexOf(newResult);
			listChanging(this,index);
		}
	}
	public void addObservableListListener(ObservableListListener highscoreListListener){
		highscoreListListeners.add(highscoreListListener);

	}

	public void removeHighscoreListListener(ObservableListListener highscoreListListener) {
		highscoreListListeners.remove(highscoreListListener);
	}

	public void listChanging(ObservableHighscoreList list, int index) {
		for (ObservableListListener highscoreListListener : highscoreListListeners) {
			highscoreListListener.listChanged(this, index);
		}

	}
}
