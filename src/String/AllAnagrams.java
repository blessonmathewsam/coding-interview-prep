package String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAnagrams {
	
	//Return all anagrams of p in s
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<>();
		if (p.length() > s.length())
			return res;
		Map<Character, Integer> map = new HashMap<>();
		for (char c : p.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int counter = map.size();
		int begin = 0, end = 0;
		while (end < s.length()) {
			char c = s.charAt(end);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);
				if (map.get(c) == 0)
					counter--;
			}
			end++;
			while (counter == 0) {
				if (end - begin == p.length()) {
					res.add(begin);
				}
				char begC = s.charAt(begin);
				if (map.containsKey(begC)) {
					map.put(begC, map.get(begC) + 1);
					if (map.get(begC) > 0)
						counter++;
				}
				begin++;
			}
		}
		return res;
	}
}
